package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.StockBO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.StockDTO;
import lk.ijse.restomaster.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {

    @Override
    public ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addStocks(StockDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateStocks(StockDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteStocks(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewStocksID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
