package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.CustomerDAO;
import lk.ijse.restomaster.dao.custom.EmployeeDAO;
import lk.ijse.restomaster.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee(EmployeeId , EmployeeName ,Address  , Contact ,Age  , DOB ,JobTitle  , Department , Compensation  ) VALUES(?, ?, ?, ? , ? ,? , ? , ? , ?)",entity.getEmpId(),entity.getEmpName(),entity.getEmpAddress(),entity.getEmpContact(),entity.getEmpAge(),entity.getEmpDob(),entity.getEmpTitle(),entity.getEmpDepartment(),entity.getEmpCompensation());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
