package lk.ijse.restomaster.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class EmployeeDTO {
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
