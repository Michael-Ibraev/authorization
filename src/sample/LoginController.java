package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

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

    @FXML
    public void login(ActionEvent event) throws SQLException {

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
            infoBox("Please enter correct Login and Password", null, "Failed");
        } else {

            infoBox(db.getEmpInfo(login, password), null, "Success");
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
