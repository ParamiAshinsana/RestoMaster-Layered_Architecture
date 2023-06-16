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
import lk.ijse.restomaster.bo.custom.Impl.SupplierBOImpl;
import lk.ijse.restomaster.bo.custom.StockBO;
import lk.ijse.restomaster.bo.custom.SupplierBO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.SupplierDTO;
import lk.ijse.restomaster.dto.tm.CustomerTM;
import lk.ijse.restomaster.dto.tm.SupplierTM;
import lk.ijse.restomaster.model.SupplierModel;
import lk.ijse.restomaster.util.Regex;
import lk.ijse.restomaster.util.TextFilds;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class ManageSupplierFormController implements Initializable {
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.B_SUPPLIERS);

    private final static String URL = "jdbc:mysql://localhost:3306/RestoMaster";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public Button btnAddSupplier;
    public Button btnUpdateSupplier;
    public Button btnDeleteSupplier;
    public Button btnClear;

    public JFXTextField txtSId;
    public JFXTextField txtSname;
    public JFXTextField txtOfferings;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQuantity;
    public JFXTextField txtAddress;
    public JFXTextField txtSMobile;
    public JFXTextField txtEmailAddress;

    public TableColumn <? , ?> colSId;
    public TableColumn <? , ?> colSName;
    public TableColumn <? , ?> colOfferings;
    public TableColumn  <? , ?> colPrice;
    public TableColumn  <? , ?> colQuantity;
    public TableColumn  <? , ?> colCost;
    public TableColumn  <? , ?> colAddress;
    public TableColumn  <? , ?> colMobileNumber;
    public TableColumn  <? , ?> colEAddress;

    public TableView <SupplierTM> tblSupplier;
    public ObservableList<SupplierTM> observableList;
    public Label lblError;
    public Label labelMenuItemCode;
    public Label labelSupplierId;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        generateNextSupplierId();
    }

    private void generateNextSupplierId() throws SQLException, ClassNotFoundException{
        String nextId = supplierBO.generateNewSuppliersID();
        labelSupplierId.setText(nextId);
    }

    private void getAll() {
        tblSupplier.getItems().clear();
        try {
            ArrayList<SupplierDTO> allSuppliers = supplierBO.getAllSuppliers();

            for (SupplierDTO c : allSuppliers) {
                tblSupplier.getItems().add(new SupplierTM(c.getSpId(),c.getSpName(),c.getServiceOfferings(),c.getUnitPrice(),c.getQuantity(),c.getTotal(),c.getAddress(),c.getMobileNumber(),c.getEmail()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void setCellValueFactory() {
        colSId.setCellValueFactory(new PropertyValueFactory<>("spId"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("spName"));
        colOfferings.setCellValueFactory(new PropertyValueFactory<>("serviceOfferings"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colEAddress.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public void btnAddSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }

        String spId = labelSupplierId.getText();
        String spName = txtSname.getText();
        String serviceOfferings = txtOfferings.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        String address = txtAddress.getText();
        String mobileNumber = txtSMobile.getText();
        String email = txtEmailAddress.getText();
        Double  tot = quantity * unitPrice;

        if(!supplierBO.addSuppliers(new SupplierDTO(spId,spName,serviceOfferings,unitPrice,quantity,tot,address,mobileNumber,email))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Supplier !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Supplier Added!!").show();
        }
            txtSname.setText("");
            txtOfferings.setText("");
            txtUnitPrice.setText("");
            txtQuantity.setText("");
            txtAddress.setText("");
            txtSMobile.setText("");
            txtEmailAddress.setText("");

        getAll();
        generateNextSupplierId();
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }

        String spId = txtSId.getText();
        String spName = txtSname.getText();
        String serviceOfferings = txtOfferings.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        String address = txtAddress.getText();
        String mobileNumber = txtSMobile.getText();
        String email = txtEmailAddress.getText();

        Double tot = quantity * unitPrice;

        if(!supplierBO.updateSuppliers(new SupplierDTO(spId,spName,serviceOfferings,unitPrice,quantity,tot,address,mobileNumber,email))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Supplier !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Supplier Added!!").show();
        }

        getAll();
        txtSname.setText("");
        txtOfferings.setText("");
        txtUnitPrice.setText("");
        txtQuantity.setText("");
        txtAddress.setText("");
        txtSMobile.setText("");
        txtEmailAddress.setText("");
        generateNextSupplierId();
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String spId = txtSId.getText();
        if(!supplierBO.deleteSuppliers(txtSId.getText())){
            new Alert(Alert.AlertType.ERROR , "Can not Delete !)").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, " Supplier Deleted :)").show();
        }

        getAll();
        txtSname.setText("");
        txtOfferings.setText("");
        txtUnitPrice.setText("");
        txtQuantity.setText("");
        txtAddress.setText("");
        txtSMobile.setText("");
        txtEmailAddress.setText("");
        generateNextSupplierId();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtSname.setText("");
        txtOfferings.setText("");
        txtUnitPrice.setText("");
        txtQuantity.setText("");
        txtAddress.setText("");
        txtSMobile.setText("");
        txtEmailAddress.setText("");
    }

    public void tblSupplierMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblSupplier.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtSId.setText(colSId.getCellData(index).toString());
        txtSname.setText(colSName.getCellData(index).toString());
        txtOfferings.setText(colOfferings.getCellData(index).toString());
        txtUnitPrice.setText(colPrice.getCellData(index).toString());
        txtQuantity.setText(colQuantity.getCellData(index).toString());
        txtAddress.setText(colAddress.getCellData(index).toString());
        txtSMobile.setText(colMobileNumber.getCellData(index).toString());
        txtEmailAddress.setText(colEAddress.getCellData(index).toString());
    }

    public void supplierNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.NAME , txtSname);
    }

    public void supplierOfferingsOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.NAME , txtOfferings);
    }

    public void supplierUPOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.DOUBLE , txtUnitPrice);
    }

    public void supplierQTYOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.INT , txtQuantity);
    }

    public void supplierAddressOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.ADDRESS , txtAddress);
    }

    public void supplierContactOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.PHONE , txtSMobile);
    }

    public void supplierEMailOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.EMAIL , txtEmailAddress);
    }

    public boolean isValidated(){
        if (!Regex.setTextColor(TextFilds.NAME,txtSname))return false;
        if (!Regex.setTextColor(TextFilds.NAME,txtOfferings))return false;
        if (!Regex.setTextColor(TextFilds.DOUBLE,txtUnitPrice))return false;
        if (!Regex.setTextColor(TextFilds.INT,txtQuantity))return false;
        if (!Regex.setTextColor(TextFilds.ADDRESS,txtAddress))return false;
        if (!Regex.setTextColor(TextFilds.PHONE,txtSMobile))return false;
        if (!Regex.setTextColor(TextFilds.EMAIL,txtEmailAddress))return false;
        return true;
    }
}
