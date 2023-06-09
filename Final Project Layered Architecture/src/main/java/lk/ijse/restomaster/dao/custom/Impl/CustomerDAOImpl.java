package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.CustomerDAO;
import lk.ijse.restomaster.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString("CustomerId"),rst.getString("CustomerName"),rst.getString("CustomerContact"),rst.getString("CustomerAddress"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer(CustomerId , CustomerName , CustomerContact , CustomerAddress) VALUES(?, ?, ?, ?)",entity.getId(),entity.getName(),entity.getContact(),entity.getAddress());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET CustomerName = ? , CustomerContact = ? , CustomerAddress = ? WHERE CustomerId = ?",entity.getName(),entity.getContact(),entity.getAddress(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("DELETE FROM Customer WHERE CustomerId=?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CustomerId FROM Customer ORDER BY CustomerId DESC LIMIT 1");
        if(rst.next()) {
            String string = rst.getString(1);
            String[] strings = string.split("C0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "C00" + id;
            } else {
                if (length < 3) {
                    return "C0" + id;
                } else {
                    return "C" + id;
                }
            }
        }else{
            return "COO1";
        }
    }
}

















