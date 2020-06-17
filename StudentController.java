package controller;

import business.ServiceStudent;
import domain.Student;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import observer.Observer;
import persistence.InXMLStudentRepository;
import validators.ValidationStudent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentController implements Observer {

    ObservableList<Student> modelGrade= FXCollections.observableArrayList();

    private ServiceStudent service;

    @FXML
    TableView<Student> tableViewStudent;

    @FXML
    TableColumn<Student,Long> tableColumnId;

    @FXML
    TableColumn<Student,String> tableColumnName;

    @FXML
    TableColumn<Student,Integer> tableColumnGroup;

    @FXML
    TableColumn<Student,String> tableColumnEmail;

    @FXML
    TableColumn<Student,String> tableColumnProfessor;

    @FXML
    TextField textFieldName;

    @FXML
    TextField textFieldId;

    @FXML
    TextField textFieldGroup;

    @FXML
    TextField textFieldEmail;

    @FXML
    TextField textFieldProfessor;

    @FXML
    Button ButtonAdd;

    @FXML
    Button ButtonDelete;

    @FXML
    Button ButtonUpdate;

    @FXML
    public void initialize()
    {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<Student,Long>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Student,String>("nume"));
        tableColumnGroup.setCellValueFactory(new PropertyValueFactory<Student,Integer>("grupa"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
        tableColumnProfessor.setCellValueFactory(new PropertyValueFactory<Student,String>("prof"));
        ButtonAdd=new Button("Adauga");
        ButtonDelete=new Button("Sterge");
        ButtonUpdate=new Button("Update");

        tableViewStudent.setItems(modelGrade);
    }
    private void initTable (){
        modelGrade.clear();
        modelGrade.addAll(StreamSupport.stream(service.getAll().spliterator(),false).collect(Collectors.toList()));
    }

    public void adauga()
    {
        Student s=new Student(Long.parseLong(textFieldId.getText()),textFieldName.getText(),Integer.parseInt(textFieldGroup.getText()),textFieldEmail.getText(),textFieldProfessor.getText());
        service.addStudent(s.getId(),s.getNume(),s.getGrupa(),s.getEmail(),s.getProf());
        initTable();
    }

    public void update1()
    {
        Long id=Long.parseLong(textFieldId.getText());
        String nume=textFieldName.getText();
        Integer gr=Integer.parseInt(textFieldGroup.getText());
        String email=textFieldEmail.getText();
        String prof=textFieldProfessor.getText();
        service.updateStudent(id,nume,gr,email,prof);
        initTable();
    }

    public void sterge()
    {
        Long id=Long.parseLong(textFieldId.getText());
        service.deleteStudent(id);
        initTable();
    }



    public void setService(ServiceStudent service) {
        this.service = service;
        service.addObserver(this);
        //convertim la lista de student din iterable
        modelGrade.addAll(StreamSupport.stream(service.getAll().spliterator(),false).collect(Collectors.toList()));

    }

    @Override
    public void update() {
        initTable();
    }
}
