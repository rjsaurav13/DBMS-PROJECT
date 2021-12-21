package sample.Allcontrollers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Account implements Initializable {
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private Button account_btn;

    @FXML
    private Button branch_btn;

    @FXML
    private Button customer_btn;

    @FXML
    private Button exit;

    @FXML
    private Button loan_btn;

    @FXML
    private Button transection_btn;

    @FXML
    void account_button(ActionEvent event) {

    }

    @FXML
    void branch_button(ActionEvent event) {

    }

    @FXML
    void customer_button(ActionEvent event) {

    }

    @FXML
    void exit_btn(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void loan_button(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/loan.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    void transection_button(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/transaction.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
