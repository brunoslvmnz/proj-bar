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
@Document(collection = "garcons")
public class Garcom {

    @Id
    private String id;
    private String nome;
    private Date dataCadastro = new Date();
    List<Pedido> pedidos = new ArrayList<>();
}
