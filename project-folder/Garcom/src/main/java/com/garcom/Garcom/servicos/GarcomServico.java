package com.garcom.Garcom.servicos;

import com.garcom.Garcom.entidades.*;
import com.garcom.Garcom.msg.GarcomEnviaMensagem;
import com.garcom.Garcom.repositorio.GarcomRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarcomServico {
    @Autowired
    private GarcomRepositorio garcomRepositorio;

    @Autowired
    private PedidoServico pedidoServico;

    @Autowired
    private GarcomEnviaMensagem garcomEnviaMensagem;

    public Garcom criarGarcom(Garcom garcom) {
        Log<Garcom> log = new Log<>(garcom);
        log.setAction(Action.ADD);
        garcomEnviaMensagem.sendMessage(log);
        return garcomRepositorio.save(garcom);
    }

    public Garcom buscarGarcomPorId(String id) {
        return garcomRepositorio.findById(id).get();
    }

    public List<Garcom> buscarGarcons() {
        return garcomRepositorio.findAll();
    }

    public Garcom atualizarGarcom(Garcom garcom) {
        if (garcom.getId() != null) {
            garcom.setNome(garcom.getNome());
            garcom.setPedidos(garcom.getPedidos());
            Log<Garcom> log = new Log<>(garcom);
            log.setAction(Action.UPDATE);
            garcomEnviaMensagem.sendMessage(log);
            return garcomRepositorio.save(garcom);
        }
        return null;
    }

    public void deletarGarcom(String id) {
        if (id != null) {
            var garcom = garcomRepositorio.findById(id).get();
            garcomRepositorio.delete(garcom);
            Log<Garcom> log = new Log<>(garcom);
            log.setAction(Action.REMOVE);
            garcomEnviaMensagem.sendMessage(log);
        }
    }

    public Garcom criarPedido(String id, Pedido pedido) {
        if (id != null) {
            var garcom = garcomRepositorio.findById(id).get();
            var total = 0.00;
            for (Produto prod : pedido.getProdutoList()) {
                total += prod.getPreco();
            }
            pedido.setValorPedido(total);
            var ped = pedidoServico.criarPedido(pedido);
            garcom.getPedidos().add(ped);
            Log<Garcom> log = new Log<>(garcom);
            log.setAction(Action.ADD);
            garcomEnviaMensagem.sendMessage(log);
            return garcomRepositorio.save(garcom);
        }
        return null;
    }

    public Garcom atualizarPedido(String id, Pedido pedido) {
        if (id != null) {
            var garcom = garcomRepositorio.findById(id).get();
            var ped = pedidoServico.atualizarPedido(pedido);
            List<Pedido> pdtList = new ArrayList<>();
            for (Pedido pdt : garcom.getPedidos()) {
                if (pdt.getId() == ped.getId()) {
                    var total = 0.00;
                    for (Produto prod : pedido.getProdutoList()) {
                        total += prod.getPreco();
                    }
                    ped.setStatus(pedido.getStatus());
                    ped.setViagem(pedido.getViagem());
                    ped.setProdutoList(pedido.getProdutoList());
                    ped.setValorPedido(total);
                    ped.setGarcom(pedido.getGarcom());
                    pdtList.add(ped);
                }
                pdtList.add(ped);
            }
            garcom.setPedidos(pdtList);
            Log<Garcom> log = new Log<>(garcom);
            log.setAction(Action.UPDATE);
            garcomEnviaMensagem.sendMessage(log);
            return garcomRepositorio.save(garcom);
        }
        return null;
    }

    public Garcom deletarPedido(String idGarcom, String idPedido) {
        if (idGarcom != null & idPedido != null) {
            var garcom = garcomRepositorio.findById(idGarcom).get();
            var ped = pedidoServico.buscarPedidoPorId(idPedido);
            pedidoServico.deletarPedido(ped.getId());
            List<Pedido> pdtList = new ArrayList<>();
            for (Pedido pdt : garcom.getPedidos()) {
                if (!pdt.equals(ped)) {
                    pdtList.add(pdt);
                }
            }
            garcom.setPedidos(pdtList);
            garcomRepositorio.save(garcom);
            Log<Garcom> log = new Log<>(garcom);
            log.setAction(Action.REMOVE);
            garcomEnviaMensagem.sendMessage(log);
            return garcom;
        }
        return null;
    }
}
