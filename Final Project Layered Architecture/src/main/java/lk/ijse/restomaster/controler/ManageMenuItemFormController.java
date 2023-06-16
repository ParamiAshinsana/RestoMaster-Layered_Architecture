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
import lk.ijse.restomaster.bo.custom.EmployeeBO;
import lk.ijse.restomaster.bo.custom.Impl.CustomerBOImpl;
import lk.ijse.restomaster.bo.custom.Impl.MenuItemBOImpl;
import lk.ijse.restomaster.bo.custom.MenuItemBO;
import lk.ijse.restomaster.db.DBConnection;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.EmployeeDTO;
import lk.ijse.restomaster.dto.MenuItemDTO;
import lk.ijse.restomaster.dto.tm.EmployeeTM;
import lk.ijse.restomaster.dto.tm.MenuItemTM;
import lk.ijse.restomaster.model.MenuItemModel;
import lk.ijse.restomaster.util.Regex;
import lk.ijse.restomaster.util.TextFilds;
import lombok.SneakyThrows;
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
    MenuItemBO menuItemBO = (MenuItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.B_MENUITEMS);

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


    @SneakyThrows
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

    private void generateMenuItemCode() throws SQLException, ClassNotFoundException {
        String nextId = menuItemBO.generateNewMenuItemsID();
        labelMenuItemCode.setText(nextId);
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

        if(!menuItemBO.addMenuItems(new MenuItemDTO(miCode,miType,description,itemUnitPrice,quantity,preTime))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Customer !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Customer Added!!").show();
        }
        getAll();

        itemtypeCbox.setValue("");
        txtdescription.setText("");
        txtunitprice.setText("");
        txtQuantity.setText("");
        preparationtimeCbox.setValue("");

        generateMenuItemCode();
    }

    private void getAll() {
        tblmenuitem.getItems().clear();
        try {
            ArrayList<MenuItemDTO> allMenuItems = menuItemBO.getAllMenuItems();

            for (MenuItemDTO c : allMenuItems) {
                tblmenuitem.getItems().add(new MenuItemTM(c.getMiCode(),c.getMiType(),c.getDescription(),c.getItemUnitPrice(),c.getQuantity(),c.getPreTime()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnclearmenuonaction(ActionEvent actionEvent) {
        itemtypeCbox.setValue("");
        txtdescription.setText("");
        txtunitprice.setText("");
        txtQuantity.setText("");
        preparationtimeCbox.setValue("");
    }

    public void btndeletemenuonaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String miCode = txtitemcode.getText();

        if(!menuItemBO.deleteMenuItems(txtitemcode.getText())){
            new Alert(Alert.AlertType.ERROR , "Can not Delete !)").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, " MenuItem Deleted :)").show();
        }

        getAll();
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

        if(!menuItemBO.updateMenuItems(new MenuItemDTO(miCode,miType,description,itemUnitPrice,quantity,preTime))){
            new Alert(Alert.AlertType.ERROR , "Can not Uptaded MenuItem !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "MenuItem Uptaded!!").show();
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
