package lk.ijse.restomaster.bo.custom;

import lk.ijse.restomaster.bo.SuperBO;
import lk.ijse.restomaster.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public ArrayList<CustomerDTO> getAllCustomers()throws SQLException, ClassNotFoundException;

    public boolean addCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean deleteCustomers(String id) throws SQLException, ClassNotFoundException;

    public String generateNewCustomersID() throws SQLException, ClassNotFoundException;

}
