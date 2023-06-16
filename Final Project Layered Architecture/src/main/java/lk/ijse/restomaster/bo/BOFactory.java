package lk.ijse.restomaster.bo;

import lk.ijse.restomaster.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes{
        B_CUSTOMERS , B_SUPPLIERS , B_STOCKS , B_MENUITEMS , B_EMPLOYEES, B_ORDERS
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case B_CUSTOMERS:
                return new CustomerBOImpl();
            case B_SUPPLIERS:
                return new SupplierBOImpl();
            case B_STOCKS:
                return new StockBOImpl();
            case B_EMPLOYEES:
                return new EmployeeBOImpl();
            case B_MENUITEMS:
                return new MenuItemBOImpl();
            case B_ORDERS:
                return new OrdersBOImpl();
            default:
                return null;
        }
    }
}

