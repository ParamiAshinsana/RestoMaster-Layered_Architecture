package lk.ijse.restomaster.controler;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lk.ijse.restomaster.bo.BOFactory;
import lk.ijse.restomaster.bo.custom.CustomerBO;
import lk.ijse.restomaster.bo.custom.EmployeeBO;
import lk.ijse.restomaster.bo.custom.Impl.EmployeeBOImpl;
import lk.ijse.restomaster.bo.custom.Impl.OrdersBOImpl;
import lk.ijse.restomaster.bo.custom.MenuItemBO;
import lk.ijse.restomaster.bo.custom.OrdersBO;
import lk.ijse.restomaster.dao.DAOFactory;
import lk.ijse.restomaster.dao.custom.Impl.OrdersDAOImpl;
import lk.ijse.restomaster.dao.custom.OrdersDAO;
import lk.ijse.restomaster.dao.custom.StockDAO;
import lk.ijse.restomaster.db.DBConnection;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.MenuItemDTO;
import lk.ijse.restomaster.dto.OrdersDTO;
import lk.ijse.restomaster.dto.tm.MenuItemTM;
import lk.ijse.restomaster.dto.tm.OrdersTM;
import lk.ijse.restomaster.model.MenuItemModel;
import lk.ijse.restomaster.model.OrderModel;
import lk.ijse.restomaster.model.SupplierModel;
import lk.ijse.restomaster.util.Regex;
import lk.ijse.restomaster.util.TextFilds;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

