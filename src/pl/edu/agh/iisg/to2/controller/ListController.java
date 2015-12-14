package pl.edu.agh.iisg.to2.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
	@FXML private TextField paramBudget;

	private LocalDateStringConverter converter;
	private ProjectController projController; 
	private ObservableList<ProjectMock> projects;
	private ObservableList<ITeam> teams;
	private ObservableList<IEmployee> employees;
	
	@FXML private Label errorId;
	@FXML private Label errorDate;
	@FXML private Label errorTeams;
	@FXML private Label errorEmployees;
	@FXML private Label errorBudget;
	
	private DateTimeFormatter formatter;
	
	
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
		errorId.setVisible(false);
		errorDate.setVisible(false);
		errorTeams.setVisible(false);
		errorEmployees.setVisible(false);
		errorBudget.setVisible(false);
	}
	
	private void initializeWithArguments() {
		ObservableList<ProjectMock> tmpWithArguments = FXCollections.observableArrayList();
		tmpWithArguments.addAll(projects);
		String tmp = "";
		if (!paramId.getText().isEmpty()) {
			tmp = paramId.getText();
			System.out.print("string id" + tmp);
			for (int i = 0; i < tmpWithArguments.size(); i++){
				if (!(tmp.equals(tmpWithArguments.get(i).getId()))){
					System.out.print("string id2" + tmpWithArguments.get(i).getId()+"\n");
					tmpWithArguments.remove(i) ;
					i--;
				}
			}
		}
		if (!paramDeadline.getText().isEmpty()){
			tmp = paramDeadline.getText();
			System.out.print("string deadline" + tmp);
			for (int i = 0; i < tmpWithArguments.size(); i++){
				if (!(tmp.equals(tmpWithArguments.get(i).getDeadline().getValue().toString()))){
					System.out.print("string deadline2" + tmpWithArguments.get(i).getDeadline().getValue().toString()+"\n");
					tmpWithArguments.remove(i) ;
					i--;
				}
			}
		}
		if (!paramStartDate.getText().isEmpty()){
			tmp = paramStartDate.getText();
			System.out.print("string startdate" + tmp);
			for (int i = 0; i < tmpWithArguments.size(); i++){
				if (!(tmp.equals(tmpWithArguments.get(i).getStartdate().getValue().toString()))){
					System.out.print("string startdate2" + tmpWithArguments.get(i).getStartdate().getValue().toString()+"\n");
					tmpWithArguments.remove(i);
					i--;
				}
			}
		}
		if (!paramEmployees.getText().isEmpty()){
			tmp = paramEmployees.getText();
			System.out.print("string Employees" + tmp);
			for (int i = 0; i < tmpWithArguments.size(); i++){
				if (!(tmp.equals(tmpWithArguments.get(i).getStringEmployees().getValue()))){
					System.out.print("string employees2" + tmpWithArguments.get(i).getStringEmployees().getValue()+"\n");
					tmpWithArguments.remove(i) ;
					i--;
				}
			}
		}
		if (!paramTeams.getText().isEmpty()){
			tmp = paramTeams.getText();
			System.out.print("string Teams" + tmp);
			for (int i = 0; i < tmpWithArguments.size(); i++){
				if (!(tmp.equals(tmpWithArguments.get(i).getStringTeams().getValue()))){
					System.out.print("string teams2" + tmpWithArguments.get(i).getStringTeams().getValue()+"\n");
					tmpWithArguments.remove(i);
					i--;
				}
			}
		}
		if (!paramBudget.getText().isEmpty()){
			tmp = paramBudget.getText();
			System.out.print("string Budget:" + tmp +"\n");
			System.out.println("size: "+tmpWithArguments.size());
			for (int i = 0; i < tmpWithArguments.size(); i++){
				if (!(tmp.equals(tmpWithArguments.get(i).getBudget().getValue().toString())) ){
					//System.out.print("string budget2 to remove: " + tmpWithArguments.get(i).getBudget().getValue().toString()+"\n");
					tmpWithArguments.remove(i);
					i--;
				} 
			}
		}
		for (ProjectMock tmpp: tmpWithArguments){
			System.out.println("koncowe:"+ tmpp.getDeadline().getValue().toString()+ tmpp.getDeadline().getValue().toString());
		}
		if (areParametersValid()){
			setData(tmpWithArguments, this.employees , this.teams);
		}	
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
			controllerAdd.setData(projectTable.getItems(), employees, teams);
			
            stageAdd.showAndWait();
            System.out.println("Refreshing...");
            //projectTable.refresh(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@FXML
	private void handleEditAction(ActionEvent event) {
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
    		controllerEdit.setData(projectTable.getSelectionModel().getSelectedItem(), this.employees, this.teams);
            
            stageEdit.showAndWait();
            //projectTable.refresh(); 
            
            System.out.println("Refreshing...");
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
	}
	
	public void setData(ObservableList<ProjectMock> p, ObservableList<IEmployee> e, ObservableList<ITeam> t) {
		this.employees = e;
		this.projects = p;
		for (ProjectMock ptmp: p){
			System.out.println("deadline: "+ ptmp.getDeadline().getValue().toString()+ "startdate: "+ ptmp.getStartdate().getValue().toString() + "\n");
		} 
		this.teams = t;
		projectTable.getItems().setAll(p);
	}
	
	public ProjectController getProjController() {
		return projController;
	}

	public void setProjController(ProjectController projController) {
		this.projController = projController;
	}
	
	public LocalDate fromStringDate(String string) {
		String pattern = "yyyy-MM-dd";
		formatter = DateTimeFormatter.ofPattern(pattern);
		setConverter(new LocalDateStringConverter(formatter, formatter));
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, formatter);
        } else {
            return null;
        }
    }
	
	private boolean areParametersValid() {
		//TODO: Implementation
		return true;
	}

	public LocalDateStringConverter getConverter() {
		return converter;
	}

	public void setConverter(LocalDateStringConverter converter) {
		this.converter = converter;
	}

	
	
}
