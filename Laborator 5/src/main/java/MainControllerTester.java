import domain.Bug;
import domain.BugStatus;
import domain.Programmer;
import domain.Tester;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import service.Service;


import java.util.Collection;

public class MainControllerTester {
    private Service service;
    private Tester tester;

    @FXML
    private Button addBugButton;

    @FXML
    private ListView<Bug> bugsList;

    @FXML
    private TextField descriptionTextBox;

    @FXML
    private TextField nameTextBox;

    @FXML
    private Button viewBugsButton;

    @FXML
    private Button passwordButton;

    @FXML
    private TextField passwordTextBox;


    @FXML
    private Label welcomeLabel;

    public void setService(Service service) {
        this.service = service;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
        welcomeLabel.setText("Welcome back, " + tester.getLastName() + " " + tester.getFirstName());
    }

    public void setBugs(){
        Collection<Bug> bugs = service.getAllBugs();
        ObservableList<Bug> obsBugs = FXCollections.observableArrayList(bugs);
        bugsList.setItems(obsBugs);
    }

    @FXML
    public void addButtonClicked(MouseEvent event) throws Exception {
        String name = nameTextBox.getText();
        String description = descriptionTextBox.getText();
        if(name.equals("") || description.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Add Error");
            alert.setContentText("Introduce values!");
            alert.showAndWait();
        }
        else {
            Bug bug = new Bug(name, description, String.valueOf(BugStatus.active));
            service.addBug(bug);
            setBugs();
        }
    }

    @FXML
    public void viewBugsButtonClicked(MouseEvent event) {
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
            service.changePasswordTester(tester.getId(), password);
            passwordTextBox.clear();
        }
    }


}
