package pl.edu.agh.iisg.to2.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectMock implements IProject {

	private String id;
	private ObjectProperty<LocalDate> deadline;
	private ObjectProperty<LocalDate> startdate;
	private ObservableList<ITeam> teams;
	private ObservableList<IEmployee> employees;
	private ObjectProperty<BigDecimal> budget;

	public ProjectMock() {
		this.id = UUID.randomUUID().toString();
	}
	 
	public ProjectMock(LocalDate deadline, LocalDate startdate, String teams, String employees, BigDecimal budget) {
		this.id = UUID.randomUUID().toString();
		this.deadline = new SimpleObjectProperty<>(deadline);
		this.startdate = new SimpleObjectProperty<>(startdate);
		this.teams = FXCollections.observableArrayList();
		this.employees = FXCollections.observableArrayList();
		
		setTeamsFromString(teams);
		this.employees = FXCollections.observableArrayList(this.getTeams().get(0).getFullMemberList());
		this.budget = new SimpleObjectProperty<BigDecimal>(budget);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ObjectProperty<LocalDate> getDeadline() {
		return deadline;
	}

	public void setDeadline(ObjectProperty<LocalDate> deadline) {
		this.deadline = deadline;
	}

	
	public ObjectProperty<LocalDate> getStartdate() {
		return startdate;
	}

	public void setStartdate(ObjectProperty<LocalDate> startdate) {
		this.startdate = startdate;
	}

	public ObservableList<ITeam> getTeams() {
		return teams;
	}

	public void setTeams(ObservableList<ITeam> teams) {
		this.teams = teams;
	}

	public ObservableList<IEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(ObservableList<IEmployee> employees) {
		this.employees = employees;
	}
	
	public StringProperty getStringTeams(){
		StringProperty s = new SimpleStringProperty("");
		ObservableList<ITeam> t = FXCollections.observableArrayList();
		t = getTeams();
		for (ITeam tmp: t){
			s.setValue(s.getValue() + tmp.getId() + ";");
		}
		return s;
	}
	
	public StringProperty getStringEmployees(){
		StringProperty s = new SimpleStringProperty("");
		ObservableList<IEmployee> e = FXCollections.observableArrayList();
		e = getEmployees();
		for (IEmployee tmp: e){
			s.setValue(s.getValue() + tmp.getId() + ";");
		}
		return s;
	}

	public ObjectProperty<BigDecimal> getBudget() {
		return budget;
	}

	public void setBudget(ObjectProperty<BigDecimal> budget) {
		this.budget = budget;
	}
	
	
	public void setEmployeesFromString(String s1){
		ObservableList<IEmployee> s = FXCollections.observableArrayList();
		if(this.employees == null) {
			this.employees = FXCollections.observableArrayList();
		}
		this.employees.addAll(s);
	}

	public void setTeamsFromString(String text) {
		int id = 0;
		if(text != null && text.length() > 0) {
			id = text.charAt(0);
		}
		ITeam s = new TeamMock(id);
		if(this.teams == null) {
			this.teams = FXCollections.observableArrayList();
		}
		this.teams.addAll(s);
	}
}
