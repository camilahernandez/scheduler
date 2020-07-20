package com.camila.scheduler.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class UserAppointment {

	private UUID patientId;
	private Integer physicianId;
	private Date date;
	 
	public UserAppointment(@JsonProperty("patientId") UUID patientId, @JsonProperty("physicianId") Integer physicianId,@JsonProperty("appointDate") Date date) {
		this.patientId = patientId;
		this.physicianId = physicianId;
		this.date = date;
	}
	
	 public UUID getPatientId() {
			return patientId;
		}
		public void setPatientId(UUID patientId) {
			this.patientId = patientId;
		}
		public Integer getPhysicianId() {
			return physicianId;
		}
		public void setPhysicianId(Integer physicianId) {
			this.physicianId = physicianId;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
	
	
}
