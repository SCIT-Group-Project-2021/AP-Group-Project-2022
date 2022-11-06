package Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="invoice")
public class Invoice extends DBEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoiceNum", nullable = false)
    int invoiceNum;

    @Column(name = "billingDate", nullable = false)
    String billingDate;

    @Column(name="customerID")
    int customerID;

    @Column(name = "staffID", nullable = false)
    int staffID;

    public Invoice(){}

    public Invoice(int invoiceNum, String billingDate, int staffID) {
        this.invoiceNum = invoiceNum;
        this.billingDate = billingDate;
        this.staffID = staffID;
    }

    public Invoice(int invoiceNum, String billingDate, int customerID, int staffID) {
        this.invoiceNum = invoiceNum;
        this.billingDate = billingDate;
        this.customerID = customerID;
        this.staffID = staffID;
    }

    //region Getters and Setters

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    //endregion

    //TODO: Something isnt working in here so i temporarily commented it out
    // Creating an invoice item
    //Map<String, Object> params = new HashMap<>();
    /* 
    params.put("cus_ID");
    params.put("price");

    InvoiceItem invoiceItem =
            InvoiceItem.create(params);

    //Retrieve and inoice item
    InvoiceItem invoiceItem =
            InvoiceItem.retrieve("");

    //Update an invoice item
    InvoiceItem updatedInvoiceItem =
            invoiceItem.update(params);

    //Delete and invoice item
    InvoiceItem deletedInvoiceItem =
            invoiceItem.delete();

    //List of all invoice items
    InvoiceItemCollection invoiceItems =
            InvoiceItem.list(params);*/
}
