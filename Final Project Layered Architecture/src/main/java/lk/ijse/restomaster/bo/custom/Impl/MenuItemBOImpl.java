package lk.ijse.restomaster.bo.custom.Impl;

import lk.ijse.restomaster.bo.SuperBO;
import lk.ijse.restomaster.bo.custom.CustomerBO;
import lk.ijse.restomaster.bo.custom.MenuItemBO;
import lk.ijse.restomaster.dto.MenuItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MenuItemBOImpl implements MenuItemBO {

    @Override
    public ArrayList<MenuItemDTO> getAllMenuItems() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addMenuItems(MenuItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateMenuItems(MenuItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteMenuItems(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewMenuItemsID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
