package JNWR.Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customerID")
    int customerId;

    @Column(name="fName", nullable = false)
    String fName;

    @Column(name="lName", nullable = false)
    String lName;

    @Column(name="dob")
    String dob;

    @Column(name="telephoneNum", nullable = false)
    String telephoneNum;

    @Column(name="email")
    String email;

    @Column(name="dateOfMembership", nullable = false)
    String dateOfMembership;

    @Column(name="dateOfMembershipExpiry", nullable = false)
    String dateOfMembershipExpiry;

    public Customer(){}

    public Customer(String fName, String lName, String dob, String telephoneNum, String email, String dateOfMembership, String dateOfMembershipExpiry) {
        this.fName = fName;
        this.lName = lName;
        this.dob = dob;
        this.telephoneNum = telephoneNum;
        this.email = email;
        this.dateOfMembership = dateOfMembership;
        this.dateOfMembershipExpiry = dateOfMembershipExpiry;
    }

    //region Getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(String dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public String getDateOfMembershipExpiry() {
        return dateOfMembershipExpiry;
    }

    public void setDateOfMembershipExpiry(String dateOfMembershipExpiry) {
        this.dateOfMembershipExpiry = dateOfMembershipExpiry;
    }
    //endregion

}
