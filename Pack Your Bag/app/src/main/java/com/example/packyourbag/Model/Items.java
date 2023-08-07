package com.example.packyourbag.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "items")
public class Items implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int ID = 0;

    @ColumnInfo(name = "itemname")
    String itemname;

    @ColumnInfo(name = "category")
    String category;

    @ColumnInfo(name = "addedby")
    String addedby;

    @ColumnInfo(name = "checked")
    boolean checked = false;


    public Items() {}
    public Items(String itemname, String category, boolean checked) {
        this.addedby = "system";
        this.itemname = itemname;
        this.category = category;
        this.checked = checked;
    }

    @Ignore
    public Items(String itemname, String category, String addedby, boolean checked) {
        this.itemname = itemname;
        this.category = category;
        this.addedby = addedby;
        this.checked = checked;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddedby() {
        return addedby;
    }

    public void setAddedby(String addedby) {
        this.addedby = addedby;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
