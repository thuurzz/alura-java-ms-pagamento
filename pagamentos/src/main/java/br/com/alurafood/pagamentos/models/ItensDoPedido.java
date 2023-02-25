package br.com.alurafood.pagamentos.models;

import lombok.Data;

@Data
public class ItensDoPedido {
    private Long id;
    private String quantidade;
    private String descricao;
}
