package com.caixa.Caixa.GarcomFeign;

import com.caixa.Caixa.entidades.Pedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("garcom")
public interface PedidoFeign {

    @GetMapping("/pedidos")
    List<Pedido> buscarPedidos();

    @PutMapping("/pedidos/caixa/atualiza/pedido")
    void caixaAtualizaPedido(@RequestBody List<Pedido> pedidosFechados);

    @PutMapping("/pedidos/atualizar")
    Pedido atualizarPedido(@RequestBody Pedido pedido);

}
