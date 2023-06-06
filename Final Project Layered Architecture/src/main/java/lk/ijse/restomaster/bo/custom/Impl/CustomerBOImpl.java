package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.CustomerBO;
import lk.ijse.restomaster.dao.custom.CustomDAO;
import lk.ijse.restomaster.dao.custom.Impl.CustomDAOImpl;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomDAO customDAO = new CustomDAOImpl();

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customDAO.add(new Customer(dto.getId(),dto.getName(),dto.getContact(),dto.getAddress()));
    }

    @Override
    public boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        System.out.println("update BOImpl");
        return customDAO.update(new Customer(dto.getName(),dto.getContact(),dto.getAddress(),dto.getId()));
        //return customDAO.update(new Customer(dto.getId(),dto.getName(),dto.getContact(),dto.getAddress()));
    }

    @Override
    public boolean deleteCustomers(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewCustomersID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
