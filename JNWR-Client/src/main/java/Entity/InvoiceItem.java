package Entity;

import java.io.Serializable;
import javax.persistence.*;

@IdClass(InvoiceItem.class) //https://www.objectdb.com/java/jpa/entity/id#composite_primary_key
@Entity
@Table(name="invoiceitem")
public class InvoiceItem implements Serializable {

    @Id
    @Column(name="productCode", nullable=false)
    String productCode;

    @Id
    @Column(name="invoiceNum", nullable = false)
    int invoiceNum;

    @Column(name="itemQuantity", nullable = false)
    int itemQuantity;

    public InvoiceItem(){}

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
}
