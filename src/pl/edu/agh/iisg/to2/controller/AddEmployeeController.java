package pl.edu.agh.iisg.to2.controller;

import java.math.BigDecimal;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to2.model.IEmployee;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class AddEmployeeController {

		@FXML private TableView<IEmployee> employeeTable;
		@FXML private TableColumn<IEmployee, String> idColumn;
		@FXML private TableColumn<IEmployee, String> firstNameColumn;
		@FXML private TableColumn<IEmployee, String> lastNameColumn;
		@FXML private TableColumn<IEmployee, BigDecimal> salaryColumn;
		
		@FXML private Button cancelButton;
		@FXML private Button addButton;
		@FXML private Button okButton;
		
		@FXML private TextField employeeTextField;

		private ProjectController projController; 
		private ProjectMock project;
		private ObservableList<IEmployee> employees;

		@FXML private Label errorEmployees;
		
		
		@FXML
		private void initialize() {
			employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			firstNameColumn.setCellValueFactory(value -> value.getValue().getFirstNameObservable());
			lastNameColumn.setCellValueFactory(value -> value.getValue().getLastNameObservable());
			salaryColumn.setCellValueFactory(value -> value.getValue().getSalaryObservable());
			addButton.disableProperty().bind(Bindings.isEmpty(employeeTable.getSelectionModel().getSelectedItems()));
			errorEmployees.setVisible(false);
		}
		
		@FXML
		private void handleCancelAction(ActionEvent event) {
			Stage stage = (Stage)cancelButton.getScene().getWindow();
			stage.close();
		}


		@FXML
		private void handleAddAction(ActionEvent event) {
	        	IEmployee etmp = employeeTable.getSelectionModel().getSelectedItem();
	        	if (this.project.getEmployees() != null){
	        		this.project.getEmployees().add(etmp);
	        	}else{
	        		ObservableList<IEmployee> e = FXCollections.observableArrayList(etmp);
	        		this.project.setEmployees(e);
	        	}
	        	employeeTable.getItems().removeAll(employeeTable.getSelectionModel().getSelectedItems());
	        	employeeTextField.setText(project.getStringEmployeesForProject().getValue());
	        	employeeTable.getItems().removeAll(employeeTable.getSelectionModel().getSelectedItems());
	            
	        	
	            System.out.println("Refreshing...");
	            employeeTable.refresh(); 
		}
		
		@FXML
		private void handleOkAction(ActionEvent event) {
			if (this.project.getEmployees() == null){
				errorEmployees.setText("You didn't choose employees");
				errorEmployees.setVisible(false);
			}
		}
		
		public void setData(ProjectMock p, ObservableList<IEmployee> e) {
			this.employees = e;
			this.project = p;
			employeeTable.getItems().setAll(e);
		}
		
		public ProjectController getProjController() {
			return projController;
		}

		public void setProjController(ProjectController projController) {
			this.projController = projController;
		}		
		
}

