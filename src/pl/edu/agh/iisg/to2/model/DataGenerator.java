package pl.edu.agh.iisg.to2.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.collections.ObservableList;
import pl.edu.agh.iisg.to2.model.IEmployee;
import pl.edu.agh.iisg.to2.model.EmployeeMock;

public class DataGenerator {
	

	public static List<IEmployee> generateEmployees(int size){
		final int SIZE = 24;
		
		String[] names = {
			"Kelsey",
			"Alexandra",
			"Abraham",
			"Rajah",
			"Jasper",
			"Daquan",
			"Nicole",
			"Hermione",
			"Jessamine",
			"Genevieve",
			"Neville",
			"Christine",
			"Skyler",
			"Miriam",
			"Carter",
			"Malachi",
			"Maryam",
			"Rebecca",
			"Kaitlin",
			"Wesley",
			"Dalton",
			"Marny",
			"Guinevere",
			"Veda"   
		};
		
		String[] surnames = {
			"Tillman",
			"Wall",
			"Barron",
			"Bullock",
			"Blackwell",
			"Henderson",
			"Conrad",
			"Benson",
			"Stafford",
			"Hamilton",
			"Peck",
			"Wagner",
			"Alvarado",
			"Reilly",
			"Bruce",
			"Swanson",
			"Barry",
			"Ellis",
			"Moon",
			"Kerrez",
			"Jimenas",
			"Salin",
			"Owen",
			"Kim"	
		};
		
		String[] pesel = {
			"185837",
			"667564",
			"363211",
			"613245",
			"284511",
			"626035",
			"820177",
			"183749",
			"410018",
			"282826",
			"225311",
			"788699",
			"832468",
			"461851",
			"193417",
			"210369",
			"907926",
			"814523",
			"548671",
			"855066",
			"744339",
			"726453",
			"691822",
			"521568" 
		};
		
		List<IEmployee> workers = new ArrayList<>();
		
		
		for (int i = 0; i < size && i < SIZE; i++) {
			Random generator = new Random(); 
			int i1 = generator.nextInt(24);
			int i2 = generator.nextInt(24);
			workers.add(new EmployeeMock(Integer.toString(i), names[i1], surnames[i2], pesel[i1], new BigDecimal(1000)));
		}
		
		return workers;
		
		
	}
	
	public static List<ITeam> generateTeams(int size){
		
		String[] id = {
				"1858",
				"667",
				"3611",
				"6245",
				"84511",
				"6235",
				"82077",
				"189",
				"41001",
				"22826",
				"2311",
				"7699",
				"3468",
				"46851",
				"19317",
				"2139",
				"90726",
				"814523",
				"54861",
				"85066",
				"74433",
				"26453",
				"69182",
				"51568" 
			};
		
		String[] names = {
				"Beauty and Beast",
				"Notre Damme De Paris",
				"Mozart Opera Rock",
				"PSY",
				"Hotel Transilvania",
				"Bold & Beatuiful",
				"Klan",
				"Criminal Minds",
				"Bora Bora",
				"Fiji",
				"Aloha!",
				"GTA: San Andreas",
				"Amadeus",
				"Monsters inc.",
				"Avatar",
				"Book of Life",
				"Belle",
				"DADDY",
				"Aliexpress",
				"Blah Blah Blah",
				"Galapagos",
				"Anaconda & team",
				"Hells' Kitchen",
				"THE END!"	
			};
		
		
		List<ITeam> teams = new ArrayList<>();
		final int SIZE = 12;
		for (int i = 0; i < size && i < SIZE; i++) {
			Random generator = new Random(); 
			int i2 = generator.nextInt(24);
			teams.add(new TeamMock(id[i], generateEmployees(2), new BigDecimal(1000), names[i2]));	
		}
		return teams;
	}
	
	public static ITeam generateTeam(){
		
		String[] id = {
				"1858",
				"667",
				"3611",
				"6245",
				"84511",
				"6235",
				"82077",
				"189",
				"41001",
				"22826",
				"2311",
				"7699",
				"3468",
				"46851",
				"19317",
				"2139",
				"90726",
				"814523",
				"54861",
				"85066",
				"74433",
				"26453",
				"69182",
				"51568" 
			};
		
		String[] names = {
				"Beauty and Beast",
				"Notre Damme De Paris",
				"Mozart Opera Rock",
				"PSY",
				"Hotel Transilvania",
				"Bold & Beatuiful",
				"Klan",
				"Criminal Minds",
				"Bora Bora",
				"Fiji",
				"Aloha!",
				"GTA: San Andreas",
				"Amadeus",
				"Monsters inc.",
				"Avatar",
				"Book of Life",
				"Belle",
				"DADDY",
				"Aliexpress",
				"Blah Blah Blah",
				"Galapagos",
				"Anaconda & team",
				"Hells' Kitchen",
				"THE END!"	
			};
		Random generator = new Random(); 
		int i = generator.nextInt(24);
		int i2 = generator.nextInt(24);
		ITeam team = new TeamMock(id[i],generateEmployees(3), new BigDecimal(1000), names[i2]);	
		
		return team;
	}
	
	
	public static List<ProjectMock> generateProjects(List<IEmployee> employees, List<ITeam> teams, int numberOfEmployees, int numberOfTeams, int numberOfProjects){
		List<ProjectMock> projects = new ArrayList<>();
		Random generator = new Random(); 
		int iT = generator.nextInt(numberOfTeams);
		int iE = generator.nextInt(numberOfEmployees);
		long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
		long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		long randomDay2 = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		LocalDate randomDate2 = LocalDate.ofEpochDay(randomDay2);
		for(int i = 0; i < numberOfProjects; i++) {
			ProjectMock p = new ProjectMock(randomDate, randomDate2, teams.get(iT), employees.get(iE), new BigDecimal(10000));
			projects.add(p);
			System.out.println();
		}
		return projects;
	}
	
	public static ProjectMock generateProject(List<IEmployee> employees, List<ITeam> teams, int numberOfEmployees, int numberOfTeams){;
		Random generator = new Random(); 
		int iT = generator.nextInt(numberOfTeams);
		int iE = generator.nextInt(numberOfEmployees);
		long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
		long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		long randomDay2 = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		LocalDate randomDate2 = LocalDate.ofEpochDay(randomDay2);
		ProjectMock p = new ProjectMock(randomDate, randomDate2, teams.get(iT), employees.get(iE), new BigDecimal(generator.nextInt(20000)));

		return p;
	}
	
	public static ProjectMock generateProjectWithMultipleTeamsEmployees(List<IEmployee> employees, List<ITeam> teams, int numberOfEmployees, int numberOfTeams){
		Random generator = new Random(); 
		long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
		long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		long randomDay2 = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		LocalDate randomDate2 = LocalDate.ofEpochDay(randomDay2);
		ProjectMock p = new ProjectMock(randomDate, randomDate2, teams, employees, new BigDecimal(10000));

		return p;
	}
}
