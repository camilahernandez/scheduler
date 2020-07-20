package com.camila.scheduler.dao;

import java.util.List;
import java.util.UUID;

import com.camila.scheduler.model.User;
import com.camila.scheduler.model.UserAppointment;

public interface PersonDao {
	int insertPerson(User newUser);
	List<User> getUsers();
	List<UserAppointment> getAppointmentsbyUser(UUID userId);
	User getUserbyId(UUID id);
	String validateAppointment(UserAppointment appointmentDate);
	boolean validateUserExists(UUID userId);
	boolean validatePhysicianExists(Integer physicianId);
	boolean isInfoTaken(String email, String phNumber);
	int schedule(UserAppointment appointment);
	
}
