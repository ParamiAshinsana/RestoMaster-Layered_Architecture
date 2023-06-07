package lk.ijse.restomaster.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SupplierDTO {
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
