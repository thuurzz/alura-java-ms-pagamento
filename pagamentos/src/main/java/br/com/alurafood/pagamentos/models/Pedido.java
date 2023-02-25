package br.com.alurafood.pagamentos.models;

import lombok.Data;

import java.util.List;

@Data
public class Pedido {
    private List<ItensDoPedido> itens;
}
