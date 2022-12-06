package com.garcom.Garcom.controlador;

import com.garcom.Garcom.entidades.Pedido;
import com.garcom.Garcom.servicos.PedidoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoControlador {
    @Autowired
    private PedidoServico pedidoServico;

    @PostMapping
    private Pedido criarPedido(@RequestBody Pedido pedido){
        return pedidoServico.criarPedido(pedido);
    }

    @GetMapping("/{id}")
    private Pedido buscarPedidoPeloId(@PathVariable String id){
        return pedidoServico.buscarPedidoPorId(id);
    }

    @GetMapping
    private List<Pedido> buscarPedidos(){
        return pedidoServico.buscarPedidos();
    }

    @PutMapping("/atualizar")
    private Pedido atualizarPedido(@RequestBody Pedido pedido){
        return pedidoServico.atualizarPedido(pedido);
    }

    @DeleteMapping("/deletar/{id}")
    private void deletarPedido(@PathVariable String id){
        pedidoServico.deletarPedido(id);
    }

    @PutMapping("/caixa/atualiza/pedido")
    private void caixaAtualizaPedido(@RequestBody List<Pedido> pedidosFechados){
        pedidoServico.caixaAtualizaPedido(pedidosFechados);
    }
}
