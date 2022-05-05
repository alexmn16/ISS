import domain.Programmer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.Service;

public class MainControllerProgrammer {
    private Service service;
    private Programmer programmer;

    @FXML
    private Label welcomeLabel;

    public void setService(Service service) {
        this.service = service;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
        welcomeLabel.setText("Welcome back, " + programmer.getLastName() + " " + programmer.getFirstName());
    }
}
