package lk.ijse.restomaster.dao.custom.Impl;

import lk.ijse.restomaster.dao.CrudDAO;
import lk.ijse.restomaster.dao.SQLUtil;
import lk.ijse.restomaster.dao.custom.CustomerDAO;
import lk.ijse.restomaster.dao.custom.MenuItemDAO;
import lk.ijse.restomaster.entity.Employee;
import lk.ijse.restomaster.entity.MenuItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemDAOImpl implements MenuItemDAO {
    @Override
    public ArrayList<MenuItem> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<MenuItem> allMenuItems = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM MenuItem");
        while (rst.next()) {
            MenuItem menuItem = new MenuItem(rst.getString("MenuItemCode"),rst.getString("MenuItemType"),rst.getString("Description"),rst.getDouble("UnitPrice"),rst.getInt("Quantity"),rst.getString("PreparationTime"));
            allMenuItems.add(menuItem);
        }
        return allMenuItems;
    }

    @Override
    public boolean add(MenuItem entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO MenuItem(MenuItemCode , MenuItemType , Description , UnitPrice , Quantity , PreparationTime) VALUES(?,? ,? ,?,? , ?)",entity.getMiCode(),entity.getMiType(),entity.getDescription(),entity.getItemUnitPrice(),entity.getQuantity(),entity.getPreTime());
    }

    @Override
    public boolean update(MenuItem entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE MenuItem SET MenuItemType = ? , Description = ? , UnitPrice = ? , Quantity = ? , PreparationTime = ? WHERE MenuItemCode = ?",entity.getMiType(),entity.getDescription(),entity.getItemUnitPrice(),entity.getQuantity(),entity.getPreTime(),entity.getMiCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM MenuItem WHERE MenuItemCode=?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
