package practice.springpractice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String store_name;
    private String id;
    private String manager;
    private String area;
    private String table_status;
    private String table_x;
    private String table_y;

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

    public void setStore_name(String name) {
        this.store_name = name;
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
