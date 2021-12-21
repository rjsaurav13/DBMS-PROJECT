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
import sample.AllClasses.tranctionclass;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Transaction implements Initializable {
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML // fx:id="accentry"
    private TextField accentry; // Value injected by FXMLLoader

    @FXML // fx:id="accnotable"
    private TableColumn<tranctionclass, Integer> accnotable; // Value injected by FXMLLoader

    @FXML // fx:id="account_btn"
    private Button account_btn; // Value injected by FXMLLoader

    @FXML // fx:id="amountentry"
    private TextField amountentry; // Value injected by FXMLLoader

    @FXML // fx:id="amounttable"
    private TableColumn<tranctionclass, Integer> amounttable; // Value injected by FXMLLoader

    @FXML // fx:id="branch_btn"
    private Button branch_btn; // Value injected by FXMLLoader

    @FXML // fx:id="customer_btn"
    private Button customer_btn; // Value injected by FXMLLoader

    @FXML // fx:id="datentey"
    private TextField datentey; // Value injected by FXMLLoader

    @FXML // fx:id="datetable"
    private TableColumn<tranctionclass, String> datetable; // Value injected by FXMLLoader

    @FXML // fx:id="deletebutton"
    private Button deletebutton; // Value injected by FXMLLoader

    @FXML // fx:id="exit"
    private Button exit; // Value injected by FXMLLoader

    @FXML // fx:id="insertbutton"
    private Button insertbutton; // Value injected by FXMLLoader

    @FXML // fx:id="loan_btn"
    private Button loan_btn; // Value injected by FXMLLoader

    @FXML // fx:id="modentry"
    private TextField modentry; // Value injected by FXMLLoader

    @FXML // fx:id="modetable"
    private TableColumn<tranctionclass,String > modetable; // Value injected by FXMLLoader

    @FXML // fx:id="tnotable"
    private TableColumn<tranctionclass, Integer> tnotable; // Value injected by FXMLLoader

    @FXML // fx:id="tranoentry"
    private TextField tranoentry; // Value injected by FXMLLoader

    @FXML // fx:id="transactiontable"
    private TableView<tranctionclass> transactiontable; // Value injected by FXMLLoader

    @FXML // fx:id="typeentry"
    private TextField typeentry; // Value injected by FXMLLoader

    @FXML // fx:id="typetable"
    private TableColumn<tranctionclass, String> typetable; // Value injected by FXMLLoader

    @FXML // fx:id="updatebutton"
    private Button updatebutton; // Value injected by FXMLLoader

    @FXML
    void selectentry(MouseEvent event) {
        tranctionclass items =transactiontable.getSelectionModel().getSelectedItem();
        accentry.setText(items.getAccount_number().toString());
        amountentry.setText(items.getAmount().toString());
        typeentry.setText(items.getType());
        modentry.setText(items.getMode());
        tranoentry.setText(items.getTransaction_number().toString());
        datentey.setText(items.getDate_of_transaction());
    }

    @FXML
    void account_button(ActionEvent event) throws IOException{
        Node node = (Node)event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/Allcontrollers/AllFxmls/account.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @FXML
    void branch_button(ActionEvent event) {

    }

    @FXML
    void customer_button(ActionEvent event) {

    }

    @FXML
    void deletebtn(ActionEvent event) throws SQLException {
        if(event.getSource()==deletebutton){
            deleterecord();
        }
    }

    private void deleterecord() {
        Connection c= getConnection();
//        "delete from table name where name=__";
        String sql="delete from transaction_detail where transaction_number=? and account_number=? and date_of_transaction=? and  mode=?  and type=? and  amount=?";
        try(PreparedStatement stmt=c.prepareStatement(sql)){
            Date date =new Date(0);
            stmt.setInt(1, Integer.parseInt(tranoentry.getText()));
            stmt.setInt(2, Integer.parseInt(accentry.getText()));
            stmt.setDate(3,date.valueOf(datentey.getText()));
            stmt.setString(4,modentry.getText());
            stmt.setString(5,typeentry.getText());
            stmt.setInt(6, Integer.parseInt(amountentry.getText()));
            stmt.execute();
            showtranactionrecord();
        }
        catch(Exception  e){
            printing(e.getMessage(),null,"Error");

        }
    }

    @FXML
    void exit_btn(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    //insert button in transaction database
    void insertbtn(ActionEvent event) throws SQLException {
            if(event.getSource()==insertbutton){
                insertrecord();
            }
    }

    // best way is use prepared statement faster in execution insert record in database
    private void insertrecord() throws SQLException {
        Connection c=getConnection();
        Date date=new Date(0);
        String sql= "insert into transaction_detail values  (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = c.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(tranoentry.getText()));
        pstmt.setInt(2, Integer.parseInt(accentry.getText()));
        pstmt.setDate(3,date.valueOf(datentey.getText()));
        pstmt.setString(4,modentry.getText());
        pstmt.setString(5,typeentry.getText());
        pstmt.setInt(6, Integer.parseInt(amountentry.getText()));
        pstmt.execute();
        showtranactionrecord();
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
    void updatebtn(ActionEvent event) throws SQLException {
            if(event.getSource()==updatebutton){
                update();
            }
    }

    private void update() throws SQLException {
        Connection c= getConnection();
        Date date= new Date(0);
        String sql="Update transaction_detail set account_number=? , " +
                "date_of_transaction=?,mode=?" +
                ",type=?,amount=? " +
                "where transaction_number=?";

        PreparedStatement stmt=c.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(accentry.getText()));
        stmt.setDate(2,date.valueOf(datentey.getText()));
        stmt.setString(3,modentry.getText());
        stmt.setString(4,typeentry.getText());
        stmt.setInt(5, Integer.parseInt(amountentry.getText()));
        stmt.setInt(6, Integer.parseInt(tranoentry.getText()));
        stmt.execute();
        showtranactionrecord();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showtranactionrecord();
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

    private ObservableList<tranctionclass> gettestingrecord() {
        ObservableList<tranctionclass> recordlist= FXCollections.observableArrayList();
        Connection c=getConnection();
        String sql = "select * from bank_144.transaction_detail";
        Statement st = null;
        ResultSet rs = null;
        try{
            st=c.createStatement();
            rs=st.executeQuery(sql);
            tranctionclass tr;
            while(rs.next()){
                tr= new tranctionclass(rs.getInt("transaction_number"),rs.getInt("account_number"),
                        rs.getDate("date_of_transaction").toString(),rs.getString("mode"),
                        rs.getString("type"),rs.getInt("amount"));

                recordlist.add(tr);
            }
        }
        catch(Exception e){
            System.out.println("Error in observable list statement sql");
        }

        return recordlist;
    }

    private void showtranactionrecord() {
        ObservableList<tranctionclass> list= gettestingrecord();

        tnotable.setCellValueFactory(new PropertyValueFactory<tranctionclass,Integer>("transaction_number"));

        accnotable.setCellValueFactory(new PropertyValueFactory<tranctionclass,Integer>("account_number"));

        datetable.setCellValueFactory(new PropertyValueFactory<tranctionclass,String>("date_of_transaction"));

        modetable.setCellValueFactory(new PropertyValueFactory<tranctionclass,String>("mode"));

        typetable.setCellValueFactory(new PropertyValueFactory<tranctionclass,String>("type"));

        amounttable.setCellValueFactory(new PropertyValueFactory<tranctionclass,Integer>("amount"));
        transactiontable.setItems(list);

    }
    //printing msg in database
    public static void printing(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}