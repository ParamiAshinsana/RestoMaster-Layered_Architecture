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
import lk.ijse.restomaster.bo.custom.Impl.StockBOImpl;
import lk.ijse.restomaster.bo.custom.StockBO;
import lk.ijse.restomaster.dao.DAOFactory;
import lk.ijse.restomaster.dao.custom.Impl.StockDAOImpl;
import lk.ijse.restomaster.dao.custom.StockDAO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.StockDTO;
import lk.ijse.restomaster.dto.tm.CustomerTM;
import lk.ijse.restomaster.dto.tm.StockTM;
import lk.ijse.restomaster.model.StockModel;
import lk.ijse.restomaster.model.SupplierModel;
import lk.ijse.restomaster.util.Regex;
import lk.ijse.restomaster.util.TextFilds;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class ManageStockFormController implements Initializable {

    StockBO stockBO = new StockBOImpl();
    StockDAO stockDAO = new StockDAOImpl();
    //StockBO stockBO = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.B_STOCKS);
    //StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCKS);

    private final static String URL = "jdbc:mysql://localhost:3306/RestoMaster";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public TableColumn <? , ?> colmax;
    public TableColumn <? , ?>  colmin;
    public TableColumn <? , ?>  colprdate;
    public TableColumn <? , ?>  colexdate;
    public TableColumn <? , ?>  coltotal;
    public TableColumn <? , ?>  colquantity;
    public TableColumn <? , ?> colsiprice;
    public TableColumn <? , ?>  colsiname;
    public TableColumn <? , ?>  colstid;
    public TableColumn <? , ?> colsupplierId;

    public Button btnclear;
    public Button btndeletestock;
    public Button btnaddstock;
    public Button btngettotal;
    public Button btnupdatestock;
    public JFXTextField txtmaxlevel;
    public JFXTextField txtminlevel;
    public JFXTextField txtprdate;
    public JFXTextField txtexdate;
    public JFXTextField txtquantity;
    public JFXTextField txtunitprice;
    public JFXTextField txtstockname;
    public JFXTextField txtstockid;
    public Label lbltotal;
    public DatePicker prDatePicker;
    public DatePicker exDatePicker;

    public TableView <StockTM> tblstock;
    public ObservableList<StockTM> observableList;
    public ComboBox supllierIdCBox;
    public Label labelMenuItemCode;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        loadSupplierId();
        generateNextStockId();
    }

    private void generateNextStockId() throws SQLException, ClassNotFoundException {
//        try {
//            String nextId = StockModel.generateNextStockID();
//            labelMenuItemCode.setText(nextId);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        String nextId = stockBO.generateNewStocksID();
        labelMenuItemCode.setText(nextId);
    }

    private void loadSupplierId() throws SQLException, ClassNotFoundException {
//        try {
//            List<String> ids = SupplierModel.getIds();
//            ObservableList<String> obList = FXCollections.observableArrayList();
//
//            for (String id : ids) {
//                obList.add(id);
//            }
//            supllierIdCBox.setItems(obList);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
//        }

        List<String> id = stockDAO.loadSuppliersId();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        supllierIdCBox.setItems(obList);
    }

    private void getAll() {
//        try{
//            observableList = FXCollections.observableArrayList();
//            List<StockDTO> StockList = StockModel.getAll();
//            for(StockDTO stock : StockList){
//                observableList.add(new StockTM(
//                        stock.getSiCode(),
//                        stock.getSiName(),
//                        stock.getMaxLevel(),
//                        stock.getMinLevel(),
//                        stock.getPrDate(),
//                        stock.getExDate(),
//                        stock.getQuantity(),
//                        stock.getUnitPrice(),
//                        stock.getTotalCost(),
//                        stock.getSpId()
//                ));
//            }
//            tblstock.setItems(observableList);
//        }catch (SQLException e ){
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR , "Query Error !").show();
//
//        }
        tblstock.getItems().clear();
        try {
            ArrayList<StockDTO> allCustomers = stockBO.getAllStocks();

            for (StockDTO c : allCustomers) {
                tblstock.getItems().add(new StockTM(c.getSiCode(),c.getSiName(),c.getMaxLevel(),c.getMinLevel(),c.getPrDate(),c.getExDate(),c.getQuantity(),c.getUnitPrice(),c.getTotalCost(),c.getSpId()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {

        colstid.setCellValueFactory(new PropertyValueFactory<>("siCode"));
        colsiname.setCellValueFactory(new PropertyValueFactory<>("siName"));
        colmax.setCellValueFactory(new PropertyValueFactory<>("maxLevel"));
        colmin.setCellValueFactory(new PropertyValueFactory<>("minLevel"));
        colprdate.setCellValueFactory(new PropertyValueFactory<>("prDate"));
        colexdate.setCellValueFactory(new PropertyValueFactory<>("exDate"));
        colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colsiprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colsupplierId.setCellValueFactory(new PropertyValueFactory<>("spId"));

    }

    public void btnaddonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Can't Added Stock!!").show();
            return;
        }

        String siCode = labelMenuItemCode.getText();
        String siName = txtstockname.getText();
        String maxLevel = txtmaxlevel.getText();
        String minLevel = txtminlevel.getText();
        String prDate = String.valueOf(prDatePicker.getValue());
        String exDate = String.valueOf(exDatePicker.getValue());
        int quantity = Integer.parseInt(txtquantity.getText());
        Double unitPrice = Double.valueOf(txtunitprice.getText());
        //Double totalCost = Double.valueOf(lbltotal.getText());
        String spId = String.valueOf(supllierIdCBox.getValue());

        Double tot = quantity * unitPrice;

//        try (Connection con = DriverManager.getConnection(URL, props)) {
//
//            String sql = "INSERT INTO Stock(StockItemCode , StockItemName , MaxStockLevel , MinStockLevel , PurchaseDate , ExpirationDate , Quantity , UnitPrice , TotalCost , SpId ) VALUES(?,?,?,?,?,?,?,?,?,?)";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, labelMenuItemCode.getText());
//            pstm.setString(2, siName);
//            pstm.setString(3, maxLevel);
//            pstm.setString(4, minLevel);
//            pstm.setString(5, prDate);
//            pstm.setString(6, exDate);
//            pstm.setInt(7, Integer.parseInt(String.valueOf(quantity)));
//            pstm.setDouble(8, Double.parseDouble(String.valueOf(unitPrice)));
//            pstm.setDouble(9, tot);
//            pstm.setString(10, spId);
//
//            int affectedRows = pstm.executeUpdate();
//            if(affectedRows > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Stock Added!!").show();
//            }
//        }

        if(!stockBO.addStocks(new StockDTO(siCode,siName,maxLevel,minLevel,prDate,exDate,quantity ,unitPrice, tot, spId))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Stock !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Stock Added!!").show();
        }

        getAll();

        //labelMenuItemCode.setText("");
        txtstockname.setText("");
        txtmaxlevel.setText("");
        txtminlevel.setText("");
        prDatePicker.setValue(null);
        exDatePicker.setValue(null);
        txtquantity.setText("");
        txtunitprice.setText("");
        //lbltotal.setText("");
        supllierIdCBox.setValue(null);

        generateNextStockId();
    }

    public void tblstockMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblstock.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        txtstockid.setText(colstid.getCellData(index).toString());
        txtstockname.setText(colsiname.getCellData(index).toString());
        txtmaxlevel.setText(colmax.getCellData(index).toString());
        txtminlevel.setText(colmin.getCellData(index).toString());
        prDatePicker.setValue(LocalDate.parse(colprdate.getCellData(index).toString()));
        exDatePicker.setValue(LocalDate.parse(colexdate.getCellData(index).toString()));
        txtquantity.setText(colquantity.getCellData(index).toString());
        txtunitprice.setText(colsiprice.getCellData(index).toString());
        //lbltotal.setText(coltotal.getCellData(index).toString());
        supllierIdCBox.setValue(colsupplierId.getCellData(index).toString());
    }

    public void btnclearonaction(ActionEvent actionEvent){
        //labelMenuItemCode.setText("");
        txtstockname.setText("");
        txtmaxlevel.setText("");
        txtminlevel.setText("");
        prDatePicker.setValue(null);
        exDatePicker.setValue(null);
        txtquantity.setText("");
        txtunitprice.setText("");
        supllierIdCBox.setValue(null);
        //lbltotal.setText("");
    }

    public void btndeleteonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String siCode = txtstockid.getText();

//        try (Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "DELETE FROM Stock WHERE StockItemCode = ?";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, txtstockid.getText());
//
//            if(pstm.executeUpdate() > 0 ) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Huree!! deleted :)").show();
//            }
//        }

        if(!stockBO.deleteStocks(txtstockid.getText())){
            new Alert(Alert.AlertType.ERROR , "Can not Delete !)").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, " Stock Deleted :)").show();
        }

        getAll();
        txtstockname.setText("");
        txtmaxlevel.setText("");
        txtminlevel.setText("");
        prDatePicker.setValue(null);
        exDatePicker.setValue(null);
        txtquantity.setText("");
        txtunitprice.setText("");
        supllierIdCBox.setValue(null);
        generateNextStockId();
    }

    public void btnupdateonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Customer Added!!").show();
            return;
        }

        String siCode = txtstockid.getText();
        String siName = txtstockname.getText();
        String maxLevel = txtmaxlevel.getText();
        String minLevel = txtminlevel.getText();
        String prDate = String.valueOf(prDatePicker.getValue());
        String exDate = String.valueOf(exDatePicker.getValue());
        int quantity = Integer.parseInt(txtquantity.getText());
        Double unitPrice = Double.valueOf(txtunitprice.getText());
        //Double totalCost = quantity * unitPrice;
        String spId = String.valueOf(supllierIdCBox.getValue());


