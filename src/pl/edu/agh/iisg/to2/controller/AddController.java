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
	@FXML private Label error;

	private Stage dialogStage;
	private ProjectMock project;
	private ObservableList<ProjectMock> projectsTmp;
	private ObservableList<ITeam> teams;
	private ObservableList<IEmployee> employees;
	private boolean approved;
	
	
	@FXML
	public void initialize() {
		this.project = new ProjectMock();
		deadlineDatePicker = new DatePicker(LocalDate.now());
		startdateDatePicker = new DatePicker(LocalDate.now());
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

	public boolean isApproved() {
		
		return approved;
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {
		approved = true;
		if (!isApproved()) {
			error.setTextFill(Color.RED);
			error.setText("Error: You have blank spaces");
		} else {
			projectsTmp.add(project);
		}
		if (isInputValid()) {
			updateModel();
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
			//sprawdz
		}
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
        /*AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Employee");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        //controller.setStage(dialogStage);
        //nameController.setPerson(person);
        dialogStage.show();
		 okno dla dodawania pracownikow*/
	}
	
	@FXML
	private void handleAddTeamsAction(ActionEvent event) {
		dialogStage.close();
	}

	private boolean isInputValid() {
		//LocalDate deadline = project.getDeadline().getValue();
		//LocalDate startdate = project.getStartdate().getValue();
		//if (deadline == null || startdate == null) return false;
		//if (startdate.isBefore(deadline)) return false;
		//if (calculateBudget() != project.getBudget().getValue().intValueExact());
		// sprawdzenie budzetu i pracownikow/ zespolow
		
		return true;
	}

	
	private void updateModel() {
		
		if (deadlineDatePicker.getValue() != null) project.setDeadline(new SimpleObjectProperty<LocalDate>(deadlineDatePicker.getValue()));
		if (startdateDatePicker.getValue() != null) project.setStartdate(new SimpleObjectProperty<LocalDate>(startdateDatePicker.getValue()));
		if (budgetTextField.getText() != null){
			DecimalFormat decimalFormatter = new DecimalFormat();
			decimalFormatter.setParseBigDecimal(true);
			try {
				SimpleObjectProperty<BigDecimal> bTmp =  new SimpleObjectProperty<BigDecimal>(((BigDecimal) decimalFormatter.parse(budgetTextField.getText())));
				project.setBudget(bTmp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (teamsTextField.getText() != null) project.setTeamsFromString(teamsTextField.getText(), teams);
		if (employeesTextField.getText() != null) project.setEmployeesFromString(employeesTextField.getText(), employees);
	}

	
	private int calculateBudget(){
		long days = ChronoUnit.DAYS.between(project.getDeadline().getValue(), project.getStartdate().getValue());
		int daysInt = toIntExact(days);
		int cost = 0;
		for (IEmployee e: project.getEmployees() ) cost += e.getSalary().intValueExact();
		for (ITeam t: project.getTeams() ) cost += t.getCostOfTeam().intValueExact();
		// TODO: implement
		cost = cost*daysInt*8; 
		
		return 0;
	}
	
	private int equalBudget(int budget){
		if (calculateBudget() == budget)
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
