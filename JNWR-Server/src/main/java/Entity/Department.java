package Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department extends DBEntity implements Serializable{
    @Id
    @Column(name = "departmentCode", nullable = false, unique = true)
    String departmentCode;

    @Column(name = "name", nullable = false)
    String name;

    public Department(){}

    public Department(String departmentCode, String name) {
        this.departmentCode = departmentCode;
        this.name = name;
    }

    //region Getters and Setters

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion
    

    @Override
    public String toString() {
        return 
            "\n departmentCode= " + getDepartmentCode() +
            ", name=" + getName();
    }

}
