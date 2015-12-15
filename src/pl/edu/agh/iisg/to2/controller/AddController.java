package pl.edu.agh.iisg.to2.controller;

import static java.lang.Math.toIntExact;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import pl.edu.agh.iisg.to2.Main;
import pl.edu.agh.iisg.to2.model.IEmployee;
import pl.edu.agh.iisg.to2.model.ITeam;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class AddController {

	private ProjectController controller;
	

	@FXML private DatePicker deadlineDatePicker;
	@FXML private DatePicker startdateDatePicker;
	@FXML private TextField employeesTextField;
	@FXML private TextField teamsTextField;
	@FXML private TextField budgetTextField;
	
	
	@FXML private Button addEmployeesButton;
	@FXML private Button addTeamsButton;
	@FXML private Button cancelButton;
	@FXML private Button okButton;
	@FXML private Button calculateBudget;
	
	@FXML private Label errorDate;
	@FXML private Label errorTeams;
	@FXML private Label errorEmployees;
	@FXML private Label errorBudget;

	private Stage dialogStage;
	private ProjectMock project;
	private ObservableList<ProjectMock> projectsTmp;
	private ObservableList<ITeam> teams;
	private ObservableList<IEmployee> employees;
	private boolean approved;
	
	
	@FXML
	public void initialize() {
		this.project = new ProjectMock();
		errorDate.setVisible(false);
		errorTeams.setVisible(false);
		errorEmployees.setVisible(false);
		errorBudget.setVisible(false);
	}
	
	public ProjectController getController() {
		return controller;
	}

	public void setController(ProjectController controller) {
		this.controller = controller;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setData(ObservableList<ProjectMock> projects, ObservableList<IEmployee> e, ObservableList<ITeam> t) {
		this.projectsTmp = projects;
		this.teams = t;
		this.employees = e;
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {
		updateModel();
		if (isInputValid()){
			projectsTmp.add(project);
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		}else{
			
		}
	}
	
	@FXML
	private void handleCalculateAction(ActionEvent event) {
		int budget = project.calculateBudget();
		budgetTextField.setText(Integer.toString(budget));	
	}

	@FXML
	private void handleCancelAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void handleAddEmployeesAction(ActionEvent event) {
		dialogStage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("AddEmployee.fxml"));
        // dokonczyc
	}
	
	@FXML
	private void handleAddTeamsAction(ActionEvent event) {
		dialogStage.close();
	}

	private boolean isInputValid() {
		if (project.getDeadline() == null || project.getDeadline().getValue() == null || project.getStartdate() == null || project.getStartdate().getValue() == null) {
			errorDate.setText("ERROR! Deadline or startdate is empty!");
			errorDate.setVisible(true);
			return false;
		}
		if (!(project.getStartdate().getValue()).isBefore(project.getDeadline().getValue())) {
			//ustaw na czewono
			errorDate.setVisible(true);
			return false;
		}
		//if (calculateBudget() != project.getBudget().getValue().intValueExact());
		
		// sprawdzenie budzetu i pracownikow/ zespolow
		return true;
	}

	
	private void updateModel() {
		
		if (deadlineDatePicker.getValue() != null){ 
			project.setDeadline(new SimpleObjectProperty<LocalDate>(deadlineDatePicker.getValue()));
			System.out.println("data pobrana2:"+ project.getDeadline().getValue().toString() );
			
		}
		if (startdateDatePicker.getValue() != null){
			project.setStartdate(new SimpleObjectProperty<LocalDate>(startdateDatePicker.getValue()));
			System.out.println("data pobrana COCOOCCO:"+ project.getStartdate().getValue().toString());
		}
		
		if (!(budgetTextField.getText().isEmpty())){
			DecimalFormat decimalFormatter = new DecimalFormat();
			decimalFormatter.setParseBigDecimal(true);
			try {
				SimpleObjectProperty<BigDecimal> bTmp =  new SimpleObjectProperty<BigDecimal>(((BigDecimal) decimalFormatter.parse(budgetTextField.getText())));
				project.setBudget(bTmp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!(teamsTextField.getText().isEmpty())){
			ObservableList<ITeam> ttmp = FXCollections.observableArrayList();
			ttmp = FindTeams.setTeamsFromString(project, teamsTextField.getText(), teams);
			
			project.setTeams(ttmp);
		}
		if (!(employeesTextField.getText().isEmpty())){
			ObservableList<IEmployee> etmp = FXCollections.observableArrayList();
			etmp = FindEmployees.setEmployeesFromString(project, employeesTextField.getText(), employees);
		}
		 
	}
	
	private int equalBudget(int budget){
		if (project.calculateBudget() == budget)
			return 1;
			else
				return 0;
	}
	
	public static void initAddEmployeesDialog() {
		// TODO: implement
	}
	
	public static void initAddTeamsDialog() {
		// TODO: implement
	}
}
