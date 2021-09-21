package practice.springpractice.controller;

import java.sql.Time;

public class StoreForm {
    private String store_name;
    private String manager;
    private String area;
    private String id;
    private String table_x;
    private String table_y;
    private String throws_value;
    private String open_time;
    private String close_time;
    private String last_order;
    private String selected_time;

    public String getSelected_time() {
        return selected_time;
    }

    public void setSelected_time(String selected_time) {
        this.selected_time = selected_time;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getLast_order() {
        return last_order;
    }

    public void setLast_order(String last_order) {
        this.last_order = last_order;
    }

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
