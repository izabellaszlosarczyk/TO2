package pl.edu.agh.iisg.to2.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import pl.edu.agh.iisg.to2.Main;
import pl.edu.agh.iisg.to2.model.IEmployee;
import pl.edu.agh.iisg.to2.model.ITeam;
import pl.edu.agh.iisg.to2.model.ProjectMock;
import static java.lang.Math.toIntExact;


public class EditController {

	private ProjectController controller;

	
	@FXML private TextField deadlineTextField;
	@FXML private TextField startdateTextField;
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
	private boolean approved;

	private LocalDateStringConverter converter;
	
	
	@FXML
	public void initialize() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		converter = new LocalDateStringConverter(formatter, formatter);

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setData(ProjectMock project) {
		this.project = project;
		updateControls();
	}

	public boolean isApproved() {
		return approved;
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {
		if (isInputValid()) {
			updateModel();
			approved = true;
			dialogStage.close();
		}
		if (!isApproved()) {
			error.setTextFill(Color.RED);
			error.setText("Error: You have blank spaces");
		} else {
			controller.addProjects(project);
		}
	}
	
	@FXML
	private void handleCancelAction(ActionEvent event) {
		dialogStage.close();
	}

	@FXML
	private void handleAddEmployeesAction(ActionEvent event) {
		dialogStage.close();
	}
	
	@FXML
	private void handleAddTeamsAction(ActionEvent event) {
		dialogStage.close();
	}

	private boolean isInputValid() {
		// TODO: implement
		return true;
	}

	private void updateModel() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		converter = new LocalDateStringConverter(formatter, formatter);
		
		StringProperty s1 =  new SimpleStringProperty(converter.fromString(teamsTextField.getText()), pattern); 
		
		project.setDeadline(new SimpleObjectProperty<LocalDate>(converter.fromString(deadlineTextField.getText())));
		project.setStartdate(new SimpleObjectProperty<LocalDate>(converter.fromString(startdateTextField.getText())));
		project.setTeamsFromString(teamsTextField.getText());
		project.setEmployeesFromString(employeesTextField.getText());

		DecimalFormat decimalFormatter = new DecimalFormat();
		decimalFormatter.setParseBigDecimal(true);

	}

	private void updateControls() {
		deadlineTextField.setText(project.getDeadline().toString());
		startdateTextField.setText(project.getStartdate().toString());
		employeesTextField.setText(project.getStringEmployees().toString());
		teamsTextField.setText(project.getStringTeams().toString());
		budgetTextField.setText(project.getBudget().toString());
		
	}
	
	private int calculateBudget(){
		long days = ChronoUnit.DAYS.between(project.getDeadline().getValue(), project.getStartdate().getValue());
		int daysInt = toIntExact(days);
		int cost = 0;
		for (IEmployee e: project.getEmployees() ) cost += e.getSalary();
		for (ITeam t: project.getTeams() ) cost += t.getCostOfTeam().intValueExact() ;
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
}
