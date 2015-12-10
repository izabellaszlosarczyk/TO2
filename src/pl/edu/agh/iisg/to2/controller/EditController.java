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
import javafx.scene.control.DatePicker;
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

	@FXML private DatePicker deadlineDatePicker;
	@FXML private DatePicker startdateDatePicker;
	@FXML private TextField employeesTextField;
	@FXML private TextField teamsTextField;
	@FXML private TextField budgetTextField;
	
	@FXML private Button editEmployeesButton;
	@FXML private Button editTeamsButton;
	@FXML private Button cancelButton;
	@FXML private Button okButton;
	@FXML private Label error;
	

	private Stage dialogStage;
	private ProjectMock projectEdit;
	private boolean approved;

	private LocalDateStringConverter converter;
	
	
	@FXML
	public void initialize() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		setConverter(new LocalDateStringConverter(formatter, formatter));

	}

	public void setData(ProjectMock project) {
		this.projectEdit = project;
		updateControls();
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
			updateModel();
			approved = true;
		}
		if (isInputValid()) {
			updateModel();
			dialogStage.close();
			//sprawdz
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
		//LocalDate deadline = projectEdit.getDeadline().getValue();
		//LocalDate startdate = projectEdit.getStartdate().getValue();
		//if (deadline == null || startdate == null) return false;
		//if (startdate.isBefore(deadline)) return false;
		//if (calculateBudget() != projectEdit.getBudget().getValue().intValueExact());
		// sprawdzenie budzetu i pracownikow/ zespolow
		
		return true;
	}

	private void updateModel() {
		
		projectEdit.setDeadline(new SimpleObjectProperty<LocalDate>(deadlineDatePicker.getValue()));
		projectEdit.setStartdate(new SimpleObjectProperty<LocalDate>(startdateDatePicker.getValue()));
		DecimalFormat decimalFormatter = new DecimalFormat();
		decimalFormatter.setParseBigDecimal(true);
		try {
			SimpleObjectProperty<BigDecimal> bTmp =  new SimpleObjectProperty<BigDecimal>(((BigDecimal) decimalFormatter.parse(budgetTextField.getText())));
			projectEdit.setBudget(bTmp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		projectEdit.setTeamsFromString(teamsTextField.getText());
		projectEdit.setEmployeesFromString(employeesTextField.getText());

	}

	private void updateControls() {
		deadlineDatePicker = new DatePicker(LocalDate.now());
		startdateDatePicker = new DatePicker(LocalDate.now());
		deadlineDatePicker.setValue(projectEdit.getDeadline().getValue());
		deadlineDatePicker.setValue(projectEdit.getStartdate().getValue());
//		employeesTextField.setText(projectEdit.getStringEmployees().toString());
//		teamsTextField.setText(projectEdit.getStringTeams().toString());
		budgetTextField.setText(projectEdit.getBudget().getValue().toString());	
	}
	
	private int calculateBudget(){
		long days = ChronoUnit.DAYS.between(getProjectEdit().getDeadline().getValue(), getProjectEdit().getStartdate().getValue());
		int daysInt = toIntExact(days);
		int cost = 0;
		for (IEmployee e: getProjectEdit().getEmployees() ) cost += e.getSalary();
		for (ITeam t: getProjectEdit().getTeams() ) cost += t.getCostOfTeam().intValueExact() ;
		// TODO: implement
		cost = cost*daysInt*8; 
		
		return 0;
	}
	

	public ProjectMock getProjectEdit() {
		return projectEdit;
	}

	public void setProjectEdit(ProjectMock projectEdit) {
		this.projectEdit = projectEdit;
	}
	
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public LocalDateStringConverter getConverter() {
		return converter;
	}

	public void setConverter(LocalDateStringConverter converter) {
		this.converter = converter;
	}
}
