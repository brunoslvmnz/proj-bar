package com.caixa.Caixa.servicos;

import com.caixa.Caixa.GarcomFeign.PedidoFeign;
import com.caixa.Caixa.entidades.*;
import com.caixa.Caixa.msg.CaixaEnviaMensagem;
import com.caixa.Caixa.repositorio.CaixaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaixaServico {
    @Autowired
    private CaixaRepositorio caixaRepositorio;

    @Autowired
    private CaixaEnviaMensagem caixaEnviaMensagem;

    @Autowired
    private PedidoFeign pedidoFeign;

    public Caixa criarCaixa(Caixa caixa) {
        Log<Caixa> log = new Log<>(caixa);
        log.setAction(Action.ADD);
        caixaEnviaMensagem.sendMessage(log);
        return caixaRepositorio.save(caixa);
    }

    public Caixa buscarCaixaPorId(String id) {
        return caixaRepositorio.findById(id).get();
    }

    public List<Caixa> buscarCaixas() {
        List<Caixa> caixas = caixaRepositorio.findAll();
        var pedidos = pedidoFeign.buscarPedidos();
        List<Pedido> pedidosFechados = new ArrayList<>();
        for (Pedido ped : pedidos) {
            if (ped.getStatus().equals("FECHADO")) {
                pedidosFechados.add(ped);
            }
        }
        for (int i = 0; i < caixas.size(); i++) {
            caixas.get(i).setPedidos(pedidosFechados);
        }
        return caixas;
    }

    public Caixa atualizarCaixa(Caixa caixa) {
        if (caixa.getId() != null) {
            var cx = caixaRepositorio.findById(caixa.getId()).get();
            cx.setNome(caixa.getNome());
            cx.setPedidos(caixa.getPedidos());
            Log<Caixa> log = new Log<>(caixa);
            log.setAction(Action.UPDATE);
            caixaEnviaMensagem.sendMessage(log);
            return caixaRepositorio.save(cx);
        }
        return null;
    }

    public void deletarCaixa(String id) {
        if (id != null) {
            var caixa = caixaRepositorio.findById(id).get();
            Log<Caixa> log = new Log<>(caixa);
            log.setAction(Action.REMOVE);
            caixaEnviaMensagem.sendMessage(log);
            caixaRepositorio.delete(caixa);
        }
    }

    public Caixa fecharPedido(String id) {
        var pedidos = pedidoFeign.buscarPedidos();
        List<Pedido> pedidosFechados = pegarPedidosComStatusFechado(pedidos);
        var caixa = caixaRepositorio.findById(id).get();
        calculaValorPedidoComStatusFechar(pedidosFechados, pedidos);
        atualizaListaDePedidosDoCaixa(pedidosFechados, caixa);
        atualizaPedidosNoBanco(pedidosFechados);
        criaLog(caixa);
        atualizaPedidosDoGarcom(pedidosFechados);
        return caixa;
    }

    public List<Pedido> pegarPedidosComStatusFechado(List<Pedido> pedidos){
        List<Pedido> pedidosFechados = new ArrayList<>();
        for (Pedido ped : pedidos) {
            if (ped.getStatus().equals("FECHADO")) {
                pedidosFechados.add(ped);
            }
        }
        return pedidosFechados;
    }

    public List<Pedido> calculaValorPedidoComStatusFechar(List<Pedido> pedidosFechados, List<Pedido> pedidos){
        var total = 0.00;
        for (Pedido ped : pedidos) {
            if (ped.getStatus().equals("FECHAR")) {
                for (Produto prod : ped.getProdutoList()) {
                    total += prod.getPreco();
                }
                ped.setValorPedido(total);
                ped.setStatus("FECHADO");
                pedidosFechados.add(ped);
            }
        }
        return pedidosFechados;
    }

    public Caixa atualizaListaDePedidosDoCaixa(List<Pedido> pedidosFechados, Caixa caixa){
        for (Pedido ped : pedidosFechados) {
            caixa.getPedidos().add(ped);
        }
        atualizarCaixa(caixa);
        return caixa;
    }

    public void atualizaPedidosNoBanco(List<Pedido> pedidosFechados){
        for (int i = 0; i < pedidosFechados.size(); i++) {
            pedidoFeign.atualizarPedido(pedidosFechados.get(i));
        }
    }

    public void criaLog(Caixa caixa){
        Log<Caixa> log = new Log<>(caixa);
        log.setAction(Action.UPDATE);
        caixaEnviaMensagem.sendMessage(log);
    }

    public void atualizaPedidosDoGarcom(List<Pedido> pedidosFechados){
        if (pedidosFechados.size() != 0) {
                pedidoFeign.caixaAtualizaPedido(pedidosFechados);
        }
    }
}
