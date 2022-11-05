package Entity;

public class DBEntity {
    private String addAction;
    private String deleteAction;
    private String findAction;

    public DBEntity() {
        this.addAction = "";
        this.deleteAction = "";
        this.findAction = "";
    }

    public DBEntity(String addAction, String deleteAction, String findAction) {
        this.addAction = addAction;
        this.deleteAction = deleteAction;
        this.findAction = findAction;
    }

    public String getAddAction() {
        return addAction;
    }

    public void setAddAction(String addAction) {
        this.addAction = addAction;
    }

    public String getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(String deleteAction) {
        this.deleteAction = deleteAction;
    }

    public String getFindAction() {
        return findAction;
    }

    public void setFindAction(String findAction) {
        this.findAction = findAction;
    }
}
