package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.EmployeeBO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.EmployeeDTO;
import lk.ijse.restomaster.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addEmployees(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateEmployees(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteEmployees(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewEmployeesID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
