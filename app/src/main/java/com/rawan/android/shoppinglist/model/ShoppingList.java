package com.rawan.android.shoppinglist.model;

/**
 * Created by Rawan on 12/27/16.
 */

public class ShoppingList {

    private String listName;
    private String owner;

    //at least one empty constructor
    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner) {
        this.listName = listName;
        this.owner = owner;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
