package com.tweetapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.User;
import com.tweetapp.service.RegistrationImpl;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	@Autowired
    private RegistrationImpl registrationImpl;
	
	
	
	@PostMapping(path = "/api/v1.0/tweets/register")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempemailId=user.getEmail();
		
		if(tempemailId!=null && !"".equals(tempemailId)){
			User userobj = registrationImpl.findUserByEmail(tempemailId);
			System.out.println(tempemailId);
			if(userobj !=null){
				throw new Exception("User with email already exists "+tempemailId);
			}else {
				userobj = registrationImpl.registerUser(user);
				return userobj;
			}
		}
		return user;
		
}
	@PostMapping(path= {"/api/v1.0/tweets/login"})
	public String validateUser(@RequestBody User user) throws Exception {
		String mail=user.getEmail();
		String pswd=user.getPassword();
		if(registrationImpl.validateuser(mail,pswd)) {
			return "success";
		}else {
			return "invalid account";
		}
	}
	
	@GetMapping(path= {"/api/v1.0/tweets/user/search/{email}"} )
	public User getUser(@PathVariable String email) throws Exception{
			return registrationImpl.findUserByEmail(email);
	}
	
	@GetMapping(path= {"/api/v1.0/tweets/users/all"} )
	public List<User> getAllUsers() throws Exception{
			return registrationImpl.getAllUser();
	}
}
