package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.SupplierBO;
import lk.ijse.restomaster.dao.custom.CustomerDAO;
import lk.ijse.restomaster.dao.custom.Impl.CustomerDAOImpl;
import lk.ijse.restomaster.dao.custom.Impl.SupplierDAOImpl;
import lk.ijse.restomaster.dao.custom.SupplierDAO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.SupplierDTO;
import lk.ijse.restomaster.entity.Customer;
import lk.ijse.restomaster.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = new SupplierDAOImpl();

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addSuppliers(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(dto.getSpId(),dto.getSpName(),dto.getServiceOfferings(),dto.getUnitPrice(),dto.getQuantity(),dto.getTotal(),dto.getAddress(),dto.getMobileNumber(),dto.getEmail()));
    }

    @Override
    public boolean updateSuppliers(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSpId(),dto.getSpName(),dto.getServiceOfferings(),dto.getUnitPrice(),dto.getQuantity(),dto.getTotal(),dto.getAddress(),dto.getMobileNumber(),dto.getEmail()));
    }

    @Override
    public boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public String generateNewSuppliersID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
