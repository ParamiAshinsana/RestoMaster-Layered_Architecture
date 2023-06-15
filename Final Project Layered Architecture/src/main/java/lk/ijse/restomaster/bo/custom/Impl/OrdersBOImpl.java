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
        return null;
    }

    @Override
    public boolean addOrders(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.add(new Orders(dto.getOrderId(),dto.getCustomerId(),dto.getMenuItem(),dto.getDescription(),dto.getUnitPrice(),dto.getQuantity(),dto.getTotal(),dto.getOrderDate(),dto.getOrderTime()));
    }

    @Override
    public boolean updateOrders(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteOrders(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
