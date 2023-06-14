package lk.ijse.restomaster.bo.custom;

import lk.ijse.restomaster.bo.SuperBO;
import lk.ijse.restomaster.dto.EmployeeDTO;
import lk.ijse.restomaster.dto.MenuItemDTO;
import lk.ijse.restomaster.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MenuItemBO extends SuperBO {
    public ArrayList<MenuItemDTO> getAllMenuItems() throws SQLException, ClassNotFoundException;

    public boolean addMenuItems(MenuItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateMenuItems(MenuItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteMenuItems(String id) throws SQLException, ClassNotFoundException;

    public String generateNewMenuItemsID() throws SQLException, ClassNotFoundException;
}
