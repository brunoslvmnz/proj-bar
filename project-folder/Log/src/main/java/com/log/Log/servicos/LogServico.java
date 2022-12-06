package com.log.Log.servicos;

import com.log.Log.entidades.Log;
import com.log.Log.repositorio.LogRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServico {
    @Autowired
    private LogRepositorio logRepositorio;

    public Log criarLog(Log log) {
        return logRepositorio.save(log);
    }

    public Log encontrarLogPeloId(String id) {
        return logRepositorio.findById(id).get();
    }

    public List<Log> encontrarLogs() {
        return logRepositorio.findAll();
    }

    public Log atualizarLog(Log log) {
        if (log.getId() != null) {
            return logRepositorio.save(log);
        }
        return log;
    }

    public void deletarLog(String id) {
        var log = logRepositorio.findById(id).get();
        if (log.getId() != null) {
            logRepositorio.delete(log);
        }
    }

    public List<Log> buscaLogPorAcao(String acao) {
        return logRepositorio.buscaPorAcao(acao);
    }

    public List<Log> buscaAdministradorPorData(String data) {
        return logRepositorio.buscaPorAdministradorDataDeCadastro(data);
    }

    public List<Log> buscaAdministradorProdutosPorData(String data) {
        return logRepositorio.buscaPorAdministradorProdutosDataDeCadastro(data);
    }

    public List<Log> buscaAdministradorPorNome(String nome) {
        return logRepositorio.buscaPorAdministradorPorNome(nome);
    }

    public List<Log> buscaPorNomeProduto(String nome) {
        return logRepositorio.buscaPorNomeDoProduto(nome);
    }
}
