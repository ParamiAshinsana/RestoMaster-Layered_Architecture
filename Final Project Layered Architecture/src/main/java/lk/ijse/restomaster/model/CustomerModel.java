package lk.ijse.restomaster.model;

import lk.ijse.restomaster.db.DBConnection;
import lk.ijse.restomaster.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public static List<CustomerDTO> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Customer";

        List<CustomerDTO> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while(resultSet.next()){
             data.add(new CustomerDTO(
                     resultSet.getString(1),
                     resultSet.getString(2),
                     resultSet.getString(3),
                     resultSet.getString(4)
             ));
        }
        return data;

    }

    public static String generateNextCustomerID() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT CustomerId FROM Customer ORDER BY CustomerId DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("C0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "C00"+id;
            }else {
                if (length < 3){
                    return "C0"+id;
                }else {
                    return "C"+id;
                }
            }
        }
        return "C001";
    }
}
