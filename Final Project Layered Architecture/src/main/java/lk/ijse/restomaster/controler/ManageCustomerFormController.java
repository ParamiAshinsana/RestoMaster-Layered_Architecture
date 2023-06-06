package lk.ijse.restomaster.controler;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.restomaster.bo.custom.CustomerBO;
import lk.ijse.restomaster.bo.custom.Impl.CustomerBOImpl;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.tm.CustomerTM;
import lk.ijse.restomaster.model.CustomerModel;
import lk.ijse.restomaster.util.Regex;
import lk.ijse.restomaster.util.TextFilds;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageCustomerFormController implements Initializable{

    private final static String URL = "jdbc:mysql://localhost:3306/RestoMaster";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public JFXTextField txtcustmerid;
    public Label lbldate;
    public Label lbltime;
    public Button btnadd;
    public Button btnclear;
    public Button btndelete;
    public Button btnupdate;
    public JFXTextField txtnumber;
    public JFXTextField txtaddress;
    public JFXTextField txtname;
    public JFXTextField txtid;
    public TableColumn <? , ?> colid;
    public TableColumn <? , ?> colname;
    public TableColumn <? , ?> coladdress;
    public TableColumn <? , ?> colnumber;
    public TableView <CustomerTM> tblcustomer;
    public ObservableList <CustomerTM> observableList;
    public JFXTextField textCustId;
    public Label labelCustomerId;
    public Label lblError;

    @SneakyThrows
    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        //lbltime.setText(String.valueOf(LocalTime.now()));
        setCellValueFactory();
        getAll();
        generateNextCustomerID();
    }

    private void generateNextCustomerID() {
        try {
            String nextId = CustomerModel.generateNextCustomerID();
            labelCustomerId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colnumber.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public void textfieldonkeypressed(KeyEvent keyEvent) {
        //txtcustmerid.setStyle("-fx-background-color: white");
    }

    public void btnaddonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }
        String id = labelCustomerId.getText();
        String name = txtname.getText();
        String contact = txtnumber.getText();
        String address = txtaddress.getText();

        CustomerBO customerBO = new CustomerBOImpl();

//            try (Connection con = DriverManager.getConnection(URL, props)) {
//                String sql = "INSERT INTO Customer(CustomerId , CustomerName , CustomerContact , CustomerAddress) VALUES(?, ?, ?, ?)";
//
//                PreparedStatement pstm = con.prepareStatement(sql);
//                pstm.setString(1, labelCustomerId.getText());
//                pstm.setString(2, name);
//                pstm.setString(3, contact);
//                pstm.setString(4, address);
//
//
//                int affectedRows = pstm.executeUpdate();
//                if (affectedRows > 0) {
//                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!!").show();
//                }
//            }
            //new
        //customerBO.addCustomers(new CustomerDTO(id,name,contact,address));
        if(!customerBO.addCustomers(new CustomerDTO(id,name,contact,address))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Customer !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Customer Added!!").show();
        }
            labelCustomerId.setText("");
            txtname.setText("");
            txtnumber.setText("");
            txtaddress.setText("");

        getAll();
        generateNextCustomerID();
    }

    private boolean CustomerNameValidate(JFXTextField txtName){
        boolean isMatch = Pattern.compile("[A-Z][a-z]*").matcher(txtName.getText()).matches();
        if (isMatch){
            return true;
        }else {
            return false;
        }
    }

    @SneakyThrows
    private void getAll() {
          try{
              observableList = FXCollections.observableArrayList();
              List <CustomerDTO> CustomerList = CustomerModel.getAll();

              for(CustomerDTO customer : CustomerList){
                     observableList.add(new CustomerTM(
                             customer.getId(),
                             customer.getName(),
                             customer.getContact(),
                             customer.getAddress()
                     ));
              }
              tblcustomer.setItems(observableList);
          }catch (SQLException e ){
              e.printStackTrace();
              new Alert(Alert.AlertType.ERROR , "Query Error !").show();

          }
    }

    public void btnclearonaction(ActionEvent actionEvent) throws SQLException {
        labelCustomerId.setText("");
        txtname.setText("");
        txtnumber.setText("");
        txtaddress.setText("");
    }

    public void btndeleteonaction(ActionEvent actionEvent) throws SQLException{
        String id = txtid.getText();

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "DELETE FROM Customer WHERE CustomerId = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);

            if(pstm.executeUpdate() > 0 ) {
                new Alert(Alert.AlertType.CONFIRMATION, "Huree!! deleted :)").show();
            }
        }
        getAll();
        labelCustomerId.setText("");

        generateNextCustomerID();
    }

    public void btnupdateonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println("1");
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "  Can not Updated !!").show();
            return;
        }
        System.out.println("2");
        String id = txtid.getText();
        String name = txtname.getText();
        String contact = txtnumber.getText();
        String address = txtaddress.getText();

        System.out.println("3");

        CustomerBO customerBO = new CustomerBOImpl();

        System.out.println("4");
//        try(Connection con = DriverManager.getConnection(URL, props)) {
//            //String sql = "UPDATE Customer SET name = ?, address = ?, salary = ? WHERE id = ?";
//            String sql = "UPDATE Customer SET CustomerName = ? , CustomerContact = ? , CustomerAddress = ? WHERE CustomerId = ?";
//            //"UPDATE Customer SET(CustomerId , CustomerName , CustomerContact , CustomerAddress) VALUES(?, ?, ?, ?)"
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, name);
//            pstm.setString(2, contact);
//            pstm.setString(3, address);
//            pstm.setString(4, id);
//
//            if(pstm.executeUpdate() > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!!").show();
//            }
//        }
        System.out.println("5");

        System.out.println("6");
        //customerBO.updateCustomers(new CustomerDTO(name,contact,address,id));
        if(!customerBO.updateCustomers(new CustomerDTO(id,name,contact,address))){
            System.out.println("7");
            new Alert(Alert.AlertType.ERROR , "Can not Uptaded Customer !").show();
            System.out.println("8");
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Customer Added!!").show();
        }
        System.out.println("9");

        getAll();

        labelCustomerId.setText("");
        txtname.setText("");
        txtnumber.setText("");
        txtaddress.setText("");
        generateNextCustomerID();
        System.out.println("10");

        System.out.println("end");
    }

    public void custableonclicked(MouseEvent mouseEvent) {

        Integer index = tblcustomer.getSelectionModel().getSelectedIndex();
       if (index <= -1) {
            return;
        }
//        System.out.println(">>>>> "+index);
        txtid.setText(colid.getCellData(index).toString());
        txtname.setText(colname.getCellData(index).toString());
        txtnumber.setText(colnumber.getCellData(index).toString());
        txtaddress.setText(coladdress.getCellData(index).toString());
    }

    public void customerContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.PHONE ,txtnumber );
    }

    public void customerAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.ADDRESS , txtaddress);
    }

    public void customerNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.NAME , txtname);
    }

    public boolean isValidated(){
        if (!Regex.setTextColor(TextFilds.PHONE,txtnumber))return false;
        if (!Regex.setTextColor(TextFilds.ADDRESS,txtaddress))return false;
        if (!Regex.setTextColor(TextFilds.NAME,txtname))return false;
        return true;
    }
}
