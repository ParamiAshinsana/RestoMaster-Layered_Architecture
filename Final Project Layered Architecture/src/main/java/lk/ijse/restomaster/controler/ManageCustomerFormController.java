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
import lk.ijse.restomaster.bo.BOFactory;
import lk.ijse.restomaster.bo.custom.CustomerBO;
import lk.ijse.restomaster.bo.custom.Impl.CustomerBOImpl;
import lk.ijse.restomaster.dao.DAOFactory;
import lk.ijse.restomaster.dao.custom.CustomerDAO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManageCustomerFormController implements Initializable{
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.B_CUSTOMERS);

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

    private void generateNextCustomerID() throws SQLException, ClassNotFoundException {
        String nextId = customerBO.generateNewCustomersID();
        labelCustomerId.setText(nextId);
    }

    void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colnumber.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public void btnaddonaction(ActionEvent actionEvent) throws SQLException,ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }
        String id = labelCustomerId.getText();
        String name = txtname.getText();
        String contact = txtnumber.getText();
        String address = txtaddress.getText();

        if(!customerBO.addCustomers(new CustomerDTO(id,name,contact,address))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Customer !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Customer Added!!").show();
        }
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
        tblcustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();

            for (CustomerDTO c : allCustomers) {
                tblcustomer.getItems().add(new CustomerTM(c.getId(),c.getName(),c.getContact(),c.getAddress()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnclearonaction(ActionEvent actionEvent) throws SQLException {
        txtname.setText("");
        txtnumber.setText("");
        txtaddress.setText("");
    }

    public void btndeleteonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtid.getText();

        if(!customerBO.deleteCustomers(txtid.getText())){
            new Alert(Alert.AlertType.ERROR , "Can not Delete !)").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, " Customer Deleted :)").show();
        }

        getAll();
        txtname.setText("");
        txtnumber.setText("");
        txtaddress.setText("");

        generateNextCustomerID();
    }

    public void btnupdateonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "  Can not Updated !!").show();
            return;
        }
        String id = txtid.getText();
        String name = txtname.getText();
        String contact = txtnumber.getText();
        String address = txtaddress.getText();

        if(!customerBO.updateCustomers(new CustomerDTO(id,name,contact,address))){
            new Alert(Alert.AlertType.ERROR , "Can not Uptaded Customer !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Customer Added!!").show();
        }

        getAll();
        txtname.setText("");
        txtnumber.setText("");
        txtaddress.setText("");
        generateNextCustomerID();
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
