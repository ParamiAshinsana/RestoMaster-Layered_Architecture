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
import lk.ijse.restomaster.bo.custom.Impl.SupplierBOImpl;
import lk.ijse.restomaster.bo.custom.SupplierBO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.SupplierDTO;
import lk.ijse.restomaster.dto.tm.CustomerTM;
import lk.ijse.restomaster.dto.tm.SupplierTM;
import lk.ijse.restomaster.model.SupplierModel;
import lk.ijse.restomaster.util.Regex;
import lk.ijse.restomaster.util.TextFilds;

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

    SupplierBO supplierBO = new SupplierBOImpl();

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        generateNextSupplierId();
    }

    private void generateNextSupplierId() {
        try {
            String nextId = SupplierModel.generateNextSupplierId();
            labelSupplierId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAll() {
//        try{
//            observableList = FXCollections.observableArrayList();
//            List<SupplierDTO> SupplierList = SupplierModel.getAll();
//
//            for(SupplierDTO supplier : SupplierList){
//                observableList.add(new SupplierTM(
//                        supplier.getSpId(),
//                        supplier.getSpName(),
//                        supplier.getServiceOfferings(),
//                        supplier.getUnitPrice(),
//                        supplier.getQuantity(),
//                        supplier.getTotal(),
//                        supplier.getAddress(),
//                        supplier.getMobileNumber(),
//                        supplier.getEmail()
//                ));
//            }
//            tblSupplier.setItems(observableList);
//        }catch (SQLException e ){
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR , "Query Error !").show();
//        }
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

//            try (Connection con = DriverManager.getConnection(URL, props)) {
//                String sql = "INSERT INTO Supplier( SupplierId , SupplierName , ServiceOfferings , UnitPrice , Quantity , Total , Address , MobileNumber , EmailAddress ) VALUES(?,? ,? ,?, ?,?,?,?,?)";
//
//                PreparedStatement pstm = con.prepareStatement(sql);
//                pstm.setString(1, spId);
//                pstm.setString(2, spName);
//                pstm.setString(3, serviceOfferings);
//                pstm.setDouble(4, Double.parseDouble(String.valueOf(unitPrice)));
//                pstm.setInt(5, quantity);
//                pstm.setDouble(6, tot);
//                pstm.setString(7, address);
//                pstm.setString(8, mobileNumber);
//                pstm.setString(9, email);
//
//                int affectedRows = pstm.executeUpdate();
//                if (affectedRows > 0) {
//                    new Alert(Alert.AlertType.CONFIRMATION, "Stock Added!!").show();
//                }
//            }

        if(!supplierBO.addSuppliers(new SupplierDTO(spId,spName,serviceOfferings,unitPrice,quantity,tot,address,mobileNumber,email))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Supplier !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Supplier Added!!").show();
        }
            labelSupplierId.setText("");
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

//        try(Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "UPDATE Supplier SET SupplierName = ? , ServiceOfferings = ? , UnitPrice = ? , Quantity = ? , Total = ? , Address = ? , MobileNumber = ? , EmailAddress = ?  WHERE SupplierId = ?";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//
//            pstm.setString(1, spName);
//            pstm.setString(2, serviceOfferings);
//            pstm.setDouble(3, Double.parseDouble(String.valueOf(unitPrice)));
//            pstm.setInt(4, quantity);
//            pstm.setDouble(5, tot);
//            pstm.setString(6, address);
//            pstm.setString(7, mobileNumber);
//            pstm.setString(8, email);
//            pstm.setString(9, spId);
//            if(pstm.executeUpdate() > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated!!").show();
//            }
//        }
        if(!supplierBO.updateSuppliers(new SupplierDTO(spId,spName,serviceOfferings,unitPrice,quantity,tot,address,mobileNumber,email))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Supplier !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Supplier Added!!").show();
        }

        getAll();
        labelSupplierId.setText("");
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

//        try (Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "DELETE FROM Supplier WHERE SupplierId = ?";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, txtSId.getText());
//
//            if(pstm.executeUpdate() > 0 ) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Huree!! deleted :)").show();
//            }
//        }
        if(!supplierBO.deleteSuppliers(txtSId.getText())){
            new Alert(Alert.AlertType.ERROR , "Can not Delete !)").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, " Supplier Deleted :)").show();
        }

        getAll();
        labelSupplierId.setText("");
        generateNextSupplierId();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        //labelSupplierId.setText("");
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
