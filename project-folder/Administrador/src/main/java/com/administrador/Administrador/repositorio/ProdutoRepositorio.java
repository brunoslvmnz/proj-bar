package com.administrador.Administrador.repositorio;

import com.administrador.Administrador.entidades.Administrador;
import com.administrador.Administrador.entidades.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio extends MongoRepository<Produto, String> {
    @Query("{nome:?0}")
    Produto buscarProdutoPeloNome(String nome);

    @Query("{preco: {$gt: ?0}}")
    Produto buscarProdutoComValorMaiorQueX(double preco);

//    @Query("{preco: {$gte: ?0, $lte: ?1}}")
//    Produto buscarProdutoComValorEntreXeY(Double x, Double y);
}
