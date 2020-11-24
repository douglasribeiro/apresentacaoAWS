package com.apresentacaoaws.domain;

import java.util.ArrayList;
import java.util.List;

import com.apresentacaoaws.domain.enuns.EstadoPedido;
import com.apresentacaoaws.domain.enuns.Role;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
	
	private Long id;
	private String nome;
	private String email;
	private String password;
	private Role role;
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private List<EstadoPedido> estados = new ArrayList<EstadoPedido>();
	
	

}
