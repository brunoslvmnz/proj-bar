package com.garcom.Garcom.entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;
    private Date dataCadastro = new Date();
    private Double valorPedido;
    private String status;
    private Boolean viagem;
    private Garcom garcom;
    private List<Produto> produtoList = new ArrayList<>();
}
