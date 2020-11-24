package com.apresentacaoaws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apresentacaoaws.domain.RequestStage;
import com.apresentacaoaws.domain.enuns.RequestState;
import com.apresentacaoaws.repository.RequestRepository;
import com.apresentacaoaws.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired private RequestStageRepository requestStateRepository;
	
	@Autowired private RequestRepository requestRepository;
	
	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());
		RequestStage result = requestStateRepository.save(stage);
		
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		
		requestRepository.updateStatus(requestId, state);
		return result;
	}
	
	public RequestStage getById(Long id) {
		Optional<RequestStage> result = requestStateRepository.findById(id);
		return result.get();
	}
	
	
	public List<RequestStage> listAllByRequestId(Long requestId){
		List<RequestStage> stages = requestStateRepository.findAllByRequestId(requestId);
		return stages;
	}
}
 