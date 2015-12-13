package pl.edu.agh.iisg.to2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to2.model.ProjectMock;

public class PickerController {
	
	@FXML private TableView<ProjectMock> pickerTable;
	@FXML private TableColumn<ProjectMock, String> nameColumn;
	@FXML private TableColumn<ProjectMock, String> costColumn;
	@FXML private TableColumn<ProjectMock, String> checkColumn;
	
	@FXML private Button okButton;


	private Stage dialogStage;
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {

	}
	
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
}
