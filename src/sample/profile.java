package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
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
}

