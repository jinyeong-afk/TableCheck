package practice.springpractice.controller;

public class StoreForm {
    private String store_name;
    private String manager;
    private String area;
    private String id;
    private String table_status;
    private String table_x;
    private String table_y;
    private String throws_value;

    public String getThrows_value() {
        return throws_value;
    }

    public void setThrows_value(String throws_value) {
        this.throws_value = throws_value;
    }

    public String getTable_x() {
        return table_x;
    }

    public void setTable_x(String table_x) {
        this.table_x = table_x;
    }

    public String getTable_y() {
        return table_y;
    }

    public void setTable_y(String table_y) {
        this.table_y = table_y;
    }

    public String getTable_status() {
        return table_status;
    }

    public void setTable_status(String table_status) {
        this.table_status = table_status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
