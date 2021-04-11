package com.tweetapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.service.RegistrationImpl;
import com.tweetapp.service.TweetServiceImpl;

@RestController
public class TweetController {

	@Autowired
	private TweetServiceImpl tweetService;
	
	@Autowired
	private RegistrationImpl usercontroller;
	
	@Autowired
	private TweetRepository tweetrepo;
	
	//create a tweet or post a tweet
	@PostMapping("/api/v1.0/{email}/add")
    public Tweet createTweets(@Valid @RequestBody Tweet tweet,User user) throws Exception {
		String email=user.getEmail();
		String password=user.getPassword();
		if(usercontroller.validateuser(email, password)==true) {
			return tweetService.createTweet(tweet);
		}
		return tweet;
        
    }
	
	//get all tweets
	 @GetMapping("/api/v1.0/tweets/all")
	    public List<Tweet> getAllTweet() {
	        return tweetService.getAllTweets();
	    }
	 
	 //get tweets by email
	 @GetMapping("/api/v1.0/tweets/{email}")
	    public ResponseEntity<Tweet> getTweetByemail(@PathVariable(value = "email") String email) {
	        return tweetrepo.findById(email)
	                .map(savedTweet -> ResponseEntity.ok(savedTweet))
	                .orElseGet( () -> ResponseEntity.notFound().build());
	    }
}
