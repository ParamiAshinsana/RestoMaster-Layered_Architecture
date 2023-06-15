package lk.ijse.restomaster.dao.custom;

import lk.ijse.restomaster.dao.CrudDAO;
import lk.ijse.restomaster.entity.MenuItem;
import lk.ijse.restomaster.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders> {
   public boolean updateQuantity(int quantity , String menuItems) throws SQLException, ClassNotFoundException;
}
