package com.rawan.android.shoppinglist.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;
import com.rawan.android.shoppinglist.utils.Constants;

import java.util.HashMap;

/**
 * Created by Rawan on 12/27/16.
 */

public class ShoppingList {

    private String listName;
    private String owner;
    private HashMap<String, Object> timestampLastChanged;
    private HashMap<String, Object> timestampCreated;

    //at least one empty constructor
    public ShoppingList() {
        // Default constructor required for calls to DataSnapshot.getValue()
    }

    public ShoppingList(String listName, String owner, HashMap<String, Object> timestampCreated) {
        this.listName = listName;
        this.owner = owner;
        this.timestampCreated = timestampCreated;
        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
        timestampNowObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);
        this.timestampLastChanged = timestampNowObject;
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


    public HashMap<String, Object> getTimestampLastChanged() {
        return timestampLastChanged;
    }

    @Exclude
    public long getTimestampLastChangedLong() {
        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
    }


    public HashMap<String, Object> getTimestampCreated() {
        return timestampCreated;
    }


    @Exclude
    public long getTimestampCreatedLong() {
        return (long) timestampLastChanged.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);

    }
}
