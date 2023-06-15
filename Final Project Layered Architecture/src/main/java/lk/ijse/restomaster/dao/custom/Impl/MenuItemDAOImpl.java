package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.CrudDAO;
import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.CustomerDAO;
import lk.ijse.restomaster.dao.custom.MenuItemDAO;
import lk.ijse.restomaster.entity.Employee;
import lk.ijse.restomaster.entity.MenuItem;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemDAOImpl implements MenuItemDAO {
    @Override
    public ArrayList<MenuItem> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(MenuItem entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO MenuItem(MenuItemCode , MenuItemType , Description , UnitPrice , Quantity , PreparationTime) VALUES(?,? ,? ,?,? , ?)",entity.getMiCode(),entity.getMiType(),entity.getDescription(),entity.getItemUnitPrice(),entity.getQuantity(),entity.getPreTime());
    }

    @Override
    public boolean update(MenuItem entity) throws SQLException, ClassNotFoundException {
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
}
