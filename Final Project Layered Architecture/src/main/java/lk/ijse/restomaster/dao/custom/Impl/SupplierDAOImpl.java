package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.SupplierDAO;
import lk.ijse.restomaster.entity.Customer;
import lk.ijse.restomaster.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSUppliers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier");
        while (rst.next()) {
            Supplier supplier = new Supplier(rst.getString("SupplierId"),rst.getString("SupplierName"),rst.getString("ServiceOfferings"),rst.getDouble("UnitPrice"),rst.getInt("Quantity"),rst.getDouble("Total"),rst.getString("Address"),rst.getString("MobileNumber"),rst.getString("EmailAddress"));
            allSUppliers.add(supplier);
        }
        return allSUppliers;
    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {
         return SQLUtil.execute("INSERT INTO Supplier( SupplierId , SupplierName , ServiceOfferings , UnitPrice , Quantity , Total , Address , MobileNumber , EmailAddress ) VALUES(?,? ,? ,?, ?,?,?,?,?)", entity.getSpId(),entity.getSpName(),entity.getServiceOfferings(),entity.getUnitPrice(),entity.getQuantity(),entity.getTotal(),entity.getAddress(),entity.getMobileNumber(),entity.getEmail());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("UPDATE Supplier SET SupplierName = ? , ServiceOfferings = ? , UnitPrice = ? , Quantity = ? , Total = ? , Address = ? , MobileNumber = ? , EmailAddress = ?  WHERE SupplierId = ?" , entity.getSpName(),entity.getServiceOfferings(),entity.getUnitPrice(),entity.getQuantity(),entity.getTotal(),entity.getAddress(),entity.getMobileNumber(),entity.getEmail(),entity.getSpId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE SupplierId=?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT SupplierId FROM Supplier ORDER BY SupplierId DESC LIMIT 1");
        if(rst.next()) {
            String string = rst.getString(1);
            String[] strings = string.split("SP0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "SP00" + id;
            } else {
                if (length < 3) {
                    return "SP0" + id;
                } else {
                    return "SP" + id;
                }
            }
        }else{
            return "SP001";
        }
    }
}
