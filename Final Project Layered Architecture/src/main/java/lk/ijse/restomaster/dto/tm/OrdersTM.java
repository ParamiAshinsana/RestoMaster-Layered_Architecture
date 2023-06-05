package lk.ijse.restomaster.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrdersTM {
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
