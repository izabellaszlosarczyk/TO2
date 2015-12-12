package pl.edu.agh.iisg.to2.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.iisg.to2.model.IEmployee;

public class FindEmployees {

	
	public static IEmployee findWithID(String id,  ObservableList<IEmployee> employees){
		for (IEmployee e: employees){
			if (e.getId() == id){
				return e;
			}
		}
		return null;
	}
	
	public static ObservableList<IEmployee> findWithNameAndSureName(String name, String surname, ObservableList<IEmployee> employees){
		 ObservableList<IEmployee> tmp = FXCollections.observableArrayList();
		 for (IEmployee e: employees){
			if (e.getFirstName() == name && e.getLastName() == surname){
				tmp.add(e);
			}
		}
		return tmp;
	}
}
