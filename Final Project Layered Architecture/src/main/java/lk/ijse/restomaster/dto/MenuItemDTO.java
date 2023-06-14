package lk.ijse.restomaster.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MenuItemDTO {
    private String miCode ;
    private String miType;
    private String description;
    private Double itemUnitPrice ;
    private int quantity;
    private String preTime;
}
