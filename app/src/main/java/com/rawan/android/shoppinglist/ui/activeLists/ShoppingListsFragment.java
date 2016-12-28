package com.rawan.android.shoppinglist.ui.activeLists;

/**
 * Created by Rawan on 12/26/16.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.firebase.database.DatabaseReference;
import com.rawan.android.shoppinglist.R;
import com.rawan.android.shoppinglist.model.ShoppingList;
import com.rawan.android.shoppinglist.ui.activeListDetails.ActiveListDetailsActivity;
import com.rawan.android.shoppinglist.utils.Constants;
import static com.rawan.android.shoppinglist.ShoppingListApplication.database;

/**
 * A simple {@link Fragment} subclass that shows a list of all shopping lists a user can see.
 * Use the {@link ShoppingListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListsFragment extends Fragment {
    private ListView mListView;


    private ActiveListAdapter mActiveListAdapter;

    public ShoppingListsFragment() {
        /* Required empty public constructor */
    }

    /**
     * Create fragment and pass bundle with data as it's arguments
     * Right now there are not arguments...but eventually there will be.
     */
    public static ShoppingListsFragment newInstance() {
        ShoppingListsFragment fragment = new ShoppingListsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * Initalize UI elements
         */
        View rootView = inflater.inflate(R.layout.fragment_shopping_lists, container, false);
        initializeScreen(rootView);


        DatabaseReference myRef = database.getReference(Constants.FIREBASE_LOCATION_ACTIVE_LISTS);



        mActiveListAdapter = new ActiveListAdapter(getActivity(), ShoppingList.class, R.layout.single_active_list, myRef);
        mListView.setAdapter(mActiveListAdapter);


        /**
         * Set interactive bits, such as click events and adapters
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShoppingList selectedList = mActiveListAdapter.getItem(position);
                if (selectedList != null) {
                    Intent intent = new Intent(getActivity(), ActiveListDetailsActivity.class);
                                   /* Get the list ID using the adapter's get ref method to get the Firebase
+                     * ref and then grab the key.
+                     */
                    String listId = mActiveListAdapter.getRef(position).getKey();
                    intent.putExtra(Constants.KEY_LIST_ID, listId);
                                   /* Starts an active showing the details for the selected list */
                    startActivity(intent);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActiveListAdapter.cleanup();
    }


    /**
     * Link layout elements from XML
     */
    private void initializeScreen(View rootView) {
        mListView = (ListView) rootView.findViewById(R.id.list_view_active_lists);
    }
}
