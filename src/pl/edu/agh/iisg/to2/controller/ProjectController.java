package pl.edu.agh.iisg.to2.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to2.Main;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class ProjectController {

	private ObservableList<ProjectMock> projects;
	private Stage primaryStage;

	public ProjectController(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void addProjects(ProjectMock p){
		this.projects.add(p);
	}
	
	public ObservableList<ProjectMock> getProjects(){
		return projects;
	}

	public void setProjects(ObservableList<ProjectMock> p){
		this.projects = p;
	}
	
	public void generateMockData() {
		projects = FXCollections.observableArrayList();
		for(int i = 0; i < 10; i++) {
			ProjectMock p = new ProjectMock(LocalDate.now(), LocalDate.of(2010, 3, i+5), "" + i + "MockTeam", "Pracownicy", new BigDecimal(i));
			projects.add(p);	
		}
	}
	
	public void initRootLayout() {
		try {
			this.primaryStage.setTitle("Project");

			// load layout from FXML file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ListView.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();
			
			// set initial data into controller
			ListController controller = loader.getController();
			controller.setAppController(this);
			generateMockData();
			controller.setData(projects);
			controller.setProjectController(this);

			// add layout to a scene and show them all
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// don't do this in common apps
			e.printStackTrace();
		}

	}

}
