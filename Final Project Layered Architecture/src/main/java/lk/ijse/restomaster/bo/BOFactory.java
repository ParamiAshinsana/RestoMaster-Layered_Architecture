package lk.ijse.restomaster.bo;

import lk.ijse.restomaster.bo.custom.Impl.CustomerBOImpl;
import lk.ijse.restomaster.bo.custom.Impl.SupplierBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOTypes{
        B_CUSTOMERS , B_SUPPLIERS
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case B_CUSTOMERS:
                return new CustomerBOImpl();
            case B_SUPPLIERS:
                return new SupplierBOImpl();
            default:
                return null;
        }
    }
}

