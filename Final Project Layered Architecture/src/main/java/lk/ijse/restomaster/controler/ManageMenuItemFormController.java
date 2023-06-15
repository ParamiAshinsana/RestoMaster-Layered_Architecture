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
import lk.ijse.restomaster.bo.custom.Impl.MenuItemBOImpl;
import lk.ijse.restomaster.bo.custom.MenuItemBO;
import lk.ijse.restomaster.db.DBConnection;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.EmployeeDTO;
import lk.ijse.restomaster.dto.MenuItemDTO;
import lk.ijse.restomaster.dto.tm.MenuItemTM;
import lk.ijse.restomaster.model.MenuItemModel;
import lk.ijse.restomaster.util.Regex;
import lk.ijse.restomaster.util.TextFilds;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class ManageMenuItemFormController implements Initializable {
    MenuItemBO menuItemBO = new MenuItemBOImpl();


    private final static String URL = "jdbc:mysql://localhost:3306/RestoMaster";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public Button btnaddmenu;
    public Button btnclearmenu;
    public Button btndeletemenu;
    public Button btnupdatemenu;
    public JFXTextField txtitemcode;
    public JFXTextField txtdescription;
    public ComboBox preparationtimeCbox;
    public JFXTextField txtunitprice;
    public JFXTextField txtQuantity;
    public ComboBox itemtypeCbox;
    public ObservableList<MenuItemTM> observableList;
    public TableView <MenuItemTM> tblmenuitem;
    public TableColumn <? , ?> colitemcode;
    public TableColumn <? , ?> colitemtype;
    public TableColumn <? , ?> coldescription;
    public TableColumn <? , ?> colunitprice;
    public TableColumn <? , ?> colQuantity;
    public TableColumn <? , ?> colpretime;
    public Button btnTFMenu;
    public Button btnBeverages;
    public Button btnSFMenu;
    public Label labelMenuItemCode;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();

        ObservableList<String> foodList = FXCollections.observableArrayList("Traditional Foods", "SeaFoods" , "Beverages");
        itemtypeCbox.setItems(foodList);

        ObservableList<String> servingsizeList = FXCollections.observableArrayList("10 - 15 minutes", "15 - 30 minutes " , "20 - 40 minutes" , "Over 40 minutes");
        preparationtimeCbox.setItems(servingsizeList);

        generateMenuItemCode();
    }

    private void generateMenuItemCode() {
        try {
            String nextId = MenuItemModel.generateNextMenuItemCode();
            labelMenuItemCode.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colitemcode.setCellValueFactory(new PropertyValueFactory<>("miCode"));
        colitemtype.setCellValueFactory(new PropertyValueFactory<>("miType"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colpretime.setCellValueFactory(new PropertyValueFactory<>("preTime"));
    }

    public void btnaddmenuonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }

        String miCode = labelMenuItemCode.getText();
        String miType = String.valueOf(itemtypeCbox.getValue());
        String description = txtdescription.getText();
        Double itemUnitPrice = Double.valueOf(txtunitprice.getText());
        int quantity = Integer.valueOf(txtQuantity.getText());
        String preTime = String.valueOf(preparationtimeCbox.getValue());

//        try (Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "INSERT INTO MenuItem(MenuItemCode , MenuItemType , Description , UnitPrice , Quantity , PreparationTime) VALUES(?,? ,? ,?,? , ?)";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, labelMenuItemCode.getText());
//            pstm.setString(2, miType);
//            pstm.setString(3, description);
//            pstm.setDouble(4, itemUnitPrice);
//            pstm.setDouble(5, quantity);
//            pstm.setString(6, preTime);
//
//            int affectedRows = pstm.executeUpdate();
//            if(affectedRows > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Menu Item Added!!").show();
//            }
//        }

        if(!menuItemBO.addMenuItems(new MenuItemDTO(miCode,miType,description,itemUnitPrice,quantity,preTime))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Customer !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Customer Added!!").show();
        }
        getAll();

        //labelMenuItemCode.setText("");
        itemtypeCbox.setValue("");
        txtdescription.setText("");
        txtunitprice.setText("");
        txtQuantity.setText("");
        preparationtimeCbox.setValue("");

        generateMenuItemCode();
    }

    private void getAll() {
        try{
            observableList = FXCollections.observableArrayList();
            List <MenuItemDTO> MenuItemList = MenuItemModel.getAll();

            for(MenuItemDTO menuitem : MenuItemList){
                observableList.add(new MenuItemTM(
                        menuitem.getMiCode(),
                        menuitem.getMiType(),
                        menuitem.getDescription(),
                        menuitem.getItemUnitPrice(),
                        menuitem.getQuantity(),
                        menuitem.getPreTime()
                ));
            }
            tblmenuitem.setItems(observableList);
        }catch (SQLException e ){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR , "Query Error !").show();

        }
    }

    public void btnclearmenuonaction(ActionEvent actionEvent) {
        //labelMenuItemCode.setText("");
        itemtypeCbox.setValue("");
        txtdescription.setText("");
        txtunitprice.setText("");
        txtQuantity.setText("");
        preparationtimeCbox.setValue("");
    }

    public void btndeletemenuonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String miCode = txtitemcode.getText();

