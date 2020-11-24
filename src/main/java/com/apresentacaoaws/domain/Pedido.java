package com.apresentacaoaws.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido {

	private Long id;
	private String objeto;
	private String descricao;
	private Date dataCriacao;
	private PedidoEstagio estagio;
	private Usuario usuario;
	private List<PedidoEstagio> estagios = new ArrayList<PedidoEstagio>();
}
