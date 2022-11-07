package Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="inventory")
public class Inventory extends DBEntity implements Serializable{
    @Id
    @Column(name="productCode", unique = true, nullable = false)
    String productCode;

    @Column(name="name", nullable = false)
    String name;

    @Column(name="shortDescrip", nullable = false)
    String shortDescrip;

    @Column(name="longDescrip")
    String longDescrip;

    @Column(name="stock", nullable = false)
    int stock;

    @Column(name="unitPrice", nullable = false)
    float unitPrice;

    public Inventory(){}

    public Inventory(String productCode, String name, String shortDescrip, int stock, float unitPrice) {
        this.productCode = productCode;
        this.name = name;
        this.shortDescrip = shortDescrip;
        this.stock = stock;
        this.unitPrice = unitPrice;
    }

    //Includes longDescrip
    public Inventory(String productCode, String name, String shortDescrip, String longDescrip, int stock, float unitPrice) {
        this.productCode = productCode;
        this.name = name;
        this.shortDescrip = shortDescrip;
        this.longDescrip = longDescrip;
        this.stock = stock;
        this.unitPrice = unitPrice;
    }


    //region Getters and Setters

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescrip() {
        return shortDescrip;
    }

    public void setShortDescrip(String shortDescrip) {
        this.shortDescrip = shortDescrip;
    }

    public String getLongDescrip() {
        return longDescrip;
    }

    public void setLongDescrip(String longDescrip) {
        this.longDescrip = longDescrip;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    //endregion


    @Override
    public String toString() {
        return
            "\n productCode= " + getProductCode() +
            ", name= " + getName() +
            ", shortDescrip= " + getShortDescrip() +
            ", longDescrip= " + getLongDescrip() +
            ", stock= " + getStock() +
            ", unitPrice= " + getUnitPrice() ;
    }
    
}
