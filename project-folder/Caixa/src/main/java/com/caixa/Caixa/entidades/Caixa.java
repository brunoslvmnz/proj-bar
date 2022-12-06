package com.caixa.Caixa.entidades;

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
@Document(collection = "caixas")
public class Caixa {

    @Id
    private String id;
    private String nome;
    private Date dataCadastro = new Date();
    @DBRef
    List<Pedido> pedidos = new ArrayList<>();
}
