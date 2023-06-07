package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.custom.SupplierDAO;
import lk.ijse.restomaster.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
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
