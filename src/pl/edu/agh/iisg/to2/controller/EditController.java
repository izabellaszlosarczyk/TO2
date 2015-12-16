package pl.edu.agh.iisg.to2.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
	
	@FXML private Label errorId;
	@FXML private Label errorDate;
	@FXML private Label errorTeams;
	@FXML private Label errorEmployees;
	@FXML private Label errorBudget;

	private Stage dialogStage;
	private ProjectMock projectEdit;
	private boolean approved;

	private LocalDateStringConverter converter;
	private DateTimeFormatter formatter;
	
	private ObservableList<ITeam> teams;
	private ObservableList<IEmployee> employees;
	
	@FXML
	public void initialize() {
		String pattern = "yyyy-MM-dd";
		formatter = DateTimeFormatter.ofPattern(pattern);
		setConverter(new LocalDateStringConverter(formatter, formatter));
	}
	
	public String toStringDate(LocalDate date) {
        if (date != null) {
            return formatter.format(date);
        } else {
            return "";
        }
    }
	
	public LocalDate fromStringDate(String string) {
         if (string != null && !string.isEmpty()) {
             return LocalDate.parse(string, formatter);
         } else {
             return null;
         }
     }


	public void setData(ProjectMock project, ObservableList<IEmployee> e, ObservableList<ITeam> t) {
		this.projectEdit = project;
		this.teams = t;
		this.employees = e;
		updateControls();
	}

	public boolean isApproved() {
		return approved;
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {
		approved = true;
		if (!isApproved()) {
			//error.setTextFill(Color.RED);
			//error.setText("Error: You have blank spaces");
		} else {
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
		showPicker();
		dialogStage.close();
	}
	
	@FXML
	private void handleAddTeamsAction(ActionEvent event) {
		showPicker();
		dialogStage.close();
	}
	
	private void showPicker() {
        try {
            FXMLLoader fxmlLoaderAdd = new FXMLLoader();
            fxmlLoaderAdd.setLocation(Main.class.getResource("view/PickerView.fxml"));
            Parent root1 = (Parent) fxmlLoaderAdd.load();
            
            Stage stageAdd = new Stage();
            stageAdd.initModality(Modality.APPLICATION_MODAL);
            stageAdd.setTitle("Employee Picker");
            stageAdd.setScene(new Scene(root1));  

            PickerController picker = fxmlLoaderAdd.getController();
            picker.setDialogStage(stageAdd);
            //pickerController.setData(pickerTable.getItems(), employees, teams);
			
            stageAdd.showAndWait();
            System.out.println("Refreshing...");
            //projectTable.refresh(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private boolean isInputValid() {
		if (projectEdit.getDeadline() == null || projectEdit.getDeadline().getValue() == null || projectEdit.getStartdate() == null || projectEdit.getStartdate().getValue() == null) {
			errorDate.setText("ERROR! Deadline or startdate is empty!");
			errorDate.setVisible(true);
			return false;
		}
		if (!(projectEdit.getStartdate().getValue()).isBefore(projectEdit.getDeadline().getValue())) {

			errorDate.setVisible(true);
			return false;
		}
		DecimalFormat decimalFormatter = new DecimalFormat();
		decimalFormatter.setParseBigDecimal(true);
		if (projectEdit.getBudget() != null){
				if (projectEdit.calculateBudget() > projectEdit.getBudget().getValue().intValueExact() ){
					errorBudget.setTextFill(Color.RED);
					errorBudget.setText("ERROR! Not enough money for project!");
					errorBudget.setVisible(true);
					return false;
				}		
		}else{
			errorBudget.setText("ERROR! Budget musn't be empty!");
			errorBudget.setVisible(true);
			return false;
		}
		if (projectEdit.getEmployees() == null){
			errorEmployees.setText("ERROR! You have to choose employees!");
			errorEmployees.setVisible(true);
			return false;
		}
		if (projectEdit.getTeams() == null){
			errorTeams.setText("ERROR! You have to choose teams!");
			errorTeams.setVisible(true);
			return false;
		}
		return true;
	}

	private void updateModel() {
		if (deadlineDatePicker.getValue() != null) projectEdit.setDeadline(new SimpleObjectProperty<LocalDate>(deadlineDatePicker.getValue()));
		if (startdateDatePicker.getValue() != null) projectEdit.setStartdate(new SimpleObjectProperty<LocalDate>(startdateDatePicker.getValue()));
		
		if (!(budgetTextField.getText().isEmpty())){
			DecimalFormat decimalFormatter = new DecimalFormat();
			decimalFormatter.setParseBigDecimal(true);
			try {
				SimpleObjectProperty<BigDecimal> bTmp =  new SimpleObjectProperty<BigDecimal>(((BigDecimal) decimalFormatter.parse(budgetTextField.getText())));
				projectEdit.setBudget(bTmp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!(teamsTextField.getText().isEmpty())){
			ObservableList<ITeam> ttmp = FXCollections.observableArrayList();
			ttmp.addAll(FindTeams.setTeamsFromString(teamsTextField.getText(), teams));
			projectEdit.setTeams(ttmp);
			
		}
		if (!(employeesTextField.getText().isEmpty())){
			ObservableList<IEmployee> etmp = FXCollections.observableArrayList();
			etmp.addAll(FindEmployees.setEmployeesFromString(employeesTextField.getText(), employees));
			projectEdit.setEmployees(etmp);
		}
	}

	private void updateControls() {
		deadlineDatePicker.setValue(projectEdit.getDeadline().getValue());
		startdateDatePicker.setValue(projectEdit.getStartdate().getValue());
		String s1 = projectEdit.getStringTeamsForProject().getValue();
		String s2 = projectEdit.getStringEmployeesForProject().getValue();
		employeesTextField.setText(s2);
		teamsTextField.setText(s1);
		budgetTextField.setText(projectEdit.getBudget().getValue().toString());	
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
