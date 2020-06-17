package controller;

import business.ServiceStudent;
import business.ServiceTema;
import domain.Student;
import domain.Tema;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import observer.Observer;
import persistence.InXMLTemaRepository;
import validators.ValidationTema;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TemaController implements Observer {
    ObservableList<Tema> modelGrade= FXCollections.observableArrayList();

    private ServiceTema service;

    @FXML
    TableView<Tema> tableViewTema;

    @FXML
    TableColumn<Tema,Long> tableColumnId;

    @FXML
    TableColumn<Tema,String> tableColumnDesc;

    @FXML
    TableColumn<Tema,Integer> tableColumnDeadlineWeek;

    @FXML
    TableColumn<Tema,Integer> tableColumnStartWeek;

    @FXML
    TextField textFieldDesc;

    @FXML
    TextField textFieldId;

    @FXML
    TextField textFieldDeadlineWeek;

    @FXML
    TextField textFieldStartWeek;

    @FXML
    Button ButtonAdd;

    @FXML
    Button ButtonDelete;

    @FXML
    Button ButtonUpdate;

    @FXML
    public void initialize()
    {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<Tema,Long>("id"));
        tableColumnDesc.setCellValueFactory(new PropertyValueFactory<Tema,String>("desc"));
        tableColumnStartWeek.setCellValueFactory(new PropertyValueFactory<Tema,Integer>("startWeek"));
        tableColumnDeadlineWeek.setCellValueFactory(new PropertyValueFactory<Tema,Integer>("deadlineWeek"));
        ButtonAdd=new Button("Adauga");
        ButtonDelete=new Button("Sterge");
        ButtonUpdate=new Button("Update");

        tableViewTema.setItems(modelGrade);
    }

    private void initTable()
    {
        modelGrade.clear();
        modelGrade.addAll(StreamSupport.stream(service.getAll().spliterator(),false).collect(Collectors.toList()));
    }

    public void adauga()
    {
        Tema t=new Tema(Long.parseLong(textFieldId.getText()),textFieldDesc.getText(),Integer.parseInt(textFieldStartWeek.getText()),Integer.parseInt(textFieldDeadlineWeek.getText()));
        service.addTema(t.getId(),t.getDesc(),t.getDeadlineWeek());
        initTable();
    }

    public void sterge()
    {
        Long id=Long.parseLong(textFieldId.getText());
        service.deleteTema(id);
        initTable();
    }

    public void update1()
    {
        Long id=Long.parseLong(textFieldId.getText());
        String desc=textFieldDesc.getText();
        Integer sw=Integer.parseInt(textFieldStartWeek.getText());
        Integer dw=Integer.parseInt(textFieldDeadlineWeek.getText());
        service.updateTema(id,desc,sw,dw);
        initTable();
    }

    public void setService(ServiceTema service) {
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
