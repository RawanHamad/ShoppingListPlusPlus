package com.rawan.android.shoppinglist.ui.activeListDetails;

import android.app.Dialog;
import android.os.Bundle;

import com.rawan.android.shoppinglist.R;
import com.rawan.android.shoppinglist.model.ShoppingList;
/**
 * Created by Rawan on 12/27/16.
 */

/**
 * Lets user edit list item name for all copies of the current list
 */
public class EditListItemNameDialogFragment extends EditListDialogFragment {

    /**
     * Public static constructor that creates fragment and passes a bundle with data into it when adapter is created
     */
    public static EditListItemNameDialogFragment newInstance(ShoppingList shoppingList, String listId) {
        EditListItemNameDialogFragment editListItemNameDialogFragment = new EditListItemNameDialogFragment();

        Bundle bundle = EditListDialogFragment.newInstanceHelper(shoppingList, R.layout.dialog_edit_item, listId);
        editListItemNameDialogFragment.setArguments(bundle);

        return editListItemNameDialogFragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /** {@link EditListDialogFragment#createDialogHelper(int)} is a
         * superclass method that creates the dialog
         */
        Dialog dialog = super.createDialogHelper(R.string.positive_button_edit_item);
        return dialog;
    }

    /**
     * Change selected list item name to the editText input if it is not empty
     */
    protected void doListEdit() {
    }
}