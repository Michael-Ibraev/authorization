package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.text.View;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private CheckBox pass_toggle;

    @FXML
    private TextField passwordHidden;

    int enter_counter = 0;


    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(loginField.getText());
        System.out.println(passwordField.getText());

        if (loginField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your login");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String login = loginField.getText();
        String password = passwordField.getText();

        DB db = new DB();
        boolean flag = db.validate(login, password);


        if (!flag) {
            if(enter_counter>1){
            new Thread(()->{
                submitButton.setDisable(true);
                try {
                    Thread.sleep(3000);
                    submitButton.setDisable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
            infoBox("Please enter correct Login and Password", null, "Failed");
            System.out.println("counter: "+enter_counter);
            enter_counter++;


        }
        else {
            db.getEmpInfo(login, password);

            Stage stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
            stage.getIcons().add(new Image("img/logo.png"));
            stage.setTitle("ЦПКиО им. Маяковского");
            stage.setScene(new Scene(root, 1000, 800));
            stage.show();
            stage.setResizable(false);

            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }



    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    public void togglevisiblePassword(ActionEvent event) {
        if (pass_toggle.isSelected()) {
            passwordHidden.setText(passwordField.getText());
            passwordHidden.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordHidden.getText());
        passwordField.setVisible(true);
        passwordHidden.setVisible(false);
    }
}