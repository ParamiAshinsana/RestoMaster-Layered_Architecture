package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.SuperBO;
import lk.ijse.restomaster.bo.custom.CustomerBO;
import lk.ijse.restomaster.bo.custom.MenuItemBO;
import lk.ijse.restomaster.dao.custom.EmployeeDAO;
import lk.ijse.restomaster.dao.custom.Impl.EmployeeDAOImpl;
import lk.ijse.restomaster.dao.custom.Impl.MenuItemDAOImpl;
import lk.ijse.restomaster.dao.custom.MenuItemDAO;
import lk.ijse.restomaster.dto.EmployeeDTO;
import lk.ijse.restomaster.dto.MenuItemDTO;
import lk.ijse.restomaster.entity.Employee;
import lk.ijse.restomaster.entity.MenuItem;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemBOImpl implements MenuItemBO {
    MenuItemDAO menuItemDAO = new MenuItemDAOImpl();

    @Override
    public ArrayList<MenuItemDTO> getAllMenuItems() throws SQLException, ClassNotFoundException {
        ArrayList<MenuItemDTO> allMenuItems= new ArrayList<>();
        ArrayList<MenuItem> all = menuItemDAO.getAll();
        for (MenuItem c : all) {
            allMenuItems.add(new MenuItemDTO(c.getMiCode(),c.getMiType(),c.getDescription(),c.getItemUnitPrice(),c.getQuantity(),c.getPreTime()));
        }
        return allMenuItems;
    }

    @Override
    public boolean addMenuItems(MenuItemDTO dto) throws SQLException, ClassNotFoundException {
        return menuItemDAO.add(new MenuItem(dto.getMiCode(),dto.getMiType(),dto.getDescription(),dto.getItemUnitPrice(),dto.getQuantity(),dto.getPreTime()));
    }

    @Override
    public boolean updateMenuItems(MenuItemDTO dto) throws SQLException, ClassNotFoundException {
        return menuItemDAO.update(new MenuItem(dto.getMiCode(),dto.getMiType(),dto.getDescription(),dto.getItemUnitPrice(),dto.getQuantity(),dto.getPreTime()));
    }

    @Override
    public boolean deleteMenuItems(String id) throws SQLException, ClassNotFoundException {
        return menuItemDAO.delete(id);
    }

    @Override
    public String generateNewMenuItemsID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
