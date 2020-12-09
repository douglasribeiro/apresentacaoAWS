 package com.apresentacaoaws.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.apresentacaoaws.dto.UserLoginResponseDto;
import com.apresentacaoaws.dto.UserSaveDto;
import com.apresentacaoaws.dto.UserUpdateDto;
import com.apresentacaoaws.dto.UserUpdateRoleDto;
import com.apresentacaoaws.model.PageModel;
import com.apresentacaoaws.model.PageRequestModel;
import com.apresentacaoaws.security.JwtManager;
import com.apresentacaoaws.service.RequestService;
import com.apresentacaoaws.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired private UserService userService;
	@Autowired private RequestService requestservice;
	@Autowired private AuthenticationManager authManager; 
	@Autowired private JwtManager jwtManager;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userDto){
		User userToSave = userDto.transformToUser();
		User userCreated = userService.save(userToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateDto userDto){
		User user = userDto.transformToUser();
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
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyModel(pr);
		
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserLoginDto user){
		//User userLoged = userService.login(user.getEmail(), user.getPassword());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		Authentication auth = authManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		org.springframework.security.core.userdetails.User userSpring =
				(org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities().stream()
				.map(authority -> authority.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(jwtManager.createToken(email, roles));
	}
	
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestById(
			@PathVariable(name = "id") Long id,
			@RequestParam(value = "size") int size,
			@RequestParam(value = "page") int page){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestservice.listAllByOwnerIdOnLazyModel(id,pr);
		return ResponseEntity.ok(pm);
	}	
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateRoleDto userDto){
		User user = new User();
		user.setId(id);
		user.setRole(userDto.getRole());
		
		userService.updateRole(user);
		
		return ResponseEntity.ok().build(); 
	}

}
