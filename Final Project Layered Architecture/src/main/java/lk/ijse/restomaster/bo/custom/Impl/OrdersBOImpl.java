package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.MenuItemBO;
import lk.ijse.restomaster.bo.custom.OrdersBO;
import lk.ijse.restomaster.dao.custom.EmployeeDAO;
import lk.ijse.restomaster.dao.custom.Impl.EmployeeDAOImpl;
import lk.ijse.restomaster.dao.custom.Impl.OrdersDAOImpl;
import lk.ijse.restomaster.dao.custom.OrdersDAO;
import lk.ijse.restomaster.dto.MenuItemDTO;
import lk.ijse.restomaster.dto.OrdersDTO;
import lk.ijse.restomaster.entity.MenuItem;
import lk.ijse.restomaster.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    OrdersDAO ordersDAO = new OrdersDAOImpl();

    @Override
    public ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<OrdersDTO> allOrders= new ArrayList<>();
        ArrayList<Orders> all = ordersDAO.getAll();
        for (Orders c : all) {
            allOrders.add(new OrdersDTO(c.getOrderId(),c.getCustomerId(),c.getMenuItem(),c.getDescription(),c.getUnitPrice(),c.getQuantity(),c.getTotal(),c.getOrderDate(),c.getOrderTime()));
        }
        return allOrders;
    }

    @Override
    public boolean addOrders(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.add(new Orders(dto.getOrderId(),dto.getCustomerId(),dto.getMenuItem(),dto.getDescription(),dto.getUnitPrice(),dto.getQuantity(),dto.getTotal(),dto.getOrderDate(),dto.getOrderTime()));
    }

    @Override
    public boolean updateOrders(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.update(new Orders(dto.getOrderId(),dto.getCustomerId(),dto.getMenuItem(),dto.getDescription(),dto.getUnitPrice(),dto.getQuantity(),dto.getTotal(),dto.getOrderDate(),dto.getOrderTime()));
    }

    @Override
    public boolean deleteOrders(String id) throws SQLException, ClassNotFoundException {
        return ordersDAO.delete(id);
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return ordersDAO.generateNewId();
    }
}
