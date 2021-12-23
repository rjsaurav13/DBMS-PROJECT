package sample.Allcontrollers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.AllClasses.loanclass;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Loan implements Initializable {
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<loanclass, Integer> loanamount;

    @FXML
    private TableColumn<loanclass, Integer> loanbranchid;

    @FXML
    private TableColumn<loanclass, Integer> loancustomerid;

    @FXML
    private Button loandelete;

    @FXML
    private TextField loanentryamount;

    @FXML
    private TextField loanentrybranchid;

    @FXML
    private TextField loanentrycustomerid;

    @FXML
    private Button loaninsert;

    @FXML
    private TableView<loanclass> loantable;

    @FXML
    private Button loanupdate;

    @FXML // fx:id="account_btn"
    private Button account_btn; // Value injected by FXMLLoader

    @FXML // fx:id="branch_btn"
    private Button branch_btn; // Value injected by FXMLLoader

    @FXML // fx:id="customer_btn"
    private Button customer_btn; // Value injected by FXMLLoader

    @FXML // fx:id="exit"
    private Button exit; // Value injected by FXMLLoader

    @FXML // fx:id="loan_btn"
    private Button loan_btn; // Value injected by FXMLLoader

    @FXML // fx:id="transection_btn"
    private Button transection_btn; // Value injected by FXMLLoader



    @FXML
    void account_button(ActionEvent event) throws IOException {
        if(event.getSource()==account_btn) {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/account.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
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
    void transection_button(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/transaction.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    void deleterecord(ActionEvent event) {
        if(event.getSource()==loandelete){
            deletingrecord();
        }
    }
    @FXML
    void handlemouseclick(MouseEvent event) {
        loanclass items =loantable.getSelectionModel().getSelectedItem();
//        System.out.println(items.getAmount());
        loanentryamount.setText(String.valueOf(items.getAmount()));
        loanentrybranchid.setText(String.valueOf(items.getBranch_id()));
        loanentrycustomerid.setText(String.valueOf(items.getCustomer_id()));
    }

    @FXML
    void insertrecord(ActionEvent event) {
        if(event.getSource()==loaninsert){
            insertingrecord();
        }
    }

    @FXML
    void updaterecord(ActionEvent event) {
        if(event.getSource()==loanupdate){
            updatingrecord();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showloanrecord();
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
            System.out.println("error in connection part in testing load class");
            return null;
        }
    }

    private ObservableList<loanclass> gettestingrecord() {
        ObservableList<loanclass> recordlist= FXCollections.observableArrayList();
        Connection c=getConnection();
        String sql = "select * from bank_144.loan";
        Statement st = null;
        ResultSet rs = null;
        try{
            st=c.createStatement();
            rs=st.executeQuery(sql);
            loanclass loans;
            while(rs.next()){
                loans=new loanclass(rs.getInt("customer_id"),rs.getInt("branch_id"),rs.getInt("amount"));
                recordlist.add(loans);
            }
        }
        catch(Exception e){
            System.out.println("Error in observable list statement sql");
        }

        return recordlist;
    }

    public void showloanrecord() {
        ObservableList<loanclass> list= gettestingrecord();
        loancustomerid.setCellValueFactory(new PropertyValueFactory<loanclass,Integer>("customer_id"));
        loanbranchid.setCellValueFactory(new PropertyValueFactory<loanclass,Integer>("branch_id"));
        loanamount.setCellValueFactory(new PropertyValueFactory<loanclass,Integer>("amount"));
        loantable.setItems(list);

    }

    private void insertingrecord(){
        String sql = "insert into loan values ("+loanentrycustomerid.getText() + "," + loanentrybranchid.getText()+ "," + loanentryamount.getText() + ")";
        executeQuery(sql);
        showloanrecord();
    }

    private void executeQuery(String sql) {
        Connection c=getConnection();
        Statement st = null;

        try{
            st=c.createStatement();
            st.executeUpdate(sql);
//            if(check==0) {
//              //if(str=="update")  printing("Value Not Found","Can't Update",str); //because of foreign key not work
////              else
//              if(str=="delete") printing("Value Not Found","Can't Delete",str) ;
//            }
        }
        catch(Exception e){
            printing(e.getMessage(),"Cant't Insert ","Insert");
            System.out.println("Error occur in insert statement in loan");
        }

    }

    //foreign key not connected it will not work
    private void updatingrecord(){
        String sql="update loan set branch_id="+ loanentrybranchid.getText() +",amount="+loanentryamount.getText()+
                   " where customer_id="+ loanentrycustomerid.getText();
        executeQuery(sql);
        showloanrecord();
    }

//    delete from loan where amount=143;
    private void deletingrecord(){
        String sql="delete from loan where customer_id="+loanentrycustomerid.getText()+" and branch_id="+
                   loanentrybranchid.getText()+" and amount="+loanentryamount.getText();
        executeQuery(sql);

        showloanrecord();
    }

    public static void printing(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
