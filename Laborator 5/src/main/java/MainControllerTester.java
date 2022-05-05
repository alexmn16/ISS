import domain.Programmer;
import domain.Tester;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.Service;

public class MainControllerTester {
    private Service service;
    private Tester tester;

    @FXML
    private Label welcomeLabel;

    public void setService(Service service) {
        this.service = service;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
        welcomeLabel.setText("Welcome back, " + tester.getLastName() + " " + tester.getFirstName());
    }
}
