package com.rawan.android.shoppinglist.model;

/**
 * Created by Rawan on 12/28/16.
 */


public class ShoppingListItem {
    private String itemName;
    private String owner;

    /**
     * Required public constructor
     */
    public ShoppingListItem() {
    }

    /**
     * Use this constructor to create new ShoppingListItem.
     *
     * @param itemName
     */
    public ShoppingListItem(String itemName) {
        this.itemName = itemName;
        /**
         * This is a default value until we can differentiate users.
         * Which will be soon, I promise.
         */
        this.owner = "Anonymous Owner";
    }

    public String getItemName() {
        return itemName;
    }

    public String getOwner() {
        return owner;
    }

}
