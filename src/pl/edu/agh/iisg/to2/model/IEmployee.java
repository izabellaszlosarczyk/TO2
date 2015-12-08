package pl.edu.agh.iisg.to2.model;

import java.math.BigDecimal;

public interface IEmployee {
	
	String getId();

	void setId(BigDecimal id);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getPosition();

	void setPosition(String position);

	int getSalary();

	void setSalary(int salary);

	String getPhone();

	void setPhone(String phone);

	String getDate();

	void setDate(String date);

	String getEmail();

	void setEmail(String email);

}