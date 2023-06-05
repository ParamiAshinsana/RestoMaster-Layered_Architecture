package lk.ijse.restomaster.dao;

import lk.ijse.restomaster.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    public ArrayList<T> getAll()throws SQLException, ClassNotFoundException;

    public boolean add(T entity) throws SQLException, ClassNotFoundException ;

    public boolean update(T entity) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

}


