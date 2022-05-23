import domain.Programmer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class MainWindowProgrammer extends Application {
    private Programmer programmer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("mainProgrammer.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        AnchorPane root=loader.load();
        MainControllerProgrammer controller= loader.getController();

        controller.setService(Main.getService());
        controller.setProgrammer(programmer);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Programmer");
        primaryStage.show();
    }



    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }
}
