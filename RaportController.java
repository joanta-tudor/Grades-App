package controller;

import business.ServiceNota;
import business.ServiceStudent;
import business.ServiceTema;
import domain.StudentMedie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import observer.Observable;

public class RaportController {
    ObservableList<StudentMedie> modelgrade= FXCollections.observableArrayList();
    Long ids;
    Float medie;
    String nume;
    Stage primaryStage;

    ServiceNota serviceNota;
    ServiceTema serviceTema;
    ServiceStudent serviceStudent;

    @FXML
    TableView<StudentMedie> tableViewMedie;

    @FXML
    TableColumn<StudentMedie,Long> tableColumnIds;

    @FXML
    TableColumn<StudentMedie,String> tableColumnName;

    @FXML
    TableColumn<StudentMedie,Float> tableColumnMedie;

    @FXML
    public void initialize()
    {
        tableColumnIds.setCellValueFactory(new PropertyValueFactory<StudentMedie,Long>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<StudentMedie,String>("nume"));
        tableColumnMedie.setCellValueFactory(new PropertyValueFactory<StudentMedie,Float>("medie"));
    }

    public void setController(Long ids,Float medie,String nume,ServiceNota serviceNota,ServiceTema serviceTema,ServiceStudent serviceStudent,Stage primaryStage)
    {
        this.ids=ids;
        this.medie=medie;
        this.nume=nume;
        this.serviceNota=serviceNota;
        this.serviceTema=serviceTema;
        this.serviceStudent=serviceStudent;
        this.primaryStage=primaryStage;
    }
}
