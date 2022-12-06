package com.garcom.Garcom.repositorio;


import com.garcom.Garcom.entidades.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends MongoRepository<Pedido, String> {
}
