package com.apresentacaoaws.repository;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apresentacaoaws.domain.Usuario;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public Usuario getByEmaiAndPassword(String nome, String password);
	
	@Query("SELECT FROM Usuario WHERE email = ?1 AND password = ?2")
	public Optional<Usuario>  login(String nome, String password);
}
