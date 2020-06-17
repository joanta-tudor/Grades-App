package controller;

import business.ServiceNota;
import business.ServiceStudent;
import business.ServiceTema;
import domain.Nota;
import domain.StructuraSemestru;
import domain.Student;
import domain.Tema;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import observer.Observer;
import window.AddWindow;
import window.RaportWindow;
import window.TemaWindow;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class NotaController implements Observer {
    ObservableList<Nota> modelGrade= FXCollections.observableArrayList();
    ObservableList<Long> teme= FXCollections.observableArrayList();
    ObservableList<Tema> temetoate= FXCollections.observableArrayList();
    ObservableList<Student> students=FXCollections.observableArrayList();
    ObservableList<String> names=FXCollections.observableArrayList();


    private ServiceNota service;
    private ServiceTema service1;
    private ServiceStudent service2;

    @FXML
    TableView<Nota> tableViewNota;

    @FXML
    TableColumn<Nota,Long> tableColumnIds;

    @FXML
    TableColumn<Nota,Long> tableColumnIdt;

    @FXML
    TableColumn<Nota, LocalDateTime> tableColumnDate;

    @FXML
    TableColumn<Nota,Float> tableColumnValue;

    @FXML
    TableColumn<Nota,String> tableColumnFeedback;

    @FXML
    TextField textFieldIds;

    @FXML
    ComboBox<Long> comboBoxIdt;

    @FXML
    TextField textFieldData;

    @FXML
    TextField textFieldValue;

    @FXML
    TextField textFieldName;

    @FXML
    TextField textFieldPenal;

    @FXML
    TextField textFieldMotivare;

    @FXML
    TextArea textAreaFeedback;

    @FXML
    Button ButtonAdd;

    @FXML
    ListView<String> listViewName;

    @FXML
    public void initialize()
    {
        tableColumnIds.setCellValueFactory(new PropertyValueFactory<Nota,Long>("idStudent"));
        tableColumnIdt.setCellValueFactory(new PropertyValueFactory<Nota,Long>("idTema"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<Nota,LocalDateTime>("data"));
        tableColumnValue.setCellValueFactory(new PropertyValueFactory<Nota,Float>("value"));
        tableColumnFeedback.setCellValueFactory(new PropertyValueFactory<Nota,String>("feedback"));
        ButtonAdd=new Button("Adauga");

        listViewName.setItems(names);
        comboBoxIdt.setItems(teme);
        tableViewNota.setItems(modelGrade);
    }

    public void setService(ServiceNota service,ServiceTema service1,ServiceStudent service2) {
        this.service = service;
        service.addObserver(this);
        this.service1=service1;
        this.service2=service2;
        modelGrade.addAll(StreamSupport.stream(service.getAll().spliterator(),false).collect(Collectors.toList()));
        temetoate.addAll(StreamSupport.stream(service1.getAll().spliterator(),false).collect(Collectors.toList()));
        teme.addAll(temetoate.stream().map(t->t.getId()).collect(Collectors.toList()));
        students.addAll(StreamSupport.stream(service2.getAll().spliterator(),false).collect(Collectors.toList()));
        names.addAll(students.stream().map(s->s.getNume()).collect(Collectors.toList()));
        textFieldName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1==null)
                    return ;
                filter();
            }
        });

        listViewName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1==null)
                    return;
                for(int i=0;i<students.size();i++)
                {
                    if (students.get(i).getNume()==t1) {
                        textFieldIds.setText(Long.toString(students.get(i).getId()));
                        return;
                    }
                }
            }
        });

        comboBoxIdt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Long>() {
            @Override
            public void changed(ObservableValue<? extends Long> observableValue, Long aLong, Long t1) {
                StructuraSemestru ss=new StructuraSemestru(2019,1);
                if (t1==null)
                    return ;
                for (int i=0;i<temetoate.size();i++)
                    if(Integer.parseInt(comboBoxIdt.getValue().toString())==temetoate.get(i).getId() && temetoate.get(i).getDeadlineWeek()<ss.getWeek(LocalDateTime.now())) {
                        textAreaFeedback.setText("NOTA A FOST DIMINUATĂ CU" + Integer.toString(ss.getWeek(LocalDateTime.now()) - temetoate.get(i).getDeadlineWeek()) + "PUNCTE  DATORITĂ  ÎNTÂRZIERILOR");
                        return ;
                    }
            }
        });

        ButtonAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1==null)
                    return ;

            }
        });
    }

    public void filter()
    {
        listViewName.getItems().clear();
        Predicate<String> byname= x->x.startsWith(textFieldName.getText()) || textFieldName.getText().length()==0;

        ObservableList<Student> students1=FXCollections.observableArrayList();
        students1.addAll(StreamSupport.stream(service2.getAll().spliterator(),false).collect(Collectors.toList()));
        ObservableList<String> names1=FXCollections.observableArrayList();
        names1.addAll(students1.stream().map(s->s.getNume()).collect(Collectors.toList()));
        List<String> listf=names1.stream()
                .filter(byname)
                .collect(Collectors.toList());
        listViewName.getItems().addAll(listf);
    }

    private void initTable (){
        modelGrade.clear();
        modelGrade.addAll(StreamSupport.stream(service.getAll().spliterator(),false).collect(Collectors.toList()));
    }

    public void deschideAdd () throws Exception
    {
        AddWindow aw=new AddWindow();
        Long ids= Long.parseLong(textFieldIds.getText());
        Long idt=Long.parseLong(comboBoxIdt.getValue().toString());
        Float nota=Float.parseFloat(textFieldValue.getText());
        Float penal = Float.parseFloat(textFieldPenal.getText());

        Stage stage=new Stage();
        //try{
            aw.setWindow(ids,idt,nota,penal,textAreaFeedback.getText(),textFieldMotivare.getText(),service);
            aw.start(stage);
       // }catch (Exception e){}
        stage.show();
    }

    public void deschideRaport() throws Exception
    {
        RaportWindow rw=new RaportWindow();
        Long ids=Long.parseLong(textFieldIds.getText());
        String nume=textFieldName.getText();
        for()

        Stage stage=new Stage();
        rw.setWindow();
    }

    public void adauga()
    {
        Float penalizare,value=Float.parseFloat(textFieldValue.getText());
        Nota n=new Nota(Long.parseLong(textFieldIds.getText()),Long.parseLong(comboBoxIdt.getValue().toString()),LocalDateTime.parse(textFieldData.getText()),Float.parseFloat(textFieldValue.getText()),textAreaFeedback.getText());

        if(textFieldMotivare.getText()=="Da")
        {
            service.addNota(n.getIdStudent(),n.getIdTema(),n.getFeedback(),value);
            initTable();
        }
        else if (textFieldMotivare.getText()=="Nu")
        {
            if(service.verificaSapt(Long.parseLong(comboBoxIdt.getValue().toString()))==0) {
                //Verificare motivare
                penalizare = Float.parseFloat(textFieldPenal.getText());
                value = service.calcNota(penalizare,Long.parseLong(comboBoxIdt.getValue().toString()),value);
            }
        }

        /*
        if(service.verificaSapt(Long.parseLong(comboBoxIdt.getValue().toString()))==0) {
            //Verificare motivare
            penalizare = Float.parseFloat(textFieldPenal.getText());
            value = service.calcNota(penalizare,Long.parseLong(comboBoxIdt.getValue().toString()),value);
        }

        service.addNota(n.getIdStudent(),n.getIdTema(),n.getFeedback(),value);
        initTable();*/
    }

    @Override
    public void update() {
        initTable();
    }
}
