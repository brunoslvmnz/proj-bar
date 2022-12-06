package com.administrador.Administrador.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "produtos")
public class Produto {
    @Id
    private String id;
    private Date dataCadastro = new Date();
    private String nome;
    private Double preco;
    private String descricao;
}
