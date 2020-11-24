package com.apresentacaoaws.domain;

import java.util.ArrayList;
import java.util.List;

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
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	
	

}
