package pl.edu.agh.iisg.to2.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.converter.LocalDateStringConverter;
import pl.edu.agh.iisg.to2.Main;
import pl.edu.agh.iisg.to2.model.IEmployee;
import pl.edu.agh.iisg.to2.model.ITeam;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class ListController {


	@FXML private TableView<ProjectMock> projectTable;
	@FXML private TableColumn<ProjectMock, LocalDate> deadlineColumn;
	@FXML private TableColumn<ProjectMock, LocalDate> startDateColumn;
	@FXML private TableColumn<ProjectMock, String> employeesColumn;
	@FXML private TableColumn<ProjectMock, String> teamsColumn;
	@FXML private TableColumn<ProjectMock, BigDecimal> budgetColumn;
	
	@FXML private Button deleteButton;
	@FXML private Button cancelButton;
	@FXML private Button editButton;
	@FXML private Button findButton;
	@FXML private Button addButton;
	
	@FXML private TextField paramId;
	@FXML private TextField paramDeadline;
	@FXML private TextField paramStartDate;
	@FXML private TextField paramEmployees;
	@FXML private TextField paramTeams;
	
	private LocalDateStringConverter converter;
	private ProjectController projController; 
	private ObservableList<ProjectMock> projects;

	
	@FXML
	private void initialize() {
		
		projectTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		budgetColumn.setCellValueFactory(value -> value.getValue().getBudget());
		deadlineColumn.setCellValueFactory(dataValue -> dataValue.getValue().getDeadline());
		startDateColumn.setCellValueFactory(dataValue -> dataValue.getValue().getStartdate());
		teamsColumn.setCellValueFactory(value -> value.getValue().getStringTeams());
		employeesColumn.setCellValueFactory(value -> value.getValue().getStringEmployees());
		deleteButton.disableProperty().bind(Bindings.isEmpty(projectTable.getSelectionModel().getSelectedItems()));
		editButton.disableProperty().bind(Bindings.isEmpty(projectTable.getSelectionModel().getSelectedItems()));
		 
	}
	
	private void initializeWithArguments() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		converter = new LocalDateStringConverter(formatter, formatter); 
		
		ObjectProperty<LocalDate> deadline = new SimpleObjectProperty<LocalDate>(converter.fromString(paramDeadline.getText()));
		ObjectProperty<LocalDate> startdate = new SimpleObjectProperty<LocalDate>(converter.fromString(paramStartDate.getText()));
		String id = new String(paramId.getText());
		String employees = new String(paramId.getText());
		String teams = new String(paramId.getText());
		
		/*ObservableList<ProjectMock> tmp1 = FXCollections.observableArrayList();
		tmp1.addAll(controller.getProjects());
		for (ProjectMock m: tmp1){
			 TODO : implementation 
		}*/
	}
	

	@FXML
	private void handleDeleteAction(ActionEvent event) {
		projectTable.getItems().removeAll(projectTable.getSelectionModel().getSelectedItems());
		//TODO: implement 
	}
	
	@FXML
	private void handleCancelAction(ActionEvent event) {
		Stage stage = (Stage)cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void handleFindAction(ActionEvent event) {
		initializeWithArguments();
	}

	@FXML
	private void handleAddAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoaderAdd = new FXMLLoader();
            fxmlLoaderAdd.setLocation(Main.class.getResource("view/AddView.fxml"));
            Parent root1 = (Parent) fxmlLoaderAdd.load();
            
            Stage stageAdd = new Stage();
            stageAdd.initModality(Modality.APPLICATION_MODAL);
            stageAdd.setTitle("Add project");
            stageAdd.setScene(new Scene(root1));  

            AddController controllerAdd = fxmlLoaderAdd.getController();
			controllerAdd.setDialogStage(stageAdd);
			controllerAdd.setData(projectTable.getItems());
			
            stageAdd.showAndWait();
            System.out.println("Refreshing...");
            projectTable.refresh(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@FXML
	private void handleEditAction(ActionEvent event) {
		ProjectMock p = new ProjectMock();
		p =  projectTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoaderEdit = new FXMLLoader();
            fxmlLoaderEdit.setLocation(Main.class.getResource("view/EditView.fxml"));
            Parent root1 = (Parent) fxmlLoaderEdit.load();
            Stage stageEdit = new Stage();
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.setTitle("Edit project");
            stageEdit.setScene(new Scene(root1)); 
            
            EditController controllerEdit = fxmlLoaderEdit.getController();
            controllerEdit.setDialogStage(stageEdit);
            controllerEdit.setData(p);
            
            stageEdit.showAndWait();
            projectTable.refresh(); 
            
            System.out.println("Refreshing...");
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
	}
	
	public void setData(ObservableList<ProjectMock> p) {
		projectTable.getItems().setAll(p);

	}
	
	public ProjectController getProjController() {
		return projController;
	}

	public void setProjController(ProjectController projController) {
		this.projController = projController;
	}

	
	
}
