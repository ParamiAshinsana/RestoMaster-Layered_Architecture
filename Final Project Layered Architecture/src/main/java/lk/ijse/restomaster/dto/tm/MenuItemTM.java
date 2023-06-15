package lk.ijse.restomaster.dto.tm;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class MenuItemTM {
     private String miCode ;
     private String miType;
     private String description;
     private Double itemUnitPrice ;
     private int quantity;
     private String preTime;
}
