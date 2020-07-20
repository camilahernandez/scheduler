package com.camila.scheduler.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.camila.scheduler.model.User;
import com.camila.scheduler.model.UserAppointment;

@Repository("postgres")
public class PersonDataAccess implements PersonDao{

	protected final String INSERT_PERSON = "INSERT INTO person (id,email,ph_number,first_name,last_name) VALUES(uuid_generate_v4() , ? , ? , ? , ?)";
	protected final String SELECT_ALL = "SELECT * FROM person";
	protected final String GET_USER_BY_ID = "SELECT * FROM person where id = ? ";
	protected final String GET_APPOINTMETNS_BY_USER = "SELECT * FROM user_appointment where fk_person_id = ? ";
	protected final String IS_INFO_TAKEN = "SELECT EXISTS (SELECT 1 from person where email = ? or ph_number = ? )";
	
	private final JdbcTemplate jdbc;
	
	@Autowired
	public PersonDataAccess(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public int insertPerson(User newUser) {
		return jdbc.update(INSERT_PERSON, newUser.getEmail(),newUser.getPhNumber(),newUser.getFirstName(),newUser.getLastName());
	}

	@Override
	public List<User> getUsers() {
		return jdbc.query(SELECT_ALL, mapUserfromDB());
	}
	
	@Override
	public User getUserbyId(UUID id) {
		return  jdbc.queryForObject(GET_USER_BY_ID, new Object[] {id} ,mapUserfromDB());
	}
	
	@Override
	public boolean isInfoTaken(String email,String phNumber) {
		
		return jdbc.queryForObject(IS_INFO_TAKEN, new Object[] {email,phNumber},
				(rs,i) -> rs.getBoolean(1));
	}
	
	@Override
	public String validateAppointment(UserAppointment appointment) {
		String message = "";
		
		if(!validateUserExists(appointment.getPatientId())) {
			return "Person does not exist";
		}else if(!validatePhysicianExists(appointment.getPhysicianId())) {
			return "Physician not Found";
		}else if(jdbc.queryForObject("Select count(*) from user_appointment where fk_physician_id = ? and appointment_date = ? ", new Object[]{appointment.getPhysicianId(),appointment.getDate()},(rs,i) -> rs.getInt(1)) >0 ) {
			return "Physician booked";
		}else {
			return message;
		}
	}

	@Override
	public int schedule(UserAppointment appointment) {
			return jdbc.update("Insert into user_appointment (fk_physician_id, fk_person_id ,appointment_date) values (? ,? ,? ) ", new Object[] {appointment.getPhysicianId(),
					appointment.getPatientId(),
					appointment.getDate()});
	}
	
	private RowMapper<User> mapUserfromDB(){
		return (rs,i) -> new User(
				UUID.fromString(rs.getString("id")),
				rs.getString("ph_number"), 
				rs.getString("email"),
				rs.getString("first_name"),
				rs.getString("last_name"));
		
	}
	private RowMapper<UserAppointment> mapAppointmentfromDB(){
		return (rs,i) -> new UserAppointment(UUID.fromString(rs.getString("fk_person_id")), 
				rs.getInt("fk_physician_id") ,rs.getDate("appointment_date"));
		
	}

	@Override
	public boolean validateUserExists(UUID userId) {
		return jdbc.queryForObject("Select count(*) from person where id = ? ", new Object[]{userId},(rs,i) -> rs.getInt(1)) != 0;
	}

	@Override
	public boolean validatePhysicianExists(Integer physicianId) {
		return jdbc.queryForObject("Select count(*) from physician where physician_id = ? ", new Object[]{physicianId},(rs,i) -> rs.getInt(1)) != 0;
	}

	@Override
	public List<UserAppointment> getAppointmentsbyUser(UUID userId) {
		return jdbc.query(GET_APPOINTMETNS_BY_USER,new Object[] {userId}, mapAppointmentfromDB());
	}

}
