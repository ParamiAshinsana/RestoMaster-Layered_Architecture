package lk.ijse.restomaster.dao.custom;

import lk.ijse.restomaster.dao.CrudDAO;
import lk.ijse.restomaster.entity.Customer;
import lk.ijse.restomaster.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockDAO extends CrudDAO<Stock> {
    public List<String> loadSuppliersId() throws SQLException, ClassNotFoundException;

}
