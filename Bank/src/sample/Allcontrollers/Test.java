package sample.Allcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.AllClasses.testing;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Test implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<testing> testtable;

    @FXML
    private TableColumn<testing, Integer> testid;

    @FXML
    private TableColumn<testing, String> testname;

//    @FXML
//    void initialize(){
//        assert testid != null : "fx:id=\"testid\" was not injected: check your FXML file 'test.fxml'.";
//        assert testname != null : "fx:id=\"testname\" was not injected: check your FXML file 'test.fxml'.";
//        assert testtable != null : "fx:id=\"testtable\" was not injected: check your FXML file 'test.fxml'.";
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showtestingrecord();
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
          System.out.println("error in connection part in testing");
          return null;
        }
//        return c;
    }
    public ObservableList<testing> gettestingrecord() {
        ObservableList<testing> recordlist= FXCollections.observableArrayList();
        Connection c=getConnection();
        String sql = "select * from bank_144.test";
        Statement st = null;
        ResultSet rs = null;
        try{
            st=c.createStatement();
            rs=st.executeQuery(sql);
            testing tests;
            while(rs.next()){
                tests=new testing(rs.getInt("roll_no"),rs.getString("name"));
                recordlist.add(tests);
            }
        }
        catch(Exception e){
            System.out.println("Error in observable list statement sql");
        }

        return recordlist;
    }

    public void showtestingrecord() {
        ObservableList<testing> list= gettestingrecord();
        testid.setCellValueFactory(new PropertyValueFactory<testing,Integer>("id"));
        testname.setCellValueFactory(new PropertyValueFactory<testing,String>("name"));
        testtable.setItems(list);

    }

}
