package com.apresentacaoaws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.apresentacaoaws.domain.Request;
import com.apresentacaoaws.domain.RequestStage;
import com.apresentacaoaws.domain.enuns.RequestState;
import com.apresentacaoaws.exception.NotFoundException;
import com.apresentacaoaws.model.PageModel;
import com.apresentacaoaws.model.PageRequestModel;
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
		return result.orElseThrow(() -> new NotFoundException("Não existe este id " + id));
	}
	
	
	public List<RequestStage> listAllByRequestId(Long requestId){
		List<RequestStage> stages = requestStateRepository.findAllByRequestId(requestId);
		return stages;
	}
	
	public PageModel<RequestStage> listAllByRequestIdOnLazyModel(Long requestId, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<RequestStage> page = requestStateRepository.findAllByRequestId(requestId, pageable);
		PageModel<RequestStage> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
}
 