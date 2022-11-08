package Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="invenCategory")
public class InvenCategory extends DBEntity implements Serializable{
    @Id
    @Column(name="categoryID", unique = true, nullable = false)
    String categoryID;

    @Column(name="name", nullable = false)
    String name;

    public InvenCategory(){}

    public InvenCategory(String categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    //region Getters and Setters

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion
}
