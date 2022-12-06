package com.administrador.Administrador.servicos;

import com.administrador.Administrador.entidades.Action;
import com.administrador.Administrador.entidades.Administrador;
import com.administrador.Administrador.entidades.Log;
import com.administrador.Administrador.entidades.Produto;
import com.administrador.Administrador.msg.AdministradorEnviaMensagem;
import com.administrador.Administrador.repositorio.AdministradorRepositorio;
import com.administrador.Administrador.repositorio.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministradorServico {

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private ProdutoServico produtoServico;

    @Autowired
    private AdministradorEnviaMensagem administradorEnviaMensagem;

    public Administrador criarAdministrador(Administrador administrador) {
        Log<Administrador> log = new Log<>(administrador);
        log.setAction(Action.ADD);
        administradorEnviaMensagem.sendMessage(log);
        return administradorRepositorio.save(administrador);
    }

    public Administrador encontrarAdministradorPeloId(String id) {
        return administradorRepositorio.findById(id).get();
    }

    public Administrador encontrarAdministradorPeloNome(String nome) {
        if (!nome.isEmpty()) {
            var adm = administradorRepositorio.encontrarAdministradorPeloNome(nome);
            return adm;
        }
        return null;
    }

    public List<Administrador> encontrarTodosOsAdministradores() {
        return administradorRepositorio.findAll();
    }

    public Administrador atualizarAdministrador(String id, Administrador administrador) {
        if (id != null) {
            var adm = administradorRepositorio.findById(id).get();
            adm.setNome(administrador.getNome());
            Log<Administrador> log = new Log<>(adm);
            log.setAction(Action.UPDATE);
            administradorEnviaMensagem.sendMessage(log);
            administradorRepositorio.save(adm);
            return adm;
        }
        return null;
    }

    public void deletarAdministrador(String id) {
        if (id != null) {
            var adm = administradorRepositorio.findById(id).get();
            Log<Administrador> log = new Log<>(adm);
            log.setAction(Action.REMOVE);
            administradorEnviaMensagem.sendMessage(log);
            administradorRepositorio.delete(adm);
        }
    }

    public Administrador administradorCadastraProduto(String id, Produto produto) {
        if (id != null) {
            var adm = administradorRepositorio.findById(id).get();
            var prod = produtoServico.criarProduto(produto);
            adm.getProdutos().add(prod);
            Log<Administrador> log = new Log<>(adm);
            log.setAction(Action.ADD);
            administradorEnviaMensagem.sendMessage(log);
            return administradorRepositorio.save(adm);
        }
        return null;
    }

    public Administrador administradorAtualizaProduto(String id, Produto produto) {
        if (id != null) {
            var adm = administradorRepositorio.findById(id).get();
            produtoServico.atualizarProduto(produto);
            List<Produto> prodList= new ArrayList<>();
            for (Produto pdrt :adm.getProdutos()){
                if (pdrt.getId()== produto.getId()){
                    pdrt.setNome(produto.getNome());
                    pdrt.setPreco(produto.getPreco());
                    pdrt.setDescricao(produto.getDescricao());
                    prodList.add(pdrt);
                }
                prodList.add(pdrt);
            }
            adm.setProdutos(prodList);
            Log<Administrador> log = new Log<>(adm);
            log.setAction(Action.UPDATE);
            administradorEnviaMensagem.sendMessage(log);
            return administradorRepositorio.save(adm);
        }
        return null;
    }

    public Administrador administradorDeletaProduto(String id, Produto produto) {
        if (id != null) {
            var adm = administradorRepositorio.findById(id).get();
            var idProd = produto.getId();
            produtoServico.deletarProduto(idProd);
            adm.getProdutos().remove(produto);
            Log<Administrador> log = new Log<>(adm);
            log.setAction(Action.REMOVE);
            administradorEnviaMensagem.sendMessage(log);
            return administradorRepositorio.save(adm);
        }
        return null;
    }
}
