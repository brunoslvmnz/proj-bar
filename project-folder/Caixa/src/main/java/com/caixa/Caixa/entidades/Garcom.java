package com.caixa.Caixa.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Garcom {

    private String id;
    private String nome;
    private Date dataCadastro = new Date();
    List<Pedido> pedidos = new ArrayList<>();
}
