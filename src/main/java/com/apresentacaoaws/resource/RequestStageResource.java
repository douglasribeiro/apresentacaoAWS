package com.apresentacaoaws.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apresentacaoaws.domain.Request;
import com.apresentacaoaws.domain.RequestStage;
import com.apresentacaoaws.service.RequestStageService;

@RestController
@RequestMapping(value = "stages")
public class RequestStageResource {

	@Autowired private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody RequestStage requestStage){
		RequestStage requestStageCreated = requestStageService.save(requestStage);
		return ResponseEntity.status(HttpStatus.CREATED).body(requestStageCreated);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id){
		RequestStage requestStage = requestStageService.getById(id);
		return ResponseEntity.ok(requestStage);
	}
	
	
}
