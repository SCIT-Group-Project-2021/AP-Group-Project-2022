package Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="invoiceitem")
@IdClass(InvoiceItemID.class) //https://www.objectdb.com/java/jpa/entity/id#composite_primary_key
public class InvoiceItem extends DBEntity implements Serializable{

    @Id
    @Column(name="productCode", nullable=false)
    String productCode;

    @Id
    @Column(name="invoiceNum", nullable = false)
    int invoiceNum;

    @Column(name="itemQuantity", nullable = false)
    int itemQuantity;

    public InvoiceItem(){}

    public InvoiceItem(String productCode, int itemQuantity) {
        this.productCode = productCode;
        this.itemQuantity = itemQuantity;
    }

    public InvoiceItem(String productCode, int invoiceNum, int itemQuantity) {
        this.productCode = productCode;
        this.invoiceNum = invoiceNum;
        this.itemQuantity = itemQuantity;
    }

    //region Getters and Setters

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    //endregion


    @Override
    public String toString() {
        return 
            "\n productCode= " + getProductCode() +
            ", invoiceNum= " + getInvoiceNum() +
            ", itemQuantity= " + getItemQuantity();
    }

}
