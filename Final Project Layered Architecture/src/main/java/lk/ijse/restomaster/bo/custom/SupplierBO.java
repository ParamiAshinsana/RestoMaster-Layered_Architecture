package lk.ijse.restomaster.bo.custom;

import lk.ijse.restomaster.bo.SuperBO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public ArrayList<SupplierDTO> getAllSuppliers()throws SQLException, ClassNotFoundException;

    public boolean addSuppliers(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateSuppliers(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean deleteSuppliers(String id) throws SQLException, ClassNotFoundException;

    public String generateNewSuppliersID() throws SQLException, ClassNotFoundException;
}
