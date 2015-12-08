package pl.edu.agh.iisg.to2.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamMock implements ITeam {
	private int id;

	public TeamMock(int id) {
		this.id = id;
	}

	@Override
	public BigDecimal getCostOfTeam() {
		BigDecimal bigDecimal = new BigDecimal(1000);
		return bigDecimal;
	}

	@Override
	public List<IEmployee> getFullMemberList() {
		List<IEmployee> list = new ArrayList();
		Random generator = new Random();
		
		if(generator.nextBoolean()) {
			list.add(new EmployeeMock("Stefan", "Nowak"));
		}
		if(generator.nextBoolean()) {
			list.add(new EmployeeMock("Zanklod", "Wamdam"));
		}
		if(generator.nextBoolean()) {
			list.add(new EmployeeMock("Bruce", "Lee"));
		}
		if(generator.nextBoolean()) {
			list.add(new EmployeeMock("Chuck", "Norris"));
		}
		if(generator.nextBoolean()) {
			list.add(new EmployeeMock("Mr", "Bean"));
		}
		if(generator.nextBoolean()) {
			list.add(new EmployeeMock("Piotrus", "Pan"));
		}
		if(generator.nextBoolean()) {
			list.add(new EmployeeMock("Dzejms", "Bond"));
		}
		if(generator.nextBoolean()) {
			list.add(new EmployeeMock("SkonczylySieImiona", "Nazwisko"));
		}
		return list;
	}

	@Override
	public String getId() {
		return new Integer(id).toString();
	}

}
