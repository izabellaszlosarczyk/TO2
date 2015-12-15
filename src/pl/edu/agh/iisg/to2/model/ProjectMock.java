package pl.edu.agh.iisg.to2.model;

import static java.lang.Math.toIntExact;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.iisg.to2.controller.FindEmployees;
import pl.edu.agh.iisg.to2.controller.FindTeams;

public class ProjectMock implements IProject {

	private String id;
	private ObjectProperty<LocalDate> deadline;
	private ObjectProperty<LocalDate> startdate;
	private ObservableList<ITeam> teams;
	private ObservableList<IEmployee> employees;
	private ObjectProperty<BigDecimal> budget;

	public ProjectMock(){
		this.id = UUID.randomUUID().toString();
	}
	
	public ProjectMock(LocalDate deadline, LocalDate startdate, ITeam team, IEmployee employee, BigDecimal budget) {
		this.deadline = new SimpleObjectProperty<>(deadline);
		this.startdate = new SimpleObjectProperty<>(startdate);
		this.id = UUID.randomUUID().toString();
		this.teams = FXCollections.observableArrayList();
		this.teams.add(team);
		this.employees = FXCollections.observableArrayList();
		this.employees.add(employee);
		this.budget = new SimpleObjectProperty<BigDecimal>(budget);
	}
	 
	public ProjectMock(LocalDate deadline, LocalDate startdate, List<ITeam> teams, List<IEmployee> employees, BigDecimal budget) {
		this.id = UUID.randomUUID().toString();
		this.deadline = new SimpleObjectProperty<>(deadline);
		this.startdate = new SimpleObjectProperty<>(startdate);
		this.teams = FXCollections.observableArrayList(teams);
		this.employees = FXCollections.observableArrayList(employees);
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
	
	public ObjectProperty<BigDecimal> getBudget() {
		return budget;
	}

	public void setBudget(ObjectProperty<BigDecimal> budget) {
		this.budget = budget;
	}
	

	public void printProject(ProjectMock p){
		System.out.println(p.getId());
	}
	
	public String normalString(StringProperty p){
		String s = p.getValue();
		return s;
	}
	
	//dopisac czesc odpowiedzialna za pracownikow
	public int calculateBudget(){
		long days = ChronoUnit.DAYS.between(getDeadline().getValue(), getStartdate().getValue());
		int daysInt = toIntExact(days);
		int cost = 0;
		for (IEmployee e: getEmployees() ) cost += e.getSalary().intValueExact();
		for (ITeam t: getTeams() ) cost += t.getCostOfTeam().intValueExact();
		cost = cost*daysInt*8; 
		
		return 0;
	}
	
	public StringProperty getStringTeamsForProject(){
		StringProperty s = new SimpleStringProperty("");
		if (getTeams() != null){
			for (ITeam tmp:  getTeams()){
				//System.out.println("ustawiam wartosc stringa Team:"+ tmp.getId());
				s.setValue(s.getValue() + tmp.getId() + " ");
			}
		}
		else s.setValue("-1");
		return s;
	}
	
	public StringProperty getStringEmployeesForProject(){
		StringProperty s = new SimpleStringProperty("");
		if (getEmployees() != null){
			for (IEmployee tmp: getEmployees() ){
				//System.out.println("ustawiam wartosc stringa Employee:"+ tmp.getId());
				s.setValue(s.getValue() + tmp.getId() + " ");
			}
		}
		else s.setValue("-1");
		return s;
	}
}
