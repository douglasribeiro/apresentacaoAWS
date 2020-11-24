package com.apresentacaoaws.domain;

import java.util.Date;

import com.apresentacaoaws.domain.enuns.EstadoPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoEstagio {
	private Long id;
	private String descricao;
	private Date realizacao;
	private EstadoPedido estado;
	private Pedido pedido;
	private Usuario usuario;
}
