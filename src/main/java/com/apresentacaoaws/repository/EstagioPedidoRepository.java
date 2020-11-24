package com.apresentacaoaws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apresentacaoaws.domain.Pedido;
import com.apresentacaoaws.domain.PedidoEstagio;
import com.apresentacaoaws.domain.enuns.EstadoPedido;

@Repository
public interface EstagioPedidoRepository extends JpaRepository<PedidoEstagio, Long> {

	public List<PedidoEstagio> findAllByPedidoId(Long id);
	
	@Query("UPDATE Pedido SET estado = ?2 WHERE id = ?1")
	public Pedido updateStatus (Long id, EstadoPedido estado);
}
