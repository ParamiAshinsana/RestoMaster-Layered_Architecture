package lk.ijse.restomaster.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private String empId;
    private String empName;
    private String empAddress;
    private String empContact;
    private String empAge;
    private String empDob;
    private String empTitle;
    private String empDepartment;
    private Double empCompensation;
}
