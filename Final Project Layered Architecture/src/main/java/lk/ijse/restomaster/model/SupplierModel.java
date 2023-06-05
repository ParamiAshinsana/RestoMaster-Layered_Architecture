package lk.ijse.restomaster.model;

import lk.ijse.restomaster.db.DBConnection;
import lk.ijse.restomaster.dto.Stock;
import lk.ijse.restomaster.dto.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public static List<Supplier> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Supplier";

        List<Supplier> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            data.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            ));
        }
        return data;

    }

    public static List<String> getIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT SupplierId FROM Supplier";

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;
    }

    public static String generateNextSupplierId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT SupplierId FROM Supplier ORDER BY SupplierId DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if(resultSet.next()) {
            return splitSupplierId(resultSet.getString(1));
        }
        return splitSupplierId(null);
    }

    private static String splitSupplierId(String string) {
        if(string != null) {
            String[] strings = string.split("SP0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "SP00"+id;
            }else {
                if (length < 3){
                    return "SP0"+id;
                }else {
                    return "SP"+id;
                }
            }
        }
        return "SP001";
    }
}
