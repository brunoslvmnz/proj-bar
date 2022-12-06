package com.administrador.Administrador.controlador;

import com.administrador.Administrador.entidades.Administrador;
import com.administrador.Administrador.entidades.Produto;
import com.administrador.Administrador.servicos.AdministradorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adm")
public class AdministradorControlador {
    @Autowired
    private AdministradorServico administradorServico;

    @PostMapping
    private Administrador criarAdministrador(@RequestBody Administrador administrador){
        return administradorServico.criarAdministrador(administrador);
    }

    @GetMapping("/{id}")
    private Administrador buscarAdministrador(@PathVariable String id){
        return administradorServico.encontrarAdministradorPeloId(id);
    }

    @GetMapping("/nome")
    private Administrador buscarAdministradorPeloNome(@RequestBody String nome){
        return administradorServico.encontrarAdministradorPeloNome(nome);
    }


    @GetMapping
    private List<Administrador> buscarAdministradores(){
        return administradorServico.encontrarTodosOsAdministradores();
    }

    @PutMapping("/atualizar/{id}")
    private Administrador atualizarAdministrador(@PathVariable String id, @RequestBody Administrador administrador){
        return administradorServico.atualizarAdministrador(id, administrador);
    }

    @DeleteMapping("/deletar/{id}")
    private void deletarAdministrador(@PathVariable String id){
        administradorServico.deletarAdministrador(id);
    }

    @PostMapping("/{id}/cadastro/produtos")
    private Administrador administradorCadastraProduto(@PathVariable String id, @RequestBody Produto produto){
        return administradorServico.administradorCadastraProduto(id, produto);
    }

    @PutMapping("/{id}/atualiza/produtos")
    private Administrador administradorAtualizaProduto(@PathVariable String id, @RequestBody Produto produto){
        return administradorServico.administradorAtualizaProduto(id, produto);
    }

    @DeleteMapping("/{id}/deleta/produtos")
    private Administrador administradorDeletaProduto(@PathVariable String id, @RequestBody Produto produto){
        return administradorServico.administradorDeletaProduto(id, produto);
    }
}
