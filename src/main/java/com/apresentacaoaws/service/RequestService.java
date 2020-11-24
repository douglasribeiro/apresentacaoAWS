package com.apresentacaoaws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apresentacaoaws.domain.Request;
import com.apresentacaoaws.domain.enuns.RequestState;
import com.apresentacaoaws.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired private RequestRepository requestRepository;
	
	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());	
		Request createdRequest = requestRepository.save(request);
		return createdRequest;
	}
	
	public Request update(Request request) {
		Request updatedRequest = requestRepository.save(request);
		return updatedRequest;
	}
	
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.get();
	}
	
	public List<Request> listAll(){
		return requestRepository.findAll();
	}
	
	public List<Request> listAllByOwnerId(Long ownerId){
		return requestRepository.findAllByOwnerId(ownerId);
	}
}
