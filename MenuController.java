package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import window.NotaWindow;
import window.StudentWindow;
import window.TemaWindow;

public class MenuController {
    @FXML
    Button ButtonStudent;

    @FXML
    Button ButtonTema;

    public void descStud()
    {
        StudentWindow sw=new StudentWindow();

        Stage stage=new Stage();
        try{
            sw.start(stage);
        }catch (Exception e){}
        stage.show();
    }

    public void descTema()
    {
        TemaWindow tw=new TemaWindow();

        Stage stage=new Stage();
        try{
            tw.start(stage);
        }catch (Exception e){}
        stage.show();
    }

    public void descNota()
    {
        NotaWindow nw=new NotaWindow();

        Stage stage=new Stage();
        try{
            nw.start(stage);
        }catch (Exception e){}
        stage.show();
    }
}
