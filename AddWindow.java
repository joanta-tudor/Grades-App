package window;

import business.ServiceNota;
import controller.AddController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddWindow extends Application {
    Long ids,idt;
    Float nota,penalizare;
    String Feedback,Motivare;

    ServiceNota service;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/addView.fxml"));
        AnchorPane root=(AnchorPane) loader.load();

        AddController ac=loader.getController();
        ac.setController(ids,idt,nota,penalizare,Feedback,Motivare,service,primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Add Window");

        primaryStage.show();
    }

    public void setWindow(Long ids,Long idt,Float nota,Float penalizare,String Feedback,String Motivare,ServiceNota service)
    {
        this.ids=ids;
        this.idt=idt;
        this.nota=nota;
        this.penalizare=penalizare;
        this.Feedback=Feedback;
        this.Motivare=Motivare;
        this.service=service;
    }
}
