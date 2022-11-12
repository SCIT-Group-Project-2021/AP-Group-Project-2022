package Entity;

import java.io.Serializable;
import java.util.Objects;

public class InvoiceItemID implements Serializable{

    private String productCode;

    private int invoiceNum;

    public InvoiceItemID() {
    }

    public InvoiceItemID(String productCode, int invoiceNum) {
        this.productCode = productCode;
        this.invoiceNum = invoiceNum;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getInvoiceNum() {
        return this.invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof InvoiceItemID)) {
            return false;
        }
        InvoiceItemID invoiceItemID = (InvoiceItemID) o;
        return Objects.equals(productCode, invoiceItemID.productCode) && invoiceNum == invoiceItemID.invoiceNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, invoiceNum);
    }
    
}
