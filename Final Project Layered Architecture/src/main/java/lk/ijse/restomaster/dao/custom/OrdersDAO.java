package lk.ijse.restomaster.dao.custom;

import lk.ijse.restomaster.dao.CrudDAO;
import lk.ijse.restomaster.entity.MenuItem;
import lk.ijse.restomaster.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDAO extends CrudDAO<Orders> {
   public boolean updateQuantity(int quantity , String menuItems) throws SQLException, ClassNotFoundException;
   public List<String> loadCustomersId() throws SQLException, ClassNotFoundException;
   public List<String> loadMenuItemsId() throws SQLException, ClassNotFoundException;
   public boolean searchCustomerMobileNumers()throws SQLException, ClassNotFoundException;
}
