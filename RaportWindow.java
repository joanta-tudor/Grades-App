package window;

import business.ServiceNota;
import business.ServiceStudent;
import business.ServiceTema;
import controller.AddController;
import controller.RaportController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RaportWindow extends Application {
    Long ids;
    Float medie;
    String nume;

    ServiceNota serviceNota;
    ServiceStudent serviceStudent;
    ServiceTema serviceTema;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/raportView.fxml"));
        AnchorPane root=(AnchorPane) loader.load();

        RaportController rc=loader.getController();
        rc.setController(ids,medie,nume,serviceNota,serviceTema,serviceStudent,primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Raport Window");

        primaryStage.show();
    }

    public void setWindow(Long ids,Float medie,String nume,ServiceNota serviceNota,ServiceTema serviceTema,ServiceStudent serviceStudent)
    {
        this.ids=ids;
        this.medie=medie;
        this.nume=nume;
        this.serviceNota=serviceNota;
        this.serviceTema=serviceTema;
        this.serviceStudent=serviceStudent;
    }
}
