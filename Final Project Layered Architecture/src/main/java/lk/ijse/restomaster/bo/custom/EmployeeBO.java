package lk.ijse.restomaster.bo.custom;

import lk.ijse.restomaster.bo.SuperBO;
import lk.ijse.restomaster.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;

    public boolean addEmployees(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateEmployees(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteEmployees(String id) throws SQLException, ClassNotFoundException ;

    public String generateNewEmployeesID() throws SQLException, ClassNotFoundException;
}
