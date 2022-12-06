package com.garcom.Garcom.repositorio;

import com.garcom.Garcom.entidades.Garcom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarcomRepositorio extends MongoRepository<Garcom, String> {
}
