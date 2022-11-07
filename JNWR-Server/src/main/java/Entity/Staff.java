package Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff extends DBEntity implements Serializable{
    @Id
    @Column(name = "idnum", nullable = false, unique = true)
    int idNum;

    @Column(name = "fName", nullable = false)
    String fName;

    @Column(name = "lName", nullable = false)
    String lName;

    @Column(name = "phoneNum", nullable = false)
    String phoneNum;

    @Column(name = "employeeType", nullable = false)
    String employeeType;

    @Column(name = "departmentCode", nullable = false)
    String departmentCode;

    public Staff(){}

    public Staff(int idNum, String fName, String lName, String phoneNum, String employeeType, String departmentCode) {
        this.idNum = idNum;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
        this.employeeType = employeeType;
        this.departmentCode = departmentCode;
    }

    //region Getters and Setters

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    //endregion


    @Override
    public String toString() {
        return 
            "\n idNum= " + getIdNum() +
            ", fName= " + getfName() +
            ", lName= " + getlName() +
            ", phoneNum= " + getPhoneNum() +
            ", employeeType= " + getEmployeeType() +
            ", departmentCode= " + getDepartmentCode();
    }

}
