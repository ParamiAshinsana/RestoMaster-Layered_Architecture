package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.custom.StockBO;
import lk.ijse.restomaster.dao.custom.Impl.StockDAOImpl;
import lk.ijse.restomaster.dao.custom.StockDAO;
import lk.ijse.restomaster.dto.CustomerDTO;
import lk.ijse.restomaster.dto.StockDTO;
import lk.ijse.restomaster.dto.SupplierDTO;
import lk.ijse.restomaster.entity.Customer;
import lk.ijse.restomaster.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = new StockDAOImpl();

    @Override
    public ArrayList<StockDTO> getAllStocks() throws SQLException, ClassNotFoundException {
        ArrayList<StockDTO> allStocks= new ArrayList<>();
        ArrayList<Stock> all = stockDAO.getAll();
        for (Stock c : all) {
            allStocks.add(new StockDTO(c.getSiCode(),c.getSiName(),c.getMaxLevel(),c.getMinLevel(),c.getPrDate(),c.getExDate(),c.getQuantity(),c.getUnitPrice(),c.getTotalCost(),c.getSpId()));
        }
        return allStocks;
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
        return stockDAO.delete(id);
    }

    @Override
    public String generateNewStocksID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
