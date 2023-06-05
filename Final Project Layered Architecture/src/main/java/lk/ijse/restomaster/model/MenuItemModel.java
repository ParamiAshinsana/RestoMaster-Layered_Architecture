package lk.ijse.restomaster.model;

import lk.ijse.restomaster.db.DBConnection;
import lk.ijse.restomaster.dto.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemModel {
    public static List<MenuItem> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM MenuItem";

        List<MenuItem> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            data.add(new MenuItem(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)

            ));
        }
        return data;

    }

    public static MenuItem searchById(String code) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM MenuItem WHERE MenuItemCode = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new MenuItem(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            );
            }
        return null;
    }

    public static String generateNextMenuItemCode() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT MenuItemCode FROM MenuItem ORDER BY MenuItemCode DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if(resultSet.next()) {
            return splitMenuItemCode(resultSet.getString(1));
        }
        return splitMenuItemCode(null);
    }

    private static String splitMenuItemCode(String string) {
        if(string != null) {
            String[] strings = string.split("I0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "I00"+id;
            }else {
                if (length < 3){
                    return "I0"+id;
                }else {
                    return "I"+id;
                }
            }
        }
        return "I001";
    }
}
