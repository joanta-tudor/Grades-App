package window;

import business.ServiceTema;
import controller.TemaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistence.InXMLTemaRepository;
import validators.ValidationTema;

public class TemaWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/temaView.fxml"));
        AnchorPane root=loader.load();

        InXMLTemaRepository repo=new InXMLTemaRepository(new ValidationTema(),"C:\\Users\\Tudor\\Desktop\\Facultate\\An II Semestru 1\\MAP\\Lab4\\Lab4\\src\\tema.xml");
        TemaController ctrl=loader.getController();
        ctrl.setService(new ServiceTema(repo));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Tema");

        primaryStage.show();
    }
}
