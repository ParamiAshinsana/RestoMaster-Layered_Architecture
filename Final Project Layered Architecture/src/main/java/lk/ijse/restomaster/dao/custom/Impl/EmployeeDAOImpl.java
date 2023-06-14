package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.CustomerDAO;
import lk.ijse.restomaster.dao.custom.EmployeeDAO;
import lk.ijse.restomaster.entity.Customer;
import lk.ijse.restomaster.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployee = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");
        while (rst.next()) {
            Employee employee = new Employee(rst.getString("EmployeeId"),rst.getString("EmployeeName"),rst.getString("Address"),rst.getString("Contact"),rst.getString("Age"),rst.getString("DOB"),rst.getString("JobTitle"),rst.getString("Department"),rst.getDouble("Compensation"));
            allEmployee.add(employee);
        }
        return allEmployee;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee(EmployeeId , EmployeeName ,Address  , Contact ,Age  , DOB ,JobTitle  , Department , Compensation  ) VALUES(?, ?, ?, ? , ? ,? , ? , ? , ?)",entity.getEmpId(),entity.getEmpName(),entity.getEmpAddress(),entity.getEmpContact(),entity.getEmpAge(),entity.getEmpDob(),entity.getEmpTitle(),entity.getEmpDepartment(),entity.getEmpCompensation());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET EmployeeName = ? , Address = ? , Contact = ? , Age = ? , DOB = ? , JobTitle = ? , Department = ? , Compensation = ?  WHERE EmployeeId = ?",entity.getEmpName(),entity.getEmpAddress(),entity.getEmpContact(),entity.getEmpAge(),entity.getEmpDob(),entity.getEmpTitle(),entity.getEmpDepartment(),entity.getEmpCompensation(),entity.getEmpId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE EmployeeId=?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT EmployeeId FROM Employee ORDER BY EmployeeId DESC LIMIT 1");
        if(rst.next()) {
            String string = rst.getString(1);
            String[] strings = string.split("E0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "E00" + id;
            } else {
                if (length < 3) {
                    return "E0" + id;
                } else {
                    return "E" + id;
                }
            }
        }else{
            return "EOO1";
        }
    }
}
