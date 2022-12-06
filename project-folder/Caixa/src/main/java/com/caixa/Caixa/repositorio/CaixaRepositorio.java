package com.caixa.Caixa.repositorio;

import com.caixa.Caixa.entidades.Caixa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaixaRepositorio extends MongoRepository<Caixa, String> {
}
