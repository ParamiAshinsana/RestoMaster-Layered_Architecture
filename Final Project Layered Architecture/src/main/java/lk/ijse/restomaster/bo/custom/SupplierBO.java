package lk.ijse.restomaster.bo.custom;

import lk.ijse.restomaster.bo.SuperBO;
import lk.ijse.restomaster.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public ArrayList<CustomerDTO> getAllSuppliers()throws SQLException, ClassNotFoundException;

    public boolean addSuppliers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateSuppliers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException;

    public String generateNewSuppliersID() throws SQLException, ClassNotFoundException;
}
