package window;

import business.ServiceStudent;
import controller.StudentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistence.InXMLStudentRepository;
import validators.ValidationStudent;

public class StudentWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/studentView.fxml"));
        AnchorPane root=loader.load();

        InXMLStudentRepository repo=new InXMLStudentRepository(new ValidationStudent(),"C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\student.xml");
        StudentController ctrl=loader.getController();
        ctrl.setService(new ServiceStudent(repo));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Student");

        primaryStage.show();
    }
}
