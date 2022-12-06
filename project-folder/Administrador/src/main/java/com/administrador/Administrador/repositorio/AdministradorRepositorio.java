package com.administrador.Administrador.repositorio;

import com.administrador.Administrador.entidades.Administrador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepositorio extends MongoRepository<Administrador, String> {

    @Query("{nome:?0}")
    Administrador encontrarAdministradorPeloNome(String nome);
}
