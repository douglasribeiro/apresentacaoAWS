package com.apresentacaoaws.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.apresentacaoaws.domain.Request;
import com.apresentacaoaws.domain.User;
import com.apresentacaoaws.domain.enuns.Role;
import com.apresentacaoaws.service.RequestService;
import com.apresentacaoaws.service.UserService;

@Configuration
public class CargaBanco implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired private UserService userService;
	@Autowired private RequestService requestService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		User user = new User();
		List<User> users = userService.listAll();
		if(users.isEmpty()) {
			
			for(int i = 0; i < 50; i++) {
				user.setId(null);
				user.setName("user0"+i);
				user.setEmail("user0"+i+"@gmail.com");
				user.setPassword("user0"+i);
				user.setRole(Role.ADMINISTRATOR);
				userService.save(user);
			}
			
			Request request = new Request();
			
			for(Long i = 0L; i < 50; i++) {
				request.setId(null);
				request.setDescription("pedidor.: 0" + i);
				user.setId(i);
				request.setOwner(user);
				request.setSubject("bla bla bla " + i);
				requestService.save(request);
			}
		}
	}

}
