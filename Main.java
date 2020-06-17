package main;


import business.ServiceStudent;
import business.ServiceTema;
import controller.StudentController;
import controller.TemaController;
import domain.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistence.InXMLStudentRepository;
import persistence.InXMLTemaRepository;
import ui.ConsoleUI;
import validators.ValidationStudent;
import validators.ValidationTema;
import validators.Validator;

public class Main extends Application {

    /*
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ui.consStart();
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/studentView.fxml"));
        AnchorPane root=loader.load();

        InXMLStudentRepository repo=new InXMLStudentRepository(new ValidationStudent(),"C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\student.xml");
        StudentController ctrl=loader.getController();
        ctrl.setService(new ServiceStudent(repo));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Hello World");
        primaryStage.show();
*/
        /*
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/temaView.fxml"));
        AnchorPane root=loader.load();

        InXMLTemaRepository repo=new InXMLTemaRepository(new ValidationTema(),"C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\tema.xml");
        TemaController ctrl=loader.getController();
        ctrl.setService(new ServiceTema(repo));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Hello World");
        primaryStage.show();
        */

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/menuView.fxml"));
        AnchorPane root=loader.load();


        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Hello World");
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
