import domain.Programmer;
import domain.Tester;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class MainWindowTester extends Application {
    private Tester tester;

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("mainTester.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        AnchorPane root = loader.load();
        MainControllerTester controller = loader.getController();
        controller.setService(Main.getService());
        controller.setTester(tester);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Tester");
        primaryStage.show();
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }
}