//        try (Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "DELETE FROM MenuItem WHERE MenuItemCode = ?";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, txtitemcode.getText());
//
//            if(pstm.executeUpdate() > 0 ) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Huree!! deleted :)").show();
//            }
//        }
        if(!menuItemBO.deleteMenuItems(txtitemcode.getText())){
            new Alert(Alert.AlertType.ERROR , "Can not Delete !)").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, " MenuItem Deleted :)").show();
        }

        getAll();
        //labelMenuItemCode.setText("");
        itemtypeCbox.setValue("");
        txtdescription.setText("");
        txtunitprice.setText("");
        txtQuantity.setText("");
        preparationtimeCbox.setValue("");
        generateMenuItemCode();
    }

    public void btnupdatemenuonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }

        String miCode = txtitemcode.getText();
        String miType = String.valueOf(itemtypeCbox.getValue());
        String description = txtdescription.getText();
        Double itemUnitPrice = Double.valueOf(txtunitprice.getText());
        int quantity = Integer.valueOf(txtQuantity.getText());
        String preTime = String.valueOf(preparationtimeCbox.getValue());

//        try(Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "UPDATE MenuItem SET MenuItemType = ? , Description = ? , UnitPrice = ? , Quantity = ? , PreparationTime = ?  WHERE MenuItemCode = ?";
//            //String sql = "UPDATE Customer SET CustomerName = ? , CustomerContact = ? , CustomerAddress = ? WHERE CustomerId = ?";
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, miType);
//            pstm.setString(2, description);
//            pstm.setDouble(3, itemUnitPrice);
//            pstm.setInt(4, quantity);
//            pstm.setString(5, preTime);
//            pstm.setString(6, miCode);
//
//            if(pstm.executeUpdate() > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated!!").show();
//            }
//        }
        if(!menuItemBO.updateMenuItems(new MenuItemDTO(miCode,miType,description,itemUnitPrice,quantity,preTime))){
            new Alert(Alert.AlertType.ERROR , "Can not Uptaded MenuItem !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "MenuItem Added!!").show();
        }

        getAll();

        txtitemcode.setText("");
        itemtypeCbox.setValue("");
        txtdescription.setText("");
        txtunitprice.setText("");
        txtQuantity.setText("");
        preparationtimeCbox.setValue("");

        generateMenuItemCode();
    }

    public void tblmiMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblmenuitem.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        txtitemcode.setText(colitemcode.getCellData(index).toString());
        itemtypeCbox.setValue(colitemtype.getCellData(index).toString());
        txtdescription.setText(coldescription.getCellData(index).toString());
        txtunitprice.setText(colunitprice.getCellData(index).toString());
        txtQuantity.setText(colQuantity.getCellData(index).toString());
        preparationtimeCbox.setValue(colpretime.getCellData(index).toString());

    }

    public void btnTFMenuOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasDesign = JRXmlLoader.load("src/main/resources/reprt/Menu Items.jrxml");
        JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("MenuItemType","Traditional Foods");

        JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasPrint,false);
    }

    public void btnSFMenuOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasDesign = JRXmlLoader.load("src/main/resources/reprt/SeaFoodMenuItem.jrxml");
        JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("SeaFood","SeaFoods");

        JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasPrint,false);

    }

    public void btnBeveragesOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasDesign = JRXmlLoader.load("src/main/resources/reprt/Beverages.jrxml");
        JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("BeverageMenuItem","Beverages");

        JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasPrint,false);

    }

    public void upOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.DOUBLE , txtunitprice);
    }

    public void qtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.INT , txtQuantity);
    }

    public void descriptionOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.NAME , txtdescription);
    }

    public boolean isValidated(){
        if (!Regex.setTextColor(TextFilds.DOUBLE,txtunitprice))return false;
        if (!Regex.setTextColor(TextFilds.INT,txtQuantity))return false;
        if (!Regex.setTextColor(TextFilds.NAME,txtdescription))return false;
        return true;
    }
}
