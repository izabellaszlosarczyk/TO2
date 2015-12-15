package pl.edu.agh.iisg.to2.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.iisg.to2.model.IEmployee;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class FindEmployees {


	public static ObservableList<IEmployee> findWithNameAndSureName(ProjectMock p, String name, String surname, ObservableList<IEmployee> e){
		ObservableList<IEmployee> empl = FXCollections.observableArrayList();
		empl.addAll(e);
		for (int i = 0; i < empl.size(); i++){
			if (!(name.equals(empl.get(i).getFirstName()) && surname.equals(empl.get(i).getLastName()) )){
				empl.remove(i) ;
				i--;
			}
		}
			//System.out.println("tworze na podstawie stringa team:"+ ss);

		return empl;
	}
	
	public static ObservableList<IEmployee> setEmployeesFromString(ProjectMock p, String s1, ObservableList<IEmployee> e){
		ObservableList<IEmployee> empl = FXCollections.observableArrayList();
		empl.addAll(e);
		for (int i = 0; i < empl.size(); i++){
			System.out.println(s1);
			System.out.println(empl.get(i).getId()+"moj string");
			if (!(s1.toLowerCase().contains(empl.get(i).getId().toLowerCase()))){
				empl.remove(i) ;
				i--;
			}
			//System.out.println("tworze na podstawie stringa team:"+ ss);
		}
		return empl;
	}
	
	public static StringProperty getStringEmployees( ProjectMock p ){
		StringProperty s = new SimpleStringProperty("");
		ObservableList<IEmployee> e = FXCollections.observableArrayList();
		e.addAll(p.getEmployees());
		if (e != null){
			for (IEmployee tmp: e){
				//System.out.println("ustawiam wartosc stringa Employee:"+ tmp.getId());
				s.setValue(s.getValue()+ " " + tmp.getId());
			}
		}
		else s.setValue("0");
	
		return s;
	}
	
	public static StringProperty getStringEmployees(ObservableList<IEmployee> etmp){
		StringProperty s = new SimpleStringProperty("");
		if (etmp != null){
			for (IEmployee tmp: etmp){
				//System.out.println("ustawiam wartosc stringa Employee:"+ tmp.getId());
				s.setValue(s.getValue()+ " " + tmp.getId());
			}
		}
		else s.setValue("0");
	
		return s;
	}
}







