package com.rawan.android.shoppinglist.ui.activeLists;

/**
 * Created by Rawan on 12/28/16.
 */

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;
import com.rawan.android.shoppinglist.R;
import com.rawan.android.shoppinglist.model.ShoppingList;


/**
 * Populates the list_view_active_lists inside ShoppingListsFragment
 */
public class ActiveListAdapter extends FirebaseListAdapter<ShoppingList> {

    /**
     * Public constructor that initializes private instance variables when adapter is created
     */
    public ActiveListAdapter(Activity activity, Class<ShoppingList> modelClass, int modelLayout,
                             Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    /**
     * Protected method that populates the view attached to the adapter (list_view_active_lists)
     * with items inflated from single_active_list.xml
     * populateView also handles data changes and updates the listView accordingly
     */
    @Override
    protected void populateView(View view, ShoppingList model, int position) {

        TextView textViewListName = (TextView) view.findViewById(R.id.text_view_list_name);
        TextView textViewCreatedByUser = (TextView) view.findViewById(R.id.text_view_created_by_user);

        textViewListName.setText(model.getListName());
        textViewCreatedByUser.setText(model.getOwner());


    }


}
