package Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "staffadress")
public class StaffAddress {
    @Id
    @Column(name = "staffID", nullable = false, unique = true)
    int staffID;

    @Column(name = "stName")
    String stName;

    @Column(name = "city", nullable = false)
    String city;

    @Column(name = "parish", nullable = false)
    String parish;

    public StaffAddress(){}

    public StaffAddress(int staffID, String stName, String city, String parish) {
        this.staffID = staffID;
        this.stName = stName;
        this.city = city;
        this.parish = parish;
    }

    //region Getters and Setters

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int customerID) {
        this.staffID = customerID;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParish() {
        return parish;
    }

    public void setParish(String parish) {
        this.parish = parish;
    }

    //endregion
}
