package com.administrador.Administrador.servicos;

import com.administrador.Administrador.entidades.Administrador;
import com.administrador.Administrador.entidades.Produto;
import com.administrador.Administrador.repositorio.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    public Produto criarProduto(Produto produto){
        return produtoRepositorio.save(produto);
    }

    public Produto encontrarProdutoPeloId(String id){
        return produtoRepositorio.findById(id).get();
    }

    public List<Produto> encontrarTodosOsProdutos(){
        return produtoRepositorio.findAll();
    }

    public Produto atualizarProduto(Produto produto){
        var prod = produtoRepositorio.findById(produto.getId()).get();
        if(produto.getId()!=null){
            prod.setNome(produto.getNome());
            prod.setPreco(produto.getPreco());
            prod.setDescricao(produto.getDescricao());
            produtoRepositorio.save(prod);
        }
        return prod;
    }

    public void deletarProduto(String id){
        if(id!=null){
            var produto = produtoRepositorio.findById(id).get();
            produtoRepositorio.delete(produto);
        }
    }

    public Produto buscarProdutoPeloNome(String nome) {
        if (!nome.isEmpty()){
            var produto = produtoRepositorio.buscarProdutoPeloNome(nome);
            return produto;
        }
        return null;
    }

    public Produto buscarProdutoComValorMaiorQueX(String preco){
        var prod = produtoRepositorio.buscarProdutoComValorMaiorQueX(Double.parseDouble(preco));
        return prod;
    }

}
