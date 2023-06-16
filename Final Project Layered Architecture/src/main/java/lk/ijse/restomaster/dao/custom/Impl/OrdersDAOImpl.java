package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.MenuItemDAO;
import lk.ijse.restomaster.dao.custom.OrdersDAO;
import lk.ijse.restomaster.entity.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Orders entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Orders (OrderId , CustomerId , MenuItemId , Description , UnitPrice , Quantity , Total , OrderDate , OrderTime ) VALUES(?, ?, ?, ? ,? , ? ,? , ? , ? )",entity.getOrderId(),entity.getCustomerId(),entity.getMenuItem(),entity.getDescription(),entity.getUnitPrice(),entity.getQuantity(),entity.getTotal(),entity.getOrderDate(),entity.getOrderTime());
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Orders SET CustomerId = ? , MenuItemId = ? , Description = ? , UnitPrice = ? , Quantity = ? ,Total = ? , OrderDate = ? ,OrderTime = ? WHERE OrderId = ?",entity.getCustomerId(),entity.getMenuItem(),entity.getDescription(),entity.getUnitPrice(),entity.getQuantity(),entity.getTotal(),entity.getOrderDate(),entity.getOrderTime(),entity.getOrderId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Orders WHERE OrderId=?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT OrderId FROM Orders ORDER BY OrderId DESC LIMIT 1");
        if(rst.next()) {
            String string = rst.getString(1);
            String[] strings = string.split("OR0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "OR00" + id;
            } else {
                if (length < 3) {
                    return "OR0" + id;
                } else {
                    return "OR" + id;
                }
            }
        }else{
            return "OROO1";
        }
    }

    @Override
    public boolean updateQuantity(int quantity, String menuItems) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE menuitem SET Quantity = (Quantity - ?) WHERE MenuItemCode = ?",quantity,menuItems);
    }
}
