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
import lk.ijse.restomaster.bo.custom.EmployeeBO;
import lk.ijse.restomaster.bo.custom.Impl.CustomerBOImpl;
import lk.ijse.restomaster.bo.custom.Impl.EmployeeBOImpl;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.EmployeeDTO;
import lk.ijse.restomaster.dto.tm.EmployeeTM;
import lk.ijse.restomaster.model.EmployeeModel;
import lk.ijse.restomaster.util.Regex;
import lk.ijse.restomaster.util.TextFilds;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class ManageEmployeeFormController implements Initializable {
    EmployeeBO employeeBO = new EmployeeBOImpl();

    private final static String URL = "jdbc:mysql://localhost:3306/RestoMaster";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public Button btnClear;
    public Button btnDeleteEmployee;
    public Button btnUpdateEmployee;
    public Button btnAddEmployee;
    public JFXTextField txteId;
    public JFXTextField txteName;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNumber;
    public JFXTextField txtDob;
    public JFXTextField txtAge;
    public JFXTextField txtCompensation;
    public ComboBox departmentCBox;
    public ComboBox jobTitleCBox;

    public TableColumn <? , ?> colemId;
    public TableColumn <? , ?> colemName;
    public TableColumn <? , ?> colemAddress;
    public TableColumn <? , ?> colmobile;
    public TableColumn <? , ?> coldob;
    public TableColumn <? , ?> colAge;
    public TableColumn <? , ?> colDepart;
    public TableColumn <? , ?> colTitle;
    public TableColumn <? , ?> colCompen;

    public TableView <EmployeeTM> tblEmployee;
    public ObservableList<EmployeeTM> observableList;
    public Label labelEmpId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();

        ObservableList<String> departmentList = FXCollections.observableArrayList("Front of House Department", "Back of House Department " , "Management Department" , "Kitchen Department" , "Driver  Department" , "Defense Department" , " Clean Department" );
        departmentCBox.setItems(departmentList);

        ObservableList<String> jobTitleList = FXCollections.observableArrayList( "Traditional Food Chef", "Seafood Chef " ,"Cook" , "Super Manager" , "Sub Manager" , "Host" , "Cashier" , "Baristas", "Bartender" , "Dishwasher" , "Food Runner" , "Waiter" , "Driver" , "Cleaner" ,  "Security Officer" );
        jobTitleCBox.setItems(jobTitleList);

        generateNextemployeeId();
    }

    private void generateNextemployeeId() {
        try {
            String nextId = EmployeeModel.generateNextEmployeeID();
            labelEmpId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAll() {
        try{
            observableList = FXCollections.observableArrayList();
            List<EmployeeDTO> EmployeeList = EmployeeModel.getAll();

            for(EmployeeDTO employee : EmployeeList){
                observableList.add(new EmployeeTM(
                        employee.getEmpId(),
                        employee.getEmpName(),
                        employee.getEmpAddress(),
                        employee.getEmpContact(),
                        employee.getEmpAge(),
                        employee.getEmpDob(),
                        employee.getEmpTitle(),
                        employee.getEmpDepartment(),
                        employee.getEmpCompensation()
                ));
            }
            tblEmployee.setItems(observableList);
        }catch (SQLException e ){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR , "Query Error !").show();

        }
    }

    private void setCellValueFactory() {
        colemId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colemName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colemAddress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        colmobile.setCellValueFactory(new PropertyValueFactory<>("empContact"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("empAge"));
        coldob.setCellValueFactory(new PropertyValueFactory<>("empDob"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("empTitle"));
        colDepart.setCellValueFactory(new PropertyValueFactory<>("empDepartment"));
        colCompen.setCellValueFactory(new PropertyValueFactory<>("empCompensation"));
    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }

        String empId = labelEmpId.getText();
        String empName = txteName.getText();
        String empAddress = txtAddress.getText();
        String empContact = txtMobileNumber.getText();
        String empAge = txtAge.getText();
        String empDob = txtDob.getText();
        String empTitle = String.valueOf(jobTitleCBox.getValue());
        String empDepartment = String.valueOf(departmentCBox.getValue());
        Double empCompensation = Double.valueOf(txtCompensation.getText());

//        try (Connection con = DriverManager.getConnection(URL, props)) {
//            String sql = "INSERT INTO Employee(EmployeeId , EmployeeName ,Address  , Contact ,Age  , DOB ,JobTitle  , Department , Compensation  ) VALUES(?, ?, ?, ? , ? ,? , ? , ? , ?)";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, empId);
//            pstm.setString(2, empName);
//            pstm.setString(3, empAddress);
//            pstm.setString(4, empContact);
//            pstm.setString(5, empAge);
//            pstm.setString(6, empDob);
//            pstm.setString(7, empTitle);
//            pstm.setString(8, empDepartment);
//            pstm.setDouble(9, Double.parseDouble(String.valueOf(empCompensation)));
//
//
//            int affectedRows = pstm.executeUpdate();
//            if(affectedRows > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!!").show();
//            }
//        }

        if(!employeeBO.addEmployees(new EmployeeDTO(empId ,empName,empAddress,empContact,empAge,empDob,empTitle,empDepartment,empCompensation))){
            new Alert(Alert.AlertType.ERROR , "Can not Added Employee !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Employee Added!!").show();
        }

        getAll();

        labelEmpId.setText("");
        txteName.setText("");
        txtAddress.setText("");
        txtMobileNumber.setText("");
        txtAge.setText("");
        txtDob.setText("");
        jobTitleCBox.setValue("");
        departmentCBox.setValue("");
        txtCompensation.setText("");

        generateNextemployeeId();
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR, "Invalid Input !").show();
            return;
        }

        String empId = txteId.getText();
        String empName = txteName.getText();
        String empAddress = txtAddress.getText();
        String empContact = txtMobileNumber.getText();
        String empAge = txtAge.getText();
        String empDob = txtDob.getText();
        String empTitle = String.valueOf(jobTitleCBox.getValue());
        String empDepartment = String.valueOf(departmentCBox.getValue());
        Double empCompensation = Double.valueOf(txtCompensation.getText());

//        try(Connection con = DriverManager.getConnection(URL, props)) {
//
//            String sql = "UPDATE Employee SET EmployeeName = ? , Address = ? , Contact = ? , Age = ? , DOB = ? , JobTitle = ? , Department = ? , Compensation = ?  WHERE EmployeeId = ?";
//
//            PreparedStatement pstm = con.prepareStatement(sql);
//
//            pstm.setString(1, empName);
//            pstm.setString(2, empAddress);
//            pstm.setString(3, empContact);
//            pstm.setString(4, empAge);
//            pstm.setString(5, empDob);
//            pstm.setString(6, empTitle);
//            pstm.setString(7, empDepartment);
//            pstm.setDouble(8, Double.parseDouble(String.valueOf(empCompensation)));
//            pstm.setString(9, empId);
//
//            if(pstm.executeUpdate() > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated!!").show();
//            }
//        }

        if(!employeeBO.updateEmployees(new EmployeeDTO(empId,empName,empAddress,empContact,empAge,empDob,empTitle,empDepartment,empCompensation))){
            new Alert(Alert.AlertType.ERROR , "Can not Uptaded Employee !").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION , "Employee Added!!").show();
        }

        getAll();

        labelEmpId.setText("");
        txteName.setText("");
        txtAddress.setText("");
        txtMobileNumber.setText("");
        txtAge.setText("");
        txtDob.setText("");
        jobTitleCBox.setValue("");
        departmentCBox.setValue("");
        txtCompensation.setText("");
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) throws SQLException {
        String empId = txteId.getText();

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "DELETE FROM Employee WHERE EmployeeId = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, txteId.getText());

            if(pstm.executeUpdate() > 0 ) {
                new Alert(Alert.AlertType.CONFIRMATION, "Huree!! deleted :)").show();
            }
        }
        getAll();
        labelEmpId.setText("");
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        labelEmpId.setText("");
        txteName.setText("");
        txtAddress.setText("");
        txtMobileNumber.setText("");
        txtAge.setText("");
        txtDob.setText("");
        jobTitleCBox.setValue("");
        departmentCBox.setValue("");
        txtCompensation.setText("");
    }

    public void tblEmployeeMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblEmployee.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        txteId.setText(colemId.getCellData(index).toString());
        txteName.setText(colemName.getCellData(index).toString());
        txtAddress.setText(colemAddress.getCellData(index).toString());
        txtMobileNumber.setText(colmobile.getCellData(index).toString());
        txtAge.setText(colAge.getCellData(index).toString());
        txtDob.setText(coldob.getCellData(index).toString());
        jobTitleCBox.setValue(colTitle.getCellData(index).toString());
        departmentCBox.setValue(colDepart.getCellData(index).toString());
        txtCompensation.setText(colCompen.getCellData(index).toString());
    }

    public void empNameOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.NAME , txteName);
    }

    public void empaddressOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.ADDRESS , txtAddress);
    }

    public void empContactOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.PHONE , txtMobileNumber);
    }

    public void empAgeOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.INT , txtAge);
    }

    public void empCompensationOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFilds.DOUBLE , txtCompensation);
    }

    public boolean isValidated(){
        if (!Regex.setTextColor(TextFilds.NAME,txteName))return false;
        if (!Regex.setTextColor(TextFilds.ADDRESS,txtAddress))return false;
        if (!Regex.setTextColor(TextFilds.PHONE,txtMobileNumber))return false;
        if (!Regex.setTextColor(TextFilds.INT,txtAge))return false;
        if (!Regex.setTextColor(TextFilds.DOUBLE,txtCompensation))return false;
        return true;
    }

}
