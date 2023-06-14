package lk.ijse.restomaster.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MenuItem {
    private String miCode ;
    private String miType;
    private String description;
    private Double itemUnitPrice ;
    private int quantity;
    private String preTime;
}
