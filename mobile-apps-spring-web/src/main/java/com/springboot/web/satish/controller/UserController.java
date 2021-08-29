package com.springboot.web.satish.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.satish.model.UpdateUserDetails;
import com.springboot.web.satish.model.UserDetailsRequest;
import com.springboot.web.satish.model.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {

	Map<String, UserRest> mapUsers;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<UserRest> getUsers() {
		
		List<UserRest> userRests = new ArrayList<UserRest>();
		
//		List<UserRest> userRests = mapUsers.entrySet().stream().collect(Collectors.toList());

		return null;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		if (null != mapUsers && mapUsers.containsKey(userId)) {
			return new ResponseEntity<UserRest>(mapUsers.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}

	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.ALL_VALUE })
	public ResponseEntity<UserRest> createUsers(@Valid @RequestBody UserDetailsRequest userDetailsRequest) {
		if (null == mapUsers)
			mapUsers = new HashMap<>();

		String userId = UUID.randomUUID().toString();

		UserRest user = new UserRest();
		user.setUserId(userId);
		user.setFirstName(userDetailsRequest.getFirstName());
		user.setLastName(userDetailsRequest.getLastName());
		user.setEmail(userDetailsRequest.getEmail());
		mapUsers.put(userId, user);
		return new ResponseEntity<UserRest>(mapUsers.get(userId), HttpStatus.CREATED);
	}

	@PutMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> updateUsers(@PathVariable String userId,
			@Valid @RequestBody UpdateUserDetails updateUserDetails) {

		UserRest userRest = mapUsers.get(userId);

		userRest.setFirstName(updateUserDetails.getFirstName());
		userRest.setLastName(updateUserDetails.getLastName());

		return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> deleteUsers(@PathVariable String userId) {

		if (mapUsers.containsKey(userId)) {
			mapUsers.remove(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
