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
import pl.edu.agh.iisg.to2.model.IEmployee;
import pl.edu.agh.iisg.to2.model.ITeam;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class ProjectController {

	private ObservableList<ProjectMock> projects;
	private ObservableList<ITeam> teams;
	private ObservableList<IEmployee> employees;
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
		this.projects = FXCollections.observableArrayList();
		for(int i = 0; i < 15; i++) {
			ProjectMock p = new ProjectMock("projekt tmp id:"+ i, LocalDate.now(), LocalDate.of(2010, 3, i+5), "" + i + "MockTeam", "Pracownicy", new BigDecimal(i));
			this.projects.add(p);	
		}
	}
	
	public void initRootLayout() {
		try {
			this.primaryStage.setTitle("Project");

			/*for (ProjectMock tmp: this.getProjects()){
				System.out.println(tmp.getId());
			}*/
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ListView.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();
			
			ListController controller = loader.getController();
			generateMockData();
			controller.setData(projects);
			controller.setProjController(this);
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
