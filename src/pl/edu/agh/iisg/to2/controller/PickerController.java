package pl.edu.agh.iisg.to2.controller;

import java.math.BigDecimal;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to2.model.EmployeeMock;
import pl.edu.agh.iisg.to2.model.IEmployee;
import pl.edu.agh.iisg.to2.model.ITeam;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class PickerController {
	
	@FXML private TableView<IEmployee> pickerTable;
	@FXML private TableColumn<IEmployee, String> nameColumn;
	@FXML private TableColumn<IEmployee, BigDecimal> costColumn;
	@FXML private TableColumn<IEmployee, String> checkColumn;
	
	@FXML private Button closeButton;
	@FXML private Button addButton;
	@FXML private Button findButton;
	
	@FXML private TextField searchTextField;
	
	private Stage dialogStage;
	
	private ObservableList<IEmployee> allEmployees;
	private ObservableList<IEmployee> selectedEmployees;
	
	@FXML
	private void initialize() {
//		nameColumn.setCellValueFactory(value -> value.getValue().getFirstName());
//		costColumn.setCellValueFactory(value -> value.getValue().getSalary());
	}
	
	@FXML
	private void handleAddAction(ActionEvent event) {
		dialogStage.close();
	}
	
	@FXML
	private void handleCloseAction(ActionEvent event) {
		dialogStage.close();
	}
	
	@FXML
	private void handleFindAction(ActionEvent event) {
		
	}
	
	/// Public Interface
	
	public void setData(ObservableList<IEmployee> allEmployees,
						ObservableList<IEmployee> selectedEmployees) {
		this.allEmployees = allEmployees;
		this.selectedEmployees = selectedEmployees;
		
		pickerTable.getItems().setAll(allEmployees);
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
}
