import domain.Programmer;
import domain.Tester;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.Service;

import java.util.Objects;

public class LoginController {
    private Service service;

    @FXML
    private Label ErrorMessageLoginIn;

    @FXML
    private Button LogInButton;

    @FXML
    private javafx.scene.control.PasswordField PasswordField;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField UsernameTextField;




    @FXML
    void LogInButtonClicked() throws Exception {
        String username = UsernameTextField.getText();
        String password = PasswordField.getText();
        Programmer programmer = service.findProgrammerByUsername(username);
        if(programmer == null)
            ErrorMessageLoginIn.setText("Login error");
        else if(!Objects.equals(programmer.getPassword(), password))
            ErrorMessageLoginIn.setText("Login error");
        else{
            ErrorMessageLoginIn.setText("");
            Stage newWindow = (Stage) LogInButton.getScene().getWindow();
            MainWindowProgrammer main = new MainWindowProgrammer();
            main.setProgrammer(programmer);
            main.start(newWindow);
        }
        Tester tester = service.findTesterByUsername(username);
        if(tester == null)
            ErrorMessageLoginIn.setText("Login error");
        else if(!Objects.equals(tester.getPassword(), password))
            ErrorMessageLoginIn.setText("Login error");
        else{
            ErrorMessageLoginIn.setText("");
            Stage newWindow = (Stage) LogInButton.getScene().getWindow();
            MainWindowTester main = new MainWindowTester();
            main.setTester(tester);
            main.start(newWindow);

        }

    }

    public void setService(Service service) {
        this.service = service;
    }
}
