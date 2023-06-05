package lk.ijse.restomaster.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class StockTM {
    private String siCode;
    private String siName;
    private String maxLevel;
    private String minLevel;
    private String prDate;
    private String exDate;
    private int quantity;
    private double unitPrice;
    private double totalCost;
    private String spId;

}
