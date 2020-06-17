package window;

import business.ServiceNota;
import business.ServiceStudent;
import business.ServiceTema;
import controller.NotaController;
import controller.StudentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistence.InXMLNotaRepository;
import persistence.InXMLStudentRepository;
import persistence.InXMLTemaRepository;
import validators.ValidationNota;
import validators.ValidationStudent;
import validators.ValidationTema;

public class NotaWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/notaView.fxml"));
        AnchorPane root=loader.load();

        InXMLStudentRepository repos=new InXMLStudentRepository(new ValidationStudent(),"C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\student.xml");
        InXMLTemaRepository repot=new InXMLTemaRepository(new ValidationTema(),"C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\tema.xml");
        InXMLNotaRepository repo=new InXMLNotaRepository(new ValidationNota(),"C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\nota.xml");
        NotaController ctrl=loader.getController();
        ServiceTema serviceTema=new ServiceTema(repot);
        ServiceStudent serviceStudent = new ServiceStudent(repos);
        ctrl.setService(new ServiceNota(repo,repos,repot),serviceTema,serviceStudent);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Nota");

        primaryStage.show();
    }
}
