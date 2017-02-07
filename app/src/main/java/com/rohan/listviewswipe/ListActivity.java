package com.rohan.listviewswipe;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;

import rohan.com.listviewslide.R;

/**
 * Created by Rohan
 */
public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listData;
    private SwipeLayout swipeLayout;

    private final static String TAG = ListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(R.id.listview);

        populateListData();
        configureSwipeLayout();
        setListViewAdapter();
    }

    private void populateListData() {
        listData = new ArrayList<>();
        for(int i=1; i< 10; i++){
            listData.add("Item " + i);
        }
    }

    private void configureSwipeLayout() {
        final View swipeViewHolder = getLayoutInflater().inflate(R.layout.header_listview, listView, false);
        swipeLayout = (SwipeLayout)swipeViewHolder.findViewById(R.id.swipe_layout);
        setSwipeViewFeatures();
    }

    private void setSwipeViewFeatures() {
        //set show mode.
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        //add drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, findViewById(R.id.bottom_wrapper));
        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                Log.i(TAG, "onClose");
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                Log.i(TAG, "on swiping");
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {
                Log.i(TAG, "on start open");
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                Log.i(TAG, "the BottomView totally show");
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
                Log.i(TAG, "the BottomView totally close");
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
                //you can close the swipe view
            }
        });
    }

    //List view adapter
    private void setListViewAdapter() {
        adapter = new ListViewAdapter(this, R.layout.item_listview, listData);
        listView.setAdapter(adapter);
    }

    //this is important
    public void updateAdapter() {
        adapter.notifyDataSetChanged(); //update adapter
    }
}