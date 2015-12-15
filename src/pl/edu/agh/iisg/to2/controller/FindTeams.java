package pl.edu.agh.iisg.to2.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.iisg.to2.model.ITeam;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class FindTeams {

	
	public static ITeam findWithID(ProjectMock p, String id, ObservableList<ITeam> teams){
		for (ITeam t: teams){
			if (t.getId() == id){
				return t;
			}
		}
		return null;
	}
	
	public static List<ITeam> findTeamsWithID(ProjectMock p, String id, ObservableList<ITeam> teams){
		List<ITeam> tmp = new ArrayList<>();	
		for (ITeam t: teams){
			if (t.getId() == id){
				tmp.add(t);
			}
		}
		return tmp;
	}
	
	public static List<ITeam> findTeamsWithName(ProjectMock p, String name, ObservableList<ITeam> teams){
		List<ITeam> tmp = new ArrayList<>();	
		for (ITeam t: teams){
			if (t.getNameOfTeam() == name){
				tmp.add(t);
			}
		}
		return tmp;
	}
	
	public static ObservableList<ITeam> setTeamsFromString(ProjectMock p, String s1,  ObservableList<ITeam> t) {
		ObservableList<ITeam> te = FXCollections.observableArrayList();
		String[] arr = s1.split(" ");
		for ( String ss : arr) {
			//System.out.println("tworze na podstawie stringa team:"+ ss);
			te.add(FindTeams.findWithID(p, ss, t));
		}
		return te;
	}

}
