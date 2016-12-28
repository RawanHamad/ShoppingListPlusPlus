package com.rawan.android.shoppinglist.ui.activeListDetails;

import android.app.Dialog;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.rawan.android.shoppinglist.R;
import com.rawan.android.shoppinglist.model.ShoppingList;
import com.rawan.android.shoppinglist.utils.Constants;

import java.util.HashMap;
/**
 * Created by Rawan on 12/27/16.
 */

/**
 * Lets user edit list item name for all copies of the current list
 */
public class EditListItemNameDialogFragment extends EditListDialogFragment {

    String mItemName, mItemId;

    /**
     * Public static constructor that creates fragment and passes a bundle with data into it when adapter is created
     */
    public static EditListItemNameDialogFragment newInstance(ShoppingList shoppingList, String itemName, String itemId, String listId) {
        EditListItemNameDialogFragment editListItemNameDialogFragment = new EditListItemNameDialogFragment();

        Bundle bundle = EditListDialogFragment.newInstanceHelper(shoppingList, R.layout.dialog_edit_item, listId);
        bundle.putString(Constants.KEY_LIST_ITEM_NAME, itemName);
        bundle.putString(Constants.KEY_LIST_ITEM_ID, itemId);
        editListItemNameDialogFragment.setArguments(bundle);

        return editListItemNameDialogFragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemName = getArguments().getString(Constants.KEY_LIST_ITEM_NAME);
        mItemId = getArguments().getString(Constants.KEY_LIST_ITEM_ID);
    }


    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /** {@link EditListDialogFragment#createDialogHelper(int)} is a
         * superclass method that creates the dialog
         */
        Dialog dialog = super.createDialogHelper(R.string.positive_button_edit_item);
        super.helpSetDefaultValueEditText(mItemName);
        return dialog;
    }

    /**
     * Change selected list item name to the editText input if it is not empty
     */
    protected void doListEdit() {
        String nameInput = mEditTextForList.getText().toString();

        /**
         * Set input text to be the current list item name if it is not empty and is not the
         * previous name.
         */
        if (!nameInput.equals("") && !nameInput.equals(mItemName)) {
            DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_URL);

                               /* Make a map for the item you are editing the name of */
            HashMap<String, Object> updatedItemToAddMap = new HashMap<String, Object>();

                               /* Add the new name to the update map*/
            updatedItemToAddMap.put("/" + Constants.FIREBASE_LOCATION_SHOPPING_LIST_ITEMS + "/"
                            + mListId + "/" + mItemId + "/" + Constants.FIREBASE_PROPERTY_ITEM_NAME,
                    nameInput);

                                /* Make the timestamp for last changed */
            HashMap<String, Object> changedTimestampMap = new HashMap<>();
            changedTimestampMap.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

                                /* Add the updated timestamp */
            updatedItemToAddMap.put("/" + Constants.FIREBASE_LOCATION_ACTIVE_LISTS +
                    "/" + mListId + "/" + Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, changedTimestampMap);

                               /* Do the update */
            firebaseRef.updateChildren(updatedItemToAddMap);

        }
    }
}