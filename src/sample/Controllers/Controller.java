package sample.Controllers;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.ControlledScreen;
import sample.ScreensController;
import sample.ScreensFramework;

public class Controller implements Initializable, ControlledScreen {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton loginTableButtom;


    @FXML
    private JFXButton loginSignUpButtom;

    ScreensController myController;

    @FXML
    void initialize() {
    }




        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // TODO
        }

        public void setScreenParent(ScreensController screenParent){
            myController = screenParent;
        }


        @FXML
        private void goToScreen2(ActionEvent event){
            myController.setScreen(ScreensFramework.screen2ID);
        }
}
