package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.StockBO;
import lk.ijse.restomaster.dao.custom.Impl.StockDAOImpl;
import lk.ijse.restomaster.dao.custom.StockDAO;
import lk.ijse.restomaster.dto.StockDTO;
import lk.ijse.restomaster.dto.SupplierDTO;
import lk.ijse.restomaster.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = new StockDAOImpl();

    @Override
    public ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addStocks(StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockDAO.add(new Stock(dto.getSiCode(),dto.getSiName(),dto.getMaxLevel(),dto.getMinLevel(),dto.getPrDate(),dto.getExDate(),dto.getQuantity(),dto.getUnitPrice(),dto.getTotalCost(),dto.getSpId()));
    }

    @Override
    public boolean updateStocks(StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(dto.getSiCode(),dto.getSiName(),dto.getMaxLevel(),dto.getMinLevel(),dto.getPrDate(),dto.getExDate(),dto.getQuantity(),dto.getUnitPrice(),dto.getTotalCost(),dto.getSpId()));
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
