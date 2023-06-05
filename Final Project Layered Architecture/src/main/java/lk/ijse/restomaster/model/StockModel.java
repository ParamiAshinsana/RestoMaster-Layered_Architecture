package lk.ijse.restomaster.model;

import lk.ijse.restomaster.db.DBConnection;
import lk.ijse.restomaster.dto.Stock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockModel {

    public static List<Stock> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Stock";

        List<Stock> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            data.add(new Stock(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getInt(7),
                    resultSet.getDouble(8),
                    resultSet.getDouble(9),
                    resultSet.getString(10)
            ));
        }
        return data;

    }

    public static String generateNextStockID() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT StockItemCode FROM Stock ORDER BY StockItemCode DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("S0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "S00"+id;
            }else {
                if (length < 3){
                    return "S0"+id;
                }else {
                    return "S"+id;
                }
            }
        }
        return "S001";
    }

}