public class ManageOrderFormController implements Initializable {
    OrdersBO ordersBO = (OrdersBO)BOFactory.getBoFactory().getBO(BOFactory.BOTypes.B_ORDERS);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);

    private final static String URL = "jdbc:mysql://localhost:3306/RestoMaster";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public StackPane controlArea;

    public Button btnplaceorder;
    public Button btnAddOrder;
    public Button btnUpdateOrder;
    public Button btnDeleteOrder;
    public Button btnClear;
    public TableColumn <? , ?> colorderid;
    public TableColumn <? , ?> colcustid;
    public TableColumn <? , ?> colmenuitemid;
    public TableColumn <? , ?> coldescription;
    public TableColumn <? , ?> colunitprice;
    public TableColumn <? , ?> colquantity;
    public TableColumn <? , ?> coltotal;
    public TableColumn <? , ?> colorderdate;
    public TableColumn <? , ?> colordertime;

    public JFXTextField txtOrderId;
    public ComboBox custIdCBox;
    public ComboBox micodeCBox;
    public JFXTextField txtDescription;
    public Button btnNewCustomer;
    public Label lblUnitPrice;
    public JFXTextField txtQuantity;
    public DatePicker orderDateCBox;
    public Label lblOrderTime;

    public TableView <OrdersTM> tblOrder;
    public ObservableList<OrdersTM> observableList;
    public Label lblDescription;
    public JFXTextField txtOrderTime;
    public Button btnOrderBill;
    public JFXTextField txtSerachCustId;
    public Label lblCustId;
    public Label lblError;
    public Button btnPlaceOrder;
    public Label labelOrderId;
    public Label labelOrderTime;
    String Date;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
        loadCustomerId();
        loadMenuItemCode();
        generateNextOrderID();
        TimeNow();
    }

    private void generateNextOrderID() throws SQLException, ClassNotFoundException {
        String nextId = ordersBO.generateNewOrderID();
        labelOrderId.setText(nextId);
    }

    private void loadMenuItemCode() throws SQLException, ClassNotFoundException {
        List<String> id = ordersDAO.loadMenuItemsId();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        micodeCBox.setItems(obList);
    }

    private void loadCustomerId() throws SQLException, ClassNotFoundException {
        List<String> id = ordersDAO.loadCustomersId();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        custIdCBox.setItems(obList);

    }

    private void getAll() {
        tblOrder.getItems().clear();
        try {
            ArrayList<OrdersDTO> allOrders = ordersBO.getAllOrders();

            for (OrdersDTO c : allOrders) {
                tblOrder.getItems().add(new OrdersTM(c.getOrderId(),c.getCustomerId(),c.getMenuItem(),c.getDescription(),c.getUnitPrice(),c.getQuantity(),c.getTotal(),c.getOrderDate(),c.getOrderTime()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colorderid.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colcustid.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colmenuitemid.setCellValueFactory(new PropertyValueFactory<>("menuItem"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colorderdate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colordertime.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
    }

    public void btnplaceorderonaction(ActionEvent actionEvent) throws IOException {
        controlArea.setVisible(true);
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/manage_orders_form.fxml"));
        AnchorPane anchorPane = loader.load();
        controlArea.getChildren().removeAll();
        controlArea.getChildren().setAll(anchorPane);
    }

    public void setControllArea(StackPane st) {
            this.controlArea = st ;
    }

    public void btnAddOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }

        String orderId = labelOrderId.getText();
        String customerId = lblCustId.getText();
        String menuItem = String.valueOf(micodeCBox.getValue());
        String description = lblDescription.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        String orderDate = String.valueOf(orderDateCBox.getValue());
        String orderTime = labelOrderTime.getText();

        Double tot = quantity * unitPrice ;

        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            if (!ordersBO.addOrders(new OrdersDTO(orderId, customerId, menuItem, description, unitPrice, quantity, tot, orderDate, orderTime))) {
                new Alert(Alert.AlertType.ERROR, "Can not Added Order !").show();
            } else {
                boolean IsUpdated = ordersDAO.updateQuantity(quantity, menuItem);
                if (IsUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!!").show();
                    con.commit();
                } else {
                    new Alert(Alert.AlertType.ERROR, "SQL ERROR !");
                }
            }
        } catch (SQLException throwables) {
            con.rollback();
            throwables.printStackTrace();
        }finally{
            con.setAutoCommit(true);
        }

        getAll();
        lblCustId.setText("");
        micodeCBox.setValue("");
        lblDescription.setText("");
        lblUnitPrice.setText("");
        txtQuantity.setText("");
        orderDateCBox.setValue(null);

        generateNextOrderID();
    }

    public void btnUpdateOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }

        String orderId = txtOrderId.getText();
        String customerId = lblCustId.getText();
        String menuItem = String.valueOf(micodeCBox.getValue());
        String description = lblDescription.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());
        String orderDate = String.valueOf(orderDateCBox.getValue());
        String orderTime = labelOrderTime.getText();

        Double tot = quantity * unitPrice ;

        if(!ordersBO.updateOrders(new OrdersDTO(orderId, customerId, menuItem, description, unitPrice, quantity, tot, orderDate, orderTime))){
            new Alert(Alert.AlertType.ERROR , "Can not Uptaded Order !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Order Added!!").show();
        }

        getAll();
        lblCustId.setText("");
        micodeCBox.setValue("");
        lblDescription.setText("");
        lblUnitPrice.setText("");
        txtQuantity.setText("");
        orderDateCBox.setValue(null);
        generateNextOrderID();
    }

    public void btnDeleteOrederOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orderId = txtOrderId.getText();

        if(!ordersBO.deleteOrders(txtOrderId.getText())){
            new Alert(Alert.AlertType.ERROR , "Can not Delete !)").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, " Order Deleted :)").show();
        }
        getAll();
        lblCustId.setText("");
        micodeCBox.setValue("");
        lblDescription.setText("");
        lblUnitPrice.setText("");
        txtQuantity.setText("");
        orderDateCBox.setValue(null);

        generateNextOrderID();
    }

    public void btnClearOrderOnAction(ActionEvent actionEvent) {
        lblCustId.setText("");
        micodeCBox.setValue("");
        lblDescription.setText("");
        lblUnitPrice.setText("");
        txtQuantity.setText("");
        orderDateCBox.setValue(null);
    }

    public void tblOrderMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblOrder.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        txtOrderId.setText(colorderid.getCellData(index).toString());
        lblCustId.setText(colcustid.getCellData(index).toString());
        micodeCBox.setValue(colmenuitemid.getCellData(index).toString());
        lblDescription.setText(coldescription.getCellData(index).toString());
        lblUnitPrice.setText(colunitprice.getCellData(index).toString());
        txtQuantity.setText(colquantity.getCellData(index).toString());
        orderDateCBox.setValue(LocalDate.parse(colorderdate.getCellData(index).toString()));
    }

    @SneakyThrows
    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/manage_customer_form.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void fillItemFields(MenuItemDTO item) {
        lblUnitPrice.setText(String.valueOf(item.getItemUnitPrice()));
        lblDescription.setText(item.getDescription());
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(micodeCBox.getSelectionModel().getSelectedItem());

        try {
            MenuItemDTO menuitem = MenuItemModel.searchById(code);
            if(menuitem==null)return;
            fillItemFields(menuitem);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void btnOrderBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        String CusID = lblCustId.getText();
        String date = String.valueOf(orderDateCBox.getValue());

        JasperDesign jasDesign = JRXmlLoader.load("src/main/resources/reprt/Order.jrxml");
        JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("CustID",CusID);
        data.put("Date",date);
        data.put("NetTotal",NetTotalCalculate(CusID,date));

        JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasPrint,false);
    }

    private Object NetTotalCalculate(String cusID, String date) throws SQLException {
        OrdersDTO product = OrderModel.searchById(cusID);

        try (Connection con = DriverManager.getConnection(URL, props)) {
            double tot = 0;

            String sql = "SELECT SUM(Total) FROM orders WHERE CustomerId = ? AND OrderDate = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,cusID);
            pstm.setString(2,date);

            ResultSet affectedRows = pstm.executeQuery();
            while (affectedRows.next()){
                double c = affectedRows.getInt(1);
                tot=tot+c;
            }
            return String.valueOf(tot);
        }
    }

    public void txtSerachCustIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String contact = txtSerachCustId.getText();

        String cusMobile = ordersDAO.searchOrder(contact);
        if (cusMobile != null){
            lblCustId.setText(cusMobile);
        }else {
            new Alert(Alert.AlertType.WARNING,"Try again", ButtonType.OK).show();
        }
    }

        public void custIdCBoxOnAction(ActionEvent actionEvent) {
        Object C = custIdCBox.getValue();
        String CusID = String.valueOf(C);
        lblCustId.setText(CusID);
        lblError.setText("");
    }

    public void cbCustIdOnMouseClicked(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        loadCustomerId();
    }

    private void TimeNow(){
        Thread thread = new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            while (!false){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() ->{
                    labelOrderTime.setText(timenow);
                });
            }
        });
        thread.start();
    }

    public void quantityOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.INT, txtQuantity);
   }

   public boolean isValidated(){
        if (!Regex.setTextColor(TextFilds.INT,txtQuantity))return false;
       return true;
   }
}
