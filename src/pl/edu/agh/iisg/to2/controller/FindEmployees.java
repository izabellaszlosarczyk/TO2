package pl.edu.agh.iisg.to2.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.iisg.to2.model.IEmployee;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class FindEmployees {

	
	public static IEmployee findWithID(ProjectMock p, String id,  ObservableList<IEmployee> employees){
		for (IEmployee e: employees){
			if (e.getId() == id){
				return e;
			}
		}
		return null;
	}
	
	public static ObservableList<IEmployee> findWithNameAndSureName(ProjectMock p, String name, String surname, ObservableList<IEmployee> employees){
		 ObservableList<IEmployee> tmp = FXCollections.observableArrayList();
		 for (IEmployee e: employees){
			if (e.getFirstName() == name && e.getLastName() == surname){
				tmp.add(e);
			}
		}
		return tmp;
	}
	
	public static ObservableList<IEmployee> setEmployeesFromString(ProjectMock p, String s1, ObservableList<IEmployee> e){
		ObservableList<IEmployee> empl = FXCollections.observableArrayList();
		String[] arr = s1.split(" ");
		for ( String ss : arr) {
			//System.out.println("tworze na podstawie stringa employee:"+ ss);
			empl.add(FindEmployees.findWithID(p, ss, e));
		}
		return empl;
	}
}
