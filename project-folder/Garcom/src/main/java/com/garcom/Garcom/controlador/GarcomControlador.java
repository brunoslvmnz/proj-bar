package com.garcom.Garcom.controlador;

import com.garcom.Garcom.entidades.Garcom;
import com.garcom.Garcom.entidades.Pedido;
import com.garcom.Garcom.servicos.GarcomServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garcom")
public class GarcomControlador {

    @Autowired
    private GarcomServico garcomServico;

    @PostMapping
    private Garcom criarGarcom(@RequestBody Garcom garcom){
        return garcomServico.criarGarcom(garcom);
    }

    @GetMapping("/{id}")
    private Garcom buscarGarcomPeloId(@PathVariable String id){
        return garcomServico.buscarGarcomPorId(id);
    }

    @GetMapping
    private List<Garcom> buscarGarcons(){
        return garcomServico.buscarGarcons();
    }

    @PutMapping("/atualizar")
    private Garcom atualizarGarcom(@RequestBody Garcom garcom){
        return garcomServico.atualizarGarcom(garcom);
    }

    @DeleteMapping("/deletar/{id}")
    private void deletarGarcom(@PathVariable String id){
        garcomServico.deletarGarcom(id);
    }

    @PostMapping("/{id}/criar/pedido")
    private Garcom criarPedido(@PathVariable String id, @RequestBody Pedido pedido){
        return garcomServico.criarPedido(id, pedido);
    }

    @PutMapping("/{id}/atualizar/pedido")
    private Garcom atualizarPedido(@PathVariable String id, @RequestBody Pedido pedido){
        return garcomServico.atualizarPedido(id, pedido);
    }

    @DeleteMapping("/{idGarcom}/deletar/pedido/{idPedido}")
    private Garcom deletarPedido(@PathVariable String idGarcom, @PathVariable String idPedido){
        return garcomServico.deletarPedido(idGarcom, idPedido);
    }
}
