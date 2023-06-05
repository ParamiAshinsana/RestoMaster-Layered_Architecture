package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.CustomDAO;
import lk.ijse.restomaster.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomDAOImpl implements CustomDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer(CustomerId , CustomerName , CustomerContact , CustomerAddress) VALUES(?, ?, ?, ?)",entity.getId(),entity.getName(),entity.getContact(),entity.getAddress());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
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
