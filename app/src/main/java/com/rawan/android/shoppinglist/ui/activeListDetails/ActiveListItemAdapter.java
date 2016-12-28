package com.rawan.android.shoppinglist.ui.activeListDetails;

/**
 * Created by Rawan on 12/28/16.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.rawan.android.shoppinglist.R;
import com.rawan.android.shoppinglist.model.ShoppingList;
import com.rawan.android.shoppinglist.model.ShoppingListItem;
import com.rawan.android.shoppinglist.utils.Constants;

import java.util.HashMap;

/**
 * Populates list_view_shopping_list_items inside ActiveListDetailsActivity
 */
public class ActiveListItemAdapter extends FirebaseListAdapter<ShoppingListItem> {

    private ShoppingList mShoppingList;
    private String mListId;

    /**
     * Public constructor that initializes private instance variables when adapter is created
     */
    public ActiveListItemAdapter(Activity activity, Class<ShoppingListItem> modelClass, int modelLayout,
                                 Query ref, String listId) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
        this.mListId = listId;
    }


    /**
     * Public method that is used to pass shoppingList object when it is loaded in ValueEventListener
     */
    public void setShoppingList(ShoppingList shoppingList) {
        this.mShoppingList = shoppingList;
        this.notifyDataSetChanged();
    }


    /**
     * Protected method that populates the view attached to the adapter (list_view_friends_autocomplete)
     * with items inflated from single_active_list_item.xml
     * populateView also handles data changes and updates the listView accordingly
     */

    @Override
    protected void populateView(View v, ShoppingListItem model, int position) {
        ImageButton buttonRemoveItem = (ImageButton) v.findViewById(R.id.button_remove_item);
        TextView textViewMealItemName = (TextView) v.findViewById(R.id.text_view_active_list_item_name);

        textViewMealItemName.setText(model.getItemName());
        final String itemToRemoveId = this.getRef(position).getKey();

        /**
         * Set the on click listener for "Remove list item" button
         */
        buttonRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity, R.style.CustomTheme_Dialog)
                        .setTitle(mActivity.getString(R.string.remove_item_option))
                        .setMessage(mActivity.getString(R.string.dialog_message_are_you_sure_remove_item))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                removeItem(itemToRemoveId);
                             /* Dismiss the dialog */
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                /* Dismiss the dialog */
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert);

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }


    private void removeItem(String itemId) {
        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_URL);

                        /* Make a map for the removal */
        HashMap<String, Object> updatedRemoveItemMap = new HashMap<String, Object>();

                        /* Remove the item by passing null */
        updatedRemoveItemMap.put("/" + Constants.FIREBASE_LOCATION_SHOPPING_LIST_ITEMS + "/"
                + mListId + "/" + itemId, null);

                       /* Make the timestamp for last changed */
        HashMap<String, Object> changedTimestampMap = new HashMap<>();
        changedTimestampMap.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

                       /* Add the updated timestamp */
        updatedRemoveItemMap.put("/" + Constants.FIREBASE_LOCATION_ACTIVE_LISTS +
                "/" + mListId + "/" + Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, changedTimestampMap);

                /* Do the update */
        firebaseRef.updateChildren(updatedRemoveItemMap);
    }
}