package com.tweetapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.model.User;
import com.tweetapp.repository.UserRepository;

@Service
public class RegistrationImpl implements Registration{
	@Autowired
    private UserRepository userRepository;


	@Override
	public User registerUser(User user) throws Exception {
		user = userRepository.save(user);
        return user;
	}


	@Override
	public User findUserByEmail(String email) throws Exception {
		return userRepository.findByEmail(email);
	}


	public boolean validateuser(String email, String password) throws Exception {
		User user=userRepository.findByEmail(email);
		if(user==null)
		return false;
		String pass=user.getPassword();
		if(password!=null && password.equals(pass)) {
			return true;
		}
		return false;
	}


	@Override
	public User getUser(String email) throws Exception {
		User user = userRepository.findByEmail(email);
        return user;
    }


	@Override
	public List<User> getAllUser() throws Exception {
		List<User> users = userRepository.findAll();
        return users;
	}
	
  
}
