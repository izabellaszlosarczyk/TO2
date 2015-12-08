package pl.edu.agh.iisg.to2.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	private ProjectController controller;

	@FXML private TableView<ProjectMock> projectTable;
	//@FXML private TableColumn<ProjectMock, String> idColumn;
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
	
	private ProjectController projectController;
	
	
	@FXML
	private void initialize() {
		
		projectTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//idColumn.setCellValueFactory(dataValue -> dataValue.getValue().getId());
		budgetColumn.setCellValueFactory(value -> value.getValue().getBudget());
		deadlineColumn.setCellValueFactory(dataValue -> dataValue.getValue().getDeadline());
		startDateColumn.setCellValueFactory(dataValue -> dataValue.getValue().getStartdate());
		teamsColumn.setCellValueFactory(value -> value.getValue().getStringTeams());
		employeesColumn.setCellValueFactory(value -> value.getValue().getStringEmployees());
		
		deleteButton.disableProperty().bind(Bindings.isEmpty(projectTable.getSelectionModel().getSelectedItems()));
	}
	
	private void initializeWithArguments() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		converter = new LocalDateStringConverter(formatter, formatter);
		
		ObjectProperty<LocalDate> deadline = new SimpleObjectProperty<LocalDate>(converter.fromString(paramDeadline.getText()));
		ObjectProperty<LocalDate> startdate = new SimpleObjectProperty<LocalDate>(converter.fromString(paramStartDate.getText()));
		String id = new String(paramId.getText());
		
		ObservableList<ProjectMock> projectsWithParam = FXCollections.observableArrayList();
	}

	@FXML
	private void handleDeleteAction(ActionEvent event) {
		projectTable.getItems().removeAll(projectTable.getSelectionModel().getSelectedItems());
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
		Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("view/AddView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add project");
            stage.setScene(new Scene(root1));  
            AddController addController = (AddController) fxmlLoader.getController();
            addController.setController(this.projectController);

            stage.showAndWait();
            System.out.println("Refreshing...");
            projectTable.refresh(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@FXML
	private void handleEditAction(ActionEvent event) {
		Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("view/EditView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit project");
            stage.setScene(new Scene(root1)); 
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void setData(ObservableList<ProjectMock> p) {
		projectTable.getItems().setAll(p);
	}
	
	public void setAppController(ProjectController controller) {
		this.controller = controller;
	}

	public ProjectController getProjectController() {
		return projectController;
	}

	public void setProjectController(ProjectController projectController) {
		this.projectController = projectController;
	}

	
}
