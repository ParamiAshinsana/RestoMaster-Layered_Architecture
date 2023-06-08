package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.StockDAO;
import lk.ijse.restomaster.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Stock entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> loadSuppliersId() throws SQLException, ClassNotFoundException {
        List<String> allStockIds = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT SupplierId FROM Supplier");
        while (rst.next()) {
            String Ids = new String(rst.getString("SupplierId"));
            allStockIds.add(Ids);
        }
        return allStockIds;
    }
}
