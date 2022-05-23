import domain.Bug;
import domain.BugStatus;
import domain.Programmer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import service.Service;

import java.awt.*;

import java.util.Collection;

public class MainControllerProgrammer {
    private Service service;
    private Programmer programmer;


    @FXML
    private Label welcomeLabel;

    @FXML
    private Button deleteBugButton;

    @FXML
    private Button updateBugButton;

    @FXML
    private Button viewBugsButton;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private Button changePasswordButton;

    @FXML
    private ListView<Bug> bugsList;

    public void setService(Service service) {
        this.service = service;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
        welcomeLabel.setText("Welcome back, " + programmer.getLastName() + " " + programmer.getFirstName());
    }


    public void setBugs(){
        Collection<Bug> bugs = service.getAllBugs();
        ObservableList<Bug> obsBugs = FXCollections.observableArrayList(bugs);
        bugsList.setItems(obsBugs);
    }

    @FXML
    void deleteButtonClicked(MouseEvent event) {
        Bug bug = bugsList.getSelectionModel().getSelectedItem();
        if(bug == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Delete Error");
            alert.setContentText("Select a bug!");
            alert.showAndWait();
        }
        else{
        service.deleteBug(bug.getId());
        setBugs();
        }
    }

    @FXML
    void updateButtonClicked(MouseEvent event) {
        Bug bug = bugsList.getSelectionModel().getSelectedItem();
        if(bug == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Delete Error");
            alert.setContentText("Select a bug!");
            alert.showAndWait();
        }
        else {
            bug.setBugStatus(String.valueOf(BugStatus.solved));
            service.updateBug(bug, bug.getId());
            setBugs();
        }
    }

    @FXML
    void viewBugsButtonClicked(MouseEvent event) {
        setBugs();
    }

    @FXML
    void changePasswordButtonClicked(MouseEvent event) {
        String password = passwordTextBox.getText();
        if(password == ""){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("New Password Error");
            alert.setContentText("Introduce the new password!");
            alert.showAndWait();
        }
        else{
            service.changePasswordProgrammer(programmer.getId(), password);
            passwordTextBox.setText("");
        }
    }


}
