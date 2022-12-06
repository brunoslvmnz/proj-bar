package com.garcom.Garcom.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private String id;
    private Date dataCadastro = new Date();
    private String nome;
    private Double preco;
    private String descricao;
}
