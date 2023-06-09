package lk.ijse.restomaster.bo.custom;

import lk.ijse.restomaster.bo.SuperBO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.StockDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {
    public ArrayList<StockDTO> getAllStocks()throws SQLException, ClassNotFoundException;

    public boolean addStocks(StockDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateStocks(StockDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean deleteStocks(String id) throws SQLException, ClassNotFoundException;

    public String generateNewStocksID() throws SQLException, ClassNotFoundException;
}
