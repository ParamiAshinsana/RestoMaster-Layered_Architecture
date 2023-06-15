package lk.ijse.restomaster.bo.custom;

import lk.ijse.restomaster.bo.SuperBO;
import lk.ijse.restomaster.dto.MenuItemDTO;
import lk.ijse.restomaster.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersBO extends SuperBO {
    public ArrayList<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException;

    public boolean addOrders(OrdersDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateOrders(OrdersDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteOrders(String id) throws SQLException, ClassNotFoundException;

    public String generateNewOrderID() throws SQLException, ClassNotFoundException;
}
