package com.rawan.android.shoppinglist.ui.activeListDetails;
import android.app.Dialog;
import android.os.Bundle;

import com.rawan.android.shoppinglist.R;
import com.rawan.android.shoppinglist.model.ShoppingList;

/**
 * Created by Rawan on 12/27/16.
 */

public class AddListItemDialogFragment extends EditListDialogFragment {

    /**
     * Public static constructor that creates fragment and passes a bundle with data into it when adapter is created
     */
    public static AddListItemDialogFragment newInstance(ShoppingList shoppingList) {
        AddListItemDialogFragment addListItemDialogFragment = new AddListItemDialogFragment();

        Bundle bundle = newInstanceHelper(shoppingList, R.layout.dialog_add_item);
        addListItemDialogFragment.setArguments(bundle);

        return addListItemDialogFragment;
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
         **/
        return super.createDialogHelper(R.string.positive_button_add_list_item);
    }

    /**
     * Adds new item to the current shopping list
     */
    @Override
    protected void doListEdit() {

    }
}
