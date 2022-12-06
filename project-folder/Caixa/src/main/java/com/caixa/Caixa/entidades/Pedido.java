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
public class Pedido {
    private String id;
    private Date dataCadastro = new Date();
    private String status;
    private Double valorPedido;
    private Boolean viagem;
    private Garcom garcom;
    private List<Produto> produtoList = new ArrayList<>();
}
