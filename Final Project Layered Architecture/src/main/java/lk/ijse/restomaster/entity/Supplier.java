package lk.ijse.restomaster.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    private String spId;
    private String spName;
    private String serviceOfferings;
    private Double unitPrice;
    private int quantity;
    private Double total;
    private String address;
    private String mobileNumber;
    private String email;
}
