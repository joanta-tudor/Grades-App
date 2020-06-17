package controller;

import business.ServiceNota;
import domain.Nota;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class AddController {
    Long ids,idt;
    Float nota,penalizare;
    String Feedback,Motivare;
    Stage primaryStage;

    ServiceNota service;

    @FXML
    TextField textFieldNameAdd;

    @FXML
    TextField textFieldTemaAdd;

    @FXML
    TextField textFieldValoareAdd;

    @FXML
    TextField textFieldPenalAdd;

    @FXML
    Button ButtonAddOK;

    @FXML
    Button ButtonAddCANCEL;

    public void adauga()
    {
        Float value=Float.parseFloat(textFieldValoareAdd.getText());
        Nota n=new Nota(ids,idt,LocalDateTime.now(),nota,Feedback);

        if(Motivare.equals("Da"))
        {
            service.addNota(n.getIdStudent(),n.getIdTema(),n.getFeedback(),value);
        }
        else if (Motivare.equals("Nu"))
        {
            if(service.verificaSapt(idt)==0) {
                //Verificare motivare
                value = service.calcNota(penalizare,idt,nota);
                service.addNota(n.getIdStudent(),n.getIdTema(),n.getFeedback(),value);
            }
        }
        primaryStage.close();
    }

    public void setController(Long ids, Long idt, Float nota, Float penalizare, String Feedback, String Motivare, ServiceNota service, Stage PrimaryStage)
    {
        this.ids=ids;
        this.idt=idt;
        this.nota=nota;
        this.penalizare=penalizare;
        this.Feedback=Feedback;
        this.Motivare=Motivare;
        this.service=service;
        this.primaryStage=PrimaryStage;
        textFieldNameAdd.setText(Long.toString(ids));
        textFieldTemaAdd.setText(Long.toString(idt));
        textFieldValoareAdd.setText(Float.toString(nota));
        textFieldPenalAdd.setText(Float.toString(penalizare));
    }

    public void cancel()
    {
        primaryStage.close();
    }

}
