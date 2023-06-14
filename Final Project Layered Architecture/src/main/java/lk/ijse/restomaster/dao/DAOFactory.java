package lk.ijse.restomaster.dao;

import lk.ijse.restomaster.dao.custom.Impl.CustomerDAOImpl;
import lk.ijse.restomaster.dao.custom.Impl.StockDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory ;

    public DAOFactory() {
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
         CUSTOMERS , ORDERS , ORDER_SELECTIONS , MENU_ITEMS , SUPPLIERS , STOCKS , EMPLOYEES
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case CUSTOMERS:
                return new CustomerDAOImpl();
//            case ORDERS:
//                return new CustomerDAOImpl();
//            case ORDER_SELECTIONS:
//                return new CustomerDAOImpl();
//            case MENU_ITEMS:
//                return new CustomerDAOImpl();
            case SUPPLIERS:
                return new CustomerDAOImpl();
            case STOCKS:
                return new StockDAOImpl();
//            case EMPLOYEES:
//                return new CustomerDAOImpl();
              default:
                  return null ;
        }
    }
}
