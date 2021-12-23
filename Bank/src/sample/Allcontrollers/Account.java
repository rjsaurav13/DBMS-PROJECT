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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.AllClasses.accountclass;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Account implements Initializable {
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private TableColumn<accountclass, Integer> accnocolumn;

    @FXML
    private TableColumn<accountclass,String> accountstatuscolumn;

    @FXML
    private TableView<accountclass> accounttable;

    @FXML
    private Button branch_btn;

    @FXML
    private TableColumn<accountclass,Integer > branchcolumn;

    @FXML
    private Button customer_btn;

    @FXML
    private TableColumn<accountclass,Integer > customercolumn;

    @FXML
    private TableColumn<accountclass, String> datecolumn;

    @FXML
    private Button deletebutton;

    @FXML
    private TextField enteramount;

    @FXML
    private TextField enterdate;

    @FXML
    private TextField enterstatus;

    @FXML
    private TextField entertype;

    @FXML
    private TextField entryaccno;

    @FXML
    private TextField entrybranchno;

    @FXML
    private TextField entrycusno;

    @FXML
    private Button exit;

    @FXML
    private Button insertbutton;

    @FXML
    private Button loan_btn;

    @FXML
    private TableColumn<accountclass, Integer> openingcolumn;

    @FXML
    private Button transection_btn;

    @FXML
    private TableColumn<accountclass, String> typecolumn;

    @FXML
    private Button updatebutton;


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

    @FXML
    void insetbtn(ActionEvent event) throws SQLException {
        Connection c=getConnection();
        String sql ="insert into account values(?,?,?,?,?,?,?)";
        PreparedStatement stmt=c.prepareStatement(sql);
        stmt.setInt(1,Integer.parseInt(entryaccno.getText()));
        stmt.setInt(2,Integer.parseInt(entrycusno.getText()));
        stmt.setInt(3,Integer.parseInt(entrybranchno.getText()));
        stmt.setInt(4,Integer.parseInt(enteramount.getText()));
        stmt.setDate(5,Date.valueOf(enterdate.getText()));
        stmt.setString(6,entertype.getText());
        stmt.setString(7,enterstatus.getText());
        stmt.execute();
        showaccountrecord();
    }

    @FXML
    void branch_button(ActionEvent event) {

    }

    @FXML
    void customer_button(ActionEvent event) {

    }

    @FXML
    void deletebtn(ActionEvent event) {


    }

    @FXML
    void updatebtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showaccountrecord();
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


    private ObservableList<accountclass> getaccountrecord() {
        ObservableList<accountclass> recordlist= FXCollections.observableArrayList();
        Connection c=getConnection();
        String sql = "select * from bank_144.account";
        Statement st = null;
        ResultSet rs = null;
        try{
            st=c.createStatement();
            rs=st.executeQuery(sql);
            accountclass tr;
            while(rs.next()){
                tr= new accountclass(
                        rs.getInt("account_number"),
                        rs.getInt("customer_id"),
                        rs.getInt("branch_id"),
                        rs.getInt("opening_amount"),
                        rs.getDate("opening_date").toString(),
                        rs.getString("account_type"),
                        rs.getString("account_status")
                );

                recordlist.add(tr);
            }
        }
        catch(Exception e){
            System.out.println("Error in observable list statement sql");
        }
        return recordlist;
    }

    private void showaccountrecord() {
        ObservableList<accountclass> list= getaccountrecord();

        accnocolumn.setCellValueFactory(new PropertyValueFactory<accountclass,Integer>("account_number"));

        customercolumn.setCellValueFactory(new PropertyValueFactory<accountclass,Integer>("customer_id"));

        branchcolumn.setCellValueFactory(new PropertyValueFactory<accountclass,Integer>("branch_id"));

        openingcolumn.setCellValueFactory(new PropertyValueFactory<accountclass,Integer>("opening_amount"));

        datecolumn.setCellValueFactory(new PropertyValueFactory<accountclass,String>("opening_date"));

        typecolumn.setCellValueFactory(new PropertyValueFactory<accountclass,String>("account_type"));


        accountstatuscolumn.setCellValueFactory(new PropertyValueFactory<accountclass,String>("account_status"));

        accounttable.setItems(list);

    }

}
