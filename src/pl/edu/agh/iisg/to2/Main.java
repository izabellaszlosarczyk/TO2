package pl.edu.agh.iisg.to2;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to2.controller.ProjectController;
import pl.edu.agh.iisg.to2.model.MySQLAccess;
//import pl.edu.agh.iisg.to.javafx.cw4.controller.AccountAppController;

public class Main extends Application {

	private Stage primaryStage;
	
	private ProjectController presenter;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("My first JavaFX app");

		this.presenter = new ProjectController(primaryStage);
		this.presenter.initRootLayout();
	}

	public static void main(String[] args) {
		
		System.out.println("Setup SQL...");
		MySQLAccess dao = new MySQLAccess();
	    try {
			dao.readDataBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		
		System.out.println("Running...");
		launch(args);
		
	}
	
}