//        try(Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "UPDATE Stock SET StockItemName = ? , MaxStockLevel = ? , MinStockLevel = ? , PurchaseDate = ? , ExpirationDate = ? , Quantity = ? , UnitPrice = ? , TotalCost = ? , SpId = ? WHERE StockItemCode = ?";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, siName);
//            pstm.setString(2, maxLevel);
//            pstm.setString(3, minLevel);
//            pstm.setString(4, prDate);
//            pstm.setString(5, exDate);
//            pstm.setInt(6, Integer.parseInt(String.valueOf(quantity)));
//            pstm.setDouble(7, Double.parseDouble(String.valueOf(unitPrice)));
//            pstm.setDouble(8, Double.parseDouble(String.valueOf(totalCost)));
//            pstm.setString(9, spId);
//            pstm.setString(10, siCode);
//
//
//            if(pstm.executeUpdate() > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Stock Updated!!").show();
//            }
//        }
       Double tot = quantity * unitPrice;

        if(!stockBO.updateStocks(new StockDTO(siCode,siName,maxLevel,minLevel,prDate,exDate,quantity,unitPrice,tot,spId))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Stock !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Stock Added!!").show();
        }

        getAll();

        //labelMenuItemCode.setText("");
        txtstockid.setText("");
        txtstockname.setText("");
        txtmaxlevel.setText("");
        txtminlevel.setText("");
        prDatePicker.setValue(null);
        exDatePicker.setValue(null);
        txtquantity.setText("");
        txtunitprice.setText("");
        //lbltotal.setText("");
        supllierIdCBox.setValue(null);
        generateNextStockId();

    }

    public void btngettotalOnAction(ActionEvent actionEvent) {
        int quantity = Integer.parseInt(txtquantity.getText());
        Double unitPrice = Double.valueOf(txtunitprice.getText());

        //lbltotal = (double)quantity * unitPrice;

        Double tot = quantity * unitPrice;
        lbltotal.setText(String.valueOf(tot));

    }

    public void stockNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.NAME , txtstockname);
    }

    public void unitPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.DOUBLE , txtunitprice);
    }

    public void qtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.INT , txtquantity);
    }

    public void maxLevelOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.INT , txtmaxlevel);
    }

    public void minLevelOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.INT , txtminlevel);
    }

    public boolean isValidated(){
        if (!Regex.setTextColor(TextFilds.NAME,txtstockname))return false;
        if (!Regex.setTextColor(TextFilds.DOUBLE,txtunitprice))return false;
        if (!Regex.setTextColor(TextFilds.INT,txtquantity))return false;
        if (!Regex.setTextColor(TextFilds.INT,txtmaxlevel))return false;
        if (!Regex.setTextColor(TextFilds.INT,txtminlevel))return false;
        return true;
    }
}
