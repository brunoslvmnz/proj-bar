package com.caixa.Caixa.controlador;

import com.caixa.Caixa.entidades.Caixa;
import com.caixa.Caixa.servicos.CaixaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caixa")
public class CaixaControlador {
    @Autowired
    private CaixaServico caixaServico;

    @PostMapping
    private Caixa criarGarcom(@RequestBody Caixa caixa){
        return caixaServico.criarCaixa(caixa);
    }

    @GetMapping("/{id}")
    private Caixa buscarCaixaPeloId(@PathVariable String id){
        return caixaServico.buscarCaixaPorId(id);
    }

    @GetMapping
    private List<Caixa> buscarCaixas(){
        return caixaServico.buscarCaixas();
    }

    @PutMapping("/atualizar")
    private Caixa atualizarCaixa(@RequestBody Caixa caixa){
        return caixaServico.atualizarCaixa(caixa);
    }

    @DeleteMapping("/deletar/{id}")
    private void deletarCaixa(@PathVariable String id){
        caixaServico.deletarCaixa(id);
    }

    @PutMapping("/{id}/fechar/pedidos")
    private Caixa fecharPedido(@PathVariable String id){
        return caixaServico.fecharPedido(id);
    }
}
