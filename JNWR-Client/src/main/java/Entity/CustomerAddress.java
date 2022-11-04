package Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "customeraddress")
public class CustomerAddress extends DBEntity implements Serializable{
    @Id
    @Column(name = "customerID", nullable = false, unique = true)
    int customerID;

    @Column(name = "stName")
    String stName;

    @Column(name = "city", nullable = false)
    String city;

    @Column(name = "parish", nullable = false)
    String parish;

    public CustomerAddress(){}

    public CustomerAddress(int customerID, String stName, String city, String parish) {
        this.customerID = customerID;
        this.stName = stName;
        this.city = city;
        this.parish = parish;
    }

    //region Getters and Setters

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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
