package lk.ijse.restomaster.dao;

import lk.ijse.restomaster.dao.custom.Impl.*;

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
            case ORDERS:
                return new OrdersDAOImpl();
//            case ORDER_SELECTIONS:
//                return new OrderSelectionDAOImpl();
            case MENU_ITEMS:
                return new MenuItemDAOImpl();
            case SUPPLIERS:
                return new SupplierDAOImpl();
            case STOCKS:
                return new StockDAOImpl();
            case EMPLOYEES:
                return new CustomerDAOImpl();
              default:
                  return null ;
        }
    }
}
