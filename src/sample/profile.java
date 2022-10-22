package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class profile implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Text textHi;
    @FXML
    private Text doljnost;

    public static String surName = "";
    public static String fio = "";
    public static String dolger = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file= new File("src/img/" + surName + ".jpeg");
        Image image = new Image(file.toURI().toString());
        photo.setImage(image);
        textHi.setText("Здравствуйте, "+fio+" !");
        doljnost.setText("Ваша должность: "+dolger);
    }
    public void exit(ActionEvent event) throws IOException {
        Stage st3 = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        st3.getIcons().add(new Image("img/logo.png"));
        st3.setTitle("ЦПКиО им. Маяковского");
        st3.setScene(new Scene(root, 600, 400));
        st3.setResizable(false);
        st3.show();

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}