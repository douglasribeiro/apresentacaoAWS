package com.apresentacaoaws.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apresentacaoaws.domain.Request;
import com.apresentacaoaws.domain.User;
import com.apresentacaoaws.dto.UserLoginDto;
import com.apresentacaoaws.model.PageModel;
import com.apresentacaoaws.model.PageRequestModel;
import com.apresentacaoaws.service.RequestService;
import com.apresentacaoaws.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired private UserService userService;
	@Autowired private RequestService requestservice;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		User userCreated = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user){
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id){
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(
			@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyModel(pr);
		
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDto user){
		User userLoged = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(userLoged);
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestById(@PathVariable(name = "id") Long id){
		List<Request> requests = requestservice.listAllByOwnerId(id);
		return ResponseEntity.ok(requests);
	}
	
}
