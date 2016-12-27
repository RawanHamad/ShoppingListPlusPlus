package com.rawan.android.shoppinglist;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Rawan on 12/27/16.
 */

public class ShoppingListApplication extends Application {

    public static FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = FirebaseDatabase.getInstance();


    }
}
