package com.camila.scheduler.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.camila.scheduler.dao.PersonDao;
import com.camila.scheduler.exception.UserRequestException;
import com.camila.scheduler.model.User;
import com.camila.scheduler.model.UserAppointment;

@Service
public class UserService {

	private final PersonDao personDao;
	
	@Autowired
	public UserService(@Qualifier("postgres") PersonDao personDao) {
		this.personDao =personDao;
	}
	
	public int save(User user) {
		if(personDao.isInfoTaken(user.getEmail(), user.getPhNumber())) throw new UserRequestException(user.getEmail() + " or " + user.getPhNumber() + " already in the system");
//			
		return personDao.insertPerson(user);
	}
	
	public List<User> getAllUsers(){
		return personDao.getUsers();
	}
	
	public User getUserbyId(UUID id) {
		return personDao.getUserbyId(id);
	}
	
	public int createAppointment(UserAppointment appointment) {
//		check physician availability
		String availability = personDao.validateAppointment(appointment);
		if (!availability.isEmpty()) throw new UserRequestException(availability);
		
		return personDao.schedule(appointment);
			
	}
	
	public List<UserAppointment> checkAppointmentsForUser(UUID id) {
		if(!personDao.validateUserExists(id)) throw new UserRequestException("Person doesn't exist");
		
		return personDao.getAppointmentsbyUser(id);
	}
	
}
