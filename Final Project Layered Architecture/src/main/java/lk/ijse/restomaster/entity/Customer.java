package lk.ijse.restomaster.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String id ;
    private String name;
    private String contact;
    private String address;
}
