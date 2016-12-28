package com.rawan.android.shoppinglist.ui.activeLists;

/**
 * Created by Rawan on 12/26/16.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.rawan.android.shoppinglist.R;
import com.rawan.android.shoppinglist.model.ShoppingList;
import com.rawan.android.shoppinglist.ui.activeListDetails.ActiveListDetailsActivity;
import com.rawan.android.shoppinglist.utils.Constants;
import com.rawan.android.shoppinglist.utils.Utils;

import java.util.Date;

import static com.google.android.gms.internal.zzs.TAG;
import static com.rawan.android.shoppinglist.ShoppingListApplication.database;

/**
 * A simple {@link Fragment} subclass that shows a list of all shopping lists a user can see.
 * Use the {@link ShoppingListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListsFragment extends Fragment {
    private ListView mListView;
//    private TextView mListViewTitle;
//    private TextView mCreatedby;
    //private TextView mOwner;
    //  private TextView mTextViewEditTime;

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

//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                ShoppingList shoppingListValue = dataSnapshot.getValue(ShoppingList.class);
//                if (shoppingListValue != null) {
//                    mListViewTitle.setText(shoppingListValue.getListName());
//                    if (shoppingListValue.getOwner() != null) {
//                        mCreatedby.setVisibility(View.VISIBLE);
//                        mOwner.setText(shoppingListValue.getOwner());
//                    }
//                    if (shoppingListValue.getTimestampLastChanged() != null) {
//                        mTextViewEditTime.setText(Utils.SIMPLE_DATE_FORMAT.format(new Date(shoppingListValue.getTimestampLastChangedLong())));
//                    } else {
//                        mTextViewEditTime.setText("");
//                    }
//                }
//
//                Log.d(TAG, "Value is: " + shoppingListValue);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });


        mActiveListAdapter = new ActiveListAdapter(getActivity(), ShoppingList.class, R.layout.single_active_list, myRef);
        mListView.setAdapter(mActiveListAdapter);

//        mListViewTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), ActiveListDetailsActivity.class));
//            }
//        });

        /**
         * Set interactive bits, such as click events and adapters
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
//        mListViewTitle = (TextView) rootView.findViewById(R.id.text_view_list_name);
//        mCreatedby = (TextView) rootView.findViewById(R.id.created_by);
//        mOwner = (TextView) rootView.findViewById(R.id.owner);
//        mTextViewEditTime = (TextView) rootView.findViewById(R.id.text_view_edit_time);
    }
}
