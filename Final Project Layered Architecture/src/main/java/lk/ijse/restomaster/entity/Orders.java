package lk.ijse.restomaster.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Orders {
    private String orderId;
    private String customerId;
    private String menuItem;
    private String description;
    private double unitPrice;
    private int quantity;
    private double total;
    private String orderDate;
    private String orderTime;
}
