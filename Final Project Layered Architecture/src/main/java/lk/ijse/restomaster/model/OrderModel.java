package lk.ijse.restomaster.model;

import lk.ijse.restomaster.db.DBConnection;
import lk.ijse.restomaster.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    public static List<OrdersDTO> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Orders";

        List<OrdersDTO> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            data.add(new OrdersDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6),
                    resultSet.getDouble(7),
                    resultSet.getString(8),
                    resultSet.getString(9)

            ));
        }
        return data;

    }

    public static List<String> getCustomerIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT CustomerId FROM Customer";

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }

    public static List<String> getMenuItemCodes() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT MenuItemCode FROM MenuItem";

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }

    public static OrdersDTO searchById(String cusID) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM orders WHERE CustomerId = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, cusID);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new OrdersDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6),
                    resultSet.getDouble(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }
        return null;
    }

    public static boolean UpdateQuntity(int quantity, String menuItem) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "UPDATE menuitem SET Quantity = (Quantity - ?) WHERE MenuItemCode = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, quantity);
        pstm.setString(2, menuItem);

        return pstm.executeUpdate() > 0;
    }
    
    public static String generateNextOrderID() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT OrderId FROM Orders ORDER BY OrderId DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("OR0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "OR00"+id;
            }else {
                if (length < 3){
                    return "OR0"+id;
                }else {
                    return "OR"+id;
                }
            }
        }
        return "OR001";
    }
}
