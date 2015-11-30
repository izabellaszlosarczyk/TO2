package com.agh.to2.model;

import java.util.Date;
import java.util.UUID;

public class Project implements IProject {

	private String id;
	private Date deadline;
	private Date startdate;
	private ITeam team;
	
	public Project(){
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public ITeam getTeam() {
		return team;
	}

	public void setTeam(ITeam team) {
		this.team = team;
	}
}
