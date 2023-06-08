package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.SupplierBO;
import lk.ijse.restomaster.dao.custom.CustomerDAO;
import lk.ijse.restomaster.dao.custom.Impl.CustomerDAOImpl;
import lk.ijse.restomaster.dao.custom.Impl.SupplierDAOImpl;
import lk.ijse.restomaster.dao.custom.SupplierDAO;
import lk.ijse.restomaster.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = new SupplierDAOImpl();

    @Override
    public ArrayList<CustomerDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addSuppliers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateSuppliers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewSuppliersID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
