package pl.edu.agh.iisg.to2.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamMock implements ITeam {
	private String id;
	private List<IEmployee> employees;
	private BigDecimal costOfTeam;
	private String nameOfTeam;

	public TeamMock() {
		this.employees = new ArrayList<>();	
	}
	
	public TeamMock(String id) {
		this.id = id;
		this.employees = new ArrayList<>();	
	}

	public TeamMock(String id, List<IEmployee> employees, BigDecimal costofteam, String nameOfTeam) {
		this.id = id;
		this.employees = employees;
		this.costOfTeam = costofteam;
		this.nameOfTeam = nameOfTeam;
	}
	
	@Override
	public BigDecimal getCostOfTeam() {
		return costOfTeam;
	}

	@Override
	public List<IEmployee> getFullMemberList() {
		return employees;
	}

	@Override
	public String getId() {
		return new Integer(id).toString();
	}

	public List<IEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<IEmployee> employees) {
		this.employees = employees;
	}

	public String getNameOfTeam() {
		return nameOfTeam;
	}

	public void setNameOfTeam(String nameOfTeam) {
		this.nameOfTeam = nameOfTeam;
	}

	public void setCostOfTeam(BigDecimal costOfTeam) {
		this.costOfTeam = costOfTeam;
	}

}
