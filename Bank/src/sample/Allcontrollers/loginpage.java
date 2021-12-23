package sample.Allcontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class loginpage implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginbtn"
    private Button loginbtn; // Value injected by FXMLLoader

    @FXML // fx:id="loginpassward"
    private PasswordField loginpassward; // Value injected by FXMLLoader

    @FXML // fx:id="loginuser"
    private TextField loginuser; // Value injected by FXMLLoader

    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {

        String user = loginuser.getText();
        String password = loginpassward.getText();

        Connection c=getConnection();
        String str="Select * from bank_144.login where name=? and usn=?";

        try{
            if(user.isEmpty() )
                printing("Plz Enter your Username",null,"Failed");

            if(password.isEmpty() )
                printing("Plz Enter your Passward",null,"Failed");

            PreparedStatement stmt=c.prepareStatement(str);
            stmt.setString(1,user);
            stmt.setString(2,password);
            ResultSet resultSet=stmt.executeQuery();
            if(!resultSet.next()){
                printing("Please enter correct Username and Password", null, "Failed");
            }else{
                printing("Login Successfull",null,"Success" );

                //going to another FXML Page
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/loan.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        Connection c;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c= DriverManager.getConnection("jdbc:mysql://117.236.190.213/bank_144","bank_144","bank_144");
            System.out.println("Connected to the database successfully " + c.getCatalog());
            return c;
        }
        catch(Exception e){
            System.out.println("error in connection part in testing loginpage class");
            return null;
        }
    }

    public static void printing(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}