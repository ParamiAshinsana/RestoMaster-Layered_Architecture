package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.MenuItemDAO;
import lk.ijse.restomaster.dao.custom.OrdersDAO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.OrdersDTO;
import lk.ijse.restomaster.entity.Customer;
import lk.ijse.restomaster.entity.MenuItem;
import lk.ijse.restomaster.entity.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> allOrders = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Orders");
        while (rst.next()) {
            Orders orders = new Orders(rst.getString("OrderId"),rst.getString("CustomerId"),rst.getString("MenuItemId"),rst.getString("Description"),rst.getDouble("UnitPrice"),rst.getInt("Quantity"),rst.getDouble("Total"),rst.getString("OrderDate"),rst.getString("OrderTime"));
            allOrders.add(orders);
        }
        return allOrders;
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

    @Override
    public List<String> loadCustomersId() throws SQLException, ClassNotFoundException {
        List<String> allCustomersIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT CustomerId FROM Customer");
        while (rst.next()) {
            String Ids = new String(rst.getString("CustomerId"));
            allCustomersIds.add(Ids);
        }
        return allCustomersIds;
    }

    @Override
    public List<String> loadMenuItemsId() throws SQLException, ClassNotFoundException {
        List<String> allMenuItemsIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT MenuItemCode FROM MenuItem");
        while (rst.next()) {
            String Ids = new String(rst.getString("MenuItemCode"));
            allMenuItemsIds.add(Ids);
        }
        return allMenuItemsIds;
    }

    @Override
    public boolean searchCustomerMobileNumers(String mobile) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String searchOrder(String contact) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CustomerId FROM Customer WHERE CustomerContact =?", contact);
        if (rst.next()){
            return rst.getString(1);
        }
        return null;
    }
}
