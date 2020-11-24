package com.apresentacaoaws.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apresentacaoaws.domain.RequestFile;

@Repository
public interface RequestFileRepository extends JpaRepository<RequestFile, Long> {

	public Page<RequestFile> findAllByRequestId(Long id, Pageable pageable);
}