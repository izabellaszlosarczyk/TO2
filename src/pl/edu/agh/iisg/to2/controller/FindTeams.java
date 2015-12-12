package pl.edu.agh.iisg.to2.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import pl.edu.agh.iisg.to2.model.ITeam;

public class FindTeams {

	
	public static ITeam findWithID(String id, ObservableList<ITeam> teams){
		for (ITeam t: teams){
			if (t.getId() == id){
				return t;
			}
		}
		return null;
	}
	
	public static List<ITeam> findTeamsWithID(String id, ObservableList<ITeam> teams){
		List<ITeam> tmp = new ArrayList<>();	
		for (ITeam t: teams){
			if (t.getId() == id){
				tmp.add(t);
			}
		}
		return tmp;
	}
	
	public static List<ITeam> findTeamsWithName(String name, ObservableList<ITeam> teams){
		List<ITeam> tmp = new ArrayList<>();	
		for (ITeam t: teams){
			if (t.getNameOfTeam() == name){
				tmp.add(t);
			}
		}
		return tmp;
	}

}
