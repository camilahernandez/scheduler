package com.camila.scheduler.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camila.scheduler.model.User;
import com.camila.scheduler.model.UserAppointment;
import com.camila.scheduler.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping(path="/{userId}")
	public User getUserDetail(@PathVariable ("userId") UUID userId) {
		return userService.getUserbyId(userId);
	}
	
	@GetMapping(path="/{userId}/appointments")
	public List<UserAppointment> getApptsForUser(@PathVariable ("userId") UUID userId) {
		return userService.checkAppointmentsForUser(userId);
	}
	
	@PostMapping()
	public int addUser(@RequestBody User newUser) {
		return userService.save(newUser);
	}
	
	@PostMapping(path="/createAppt/")
	public int addApptForUser(@RequestBody UserAppointment newAppointment) {
		return userService.createAppointment(newAppointment);
	}
	
}
