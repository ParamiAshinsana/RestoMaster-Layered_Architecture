package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.CustomerBO;
import lk.ijse.restomaster.dao.custom.CustomerDAO;
import lk.ijse.restomaster.dao.custom.Impl.CustomerDAOImpl;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getId(),c.getName(),c.getContact(),c.getAddress()));
        }
        return allCustomers;
    }

    @Override
    public boolean addCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getId(),dto.getName(),dto.getContact(),dto.getAddress()));
    }

    @Override
    public boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        //return customDAO.update(new Customer(dto.getName(),dto.getContact(),dto.getAddress(),dto.getId()));
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getContact(),dto.getAddress()));
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
