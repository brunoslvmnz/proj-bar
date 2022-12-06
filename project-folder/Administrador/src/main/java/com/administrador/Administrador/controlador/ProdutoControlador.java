package com.administrador.Administrador.controlador;

import com.administrador.Administrador.entidades.Produto;
import com.administrador.Administrador.servicos.ProdutoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoControlador {
    @Autowired
    private ProdutoServico produtoServico;

    @PostMapping
    private Produto criarProduto(@RequestBody Produto produto){
        return produtoServico.criarProduto(produto);
    }

    @GetMapping("/{id}")
    private Produto buscarProduto(@PathVariable String id){
        return produtoServico.encontrarProdutoPeloId(id);
    }

    @GetMapping("/nome")
    private Produto buscarProdutoPeloNome(@RequestBody String nome){
        return produtoServico.buscarProdutoPeloNome(nome);
    }

    @GetMapping
    private List<Produto> buscarProdutos(){
        return produtoServico.encontrarTodosOsProdutos();
    }

    @PutMapping("/atualizar")
    private Produto atualizarProduto(@RequestBody Produto produto){
        return produtoServico.atualizarProduto(produto);
    }

    @DeleteMapping("/deletar/{id}")
    private void deletarProduto(@PathVariable String id){
        produtoServico.deletarProduto(id);
    }

    @GetMapping("/busca/preco/")
    private Produto buscaProdutoPorPrecoMaior(@RequestBody String preco){
        return produtoServico.buscarProdutoComValorMaiorQueX(preco);
    }

}
