package practice.springpractice.controller;

import java.util.Date;
import java.util.List;

public class ReservationForm {
    private long sequence;
    private String store_name;
    private String id;
    private String reserve_time;
    private String menu_name;
    private int menu_num;
    private List<String> menuNameList;
    private List<Integer> menuPriceList;
    private List<Integer> menuNumList;
    private String seat;

    public List<String> getMenuNameList() {
        return menuNameList;
    }

    public void setMenuNameList(List<String> menuNameList) {
        this.menuNameList = menuNameList;
    }

    public List<Integer> getMenuPriceList() {
        return menuPriceList;
    }

    public void setMenuPriceList(List<Integer> menuPriceList) {
        this.menuPriceList = menuPriceList;
    }

    public List<Integer> getMenuNumList() {
        return menuNumList;
    }

    public void setMenuNumList(List<Integer> menuNumList) {
        this.menuNumList = menuNumList;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReserve_time() {
        return reserve_time;
    }

    public void setReserve_time(String reserve_time) {
        this.reserve_time = reserve_time;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getMenu_num() {
        return menu_num;
    }

    public void setMenu_num(int menu_num) {
        this.menu_num = menu_num;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
