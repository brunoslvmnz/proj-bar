package com.log.Log.controlador;

import com.log.Log.entidades.Log;
import com.log.Log.servicos.LogServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogControlador {

    @Autowired
    private LogServico logServico;

    @PostMapping
    public Log criarLog(Log log) {
        return logServico.criarLog(log);
    }

    @GetMapping("/{id}")
    public Log encontrarLogPeloId(@PathVariable String id) {
        return logServico.encontrarLogPeloId(id);
    }

    @GetMapping
    public List<Log> encontrarLogs() {
        return logServico.encontrarLogs();
    }

    @PutMapping("/atualizar")
    public Log atualizarLog(Log log) {
        return logServico.atualizarLog(log);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarLog(@PathVariable String id) {
        logServico.deletarLog(id);
    }

    @GetMapping("/acao")
    public List<Log> buscaLogPorAcao(@RequestBody String acao){
        return logServico.buscaLogPorAcao(acao);
    }

    @GetMapping("/data/adm")
    public List<Log> buscaAdministradorPorData(@RequestBody String data){
        return logServico.buscaAdministradorPorData(data);
    }

    @GetMapping("/data/produto")
    public List<Log> buscaAdministradorProdutosPorData(@RequestBody String data){
        return logServico.buscaAdministradorProdutosPorData(data);
    }

    @GetMapping("/nome/adm")
    public List<Log> buscaAdministradorPorNome(@RequestBody String nome){
        return logServico.buscaAdministradorPorNome(nome);
    }

    @GetMapping("/nome/produto")
    public List<Log> buscaPorNomeProduto(@RequestBody String nome){
        return logServico.buscaPorNomeProduto(nome);
    }
}
