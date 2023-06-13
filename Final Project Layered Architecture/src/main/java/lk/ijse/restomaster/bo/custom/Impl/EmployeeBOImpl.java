package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.EmployeeBO;
import lk.ijse.restomaster.dao.custom.EmployeeDAO;
import lk.ijse.restomaster.dao.custom.Impl.EmployeeDAOImpl;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.EmployeeDTO;
import lk.ijse.restomaster.entity.Customer;
import lk.ijse.restomaster.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addEmployees(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getEmpId(),dto.getEmpName(),dto.getEmpAddress(),dto.getEmpContact(),dto.getEmpAge(),dto.getEmpDob(),dto.getEmpTitle(),dto.getEmpDepartment(),dto.getEmpCompensation()));
    }

    @Override
    public boolean updateEmployees(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmpId(),dto.getEmpName(),dto.getEmpAddress(),dto.getEmpContact(),dto.getEmpAge(),dto.getEmpDob(),dto.getEmpTitle(),dto.getEmpDepartment(),dto.getEmpCompensation()));
    }

    @Override
    public boolean deleteEmployees(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public String generateNewEmployeesID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
