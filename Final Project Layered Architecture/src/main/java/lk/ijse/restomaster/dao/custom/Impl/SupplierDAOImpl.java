package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.SQLUtil;
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
        //return SQLUtil.execute("INSERT INTO Customer(CustomerId , CustomerName , CustomerContact , CustomerAddress) VALUES(?, ?, ?, ?)",entity.getId(),entity.getName(),entity.getContact(),entity.getAddress());
         return SQLUtil.execute("INSERT INTO Supplier( SupplierId , SupplierName , ServiceOfferings , UnitPrice , Quantity , Total , Address , MobileNumber , EmailAddress ) VALUES(?,? ,? ,?, ?,?,?,?,?)", entity.getSpId(),entity.getSpName(),entity.getServiceOfferings(),entity.getUnitPrice(),entity.getQuantity(),entity.getTotal(),entity.getAddress(),entity.getMobileNumber(),entity.getEmail());
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
