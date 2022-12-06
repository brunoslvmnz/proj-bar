package com.log.Log.repositorio;

import com.log.Log.entidades.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepositorio extends MongoRepository<Log, String> {

    @Query("{action: ?0}")
    List<Log> buscaPorAcao(String acao);

    @Query("{'object.dataCadastro': ?0}")
    List<Log> buscaPorAdministradorDataDeCadastro(String data);

    @Query("{'object.nome': ?0}")
    List<Log> buscaPorAdministradorProdutosDataDeCadastro(String data);

    @Query("{'object.nome': ?0}")
    List<Log> buscaPorAdministradorPorNome(String nome);

    @Query("{'object.produtos.nome': ?0}")
    List<Log> buscaPorNomeDoProduto(String nome);
}
