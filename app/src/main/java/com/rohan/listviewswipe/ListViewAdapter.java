package com.rohan.listviewswipe;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.util.List;

import rohan.com.listviewslide.R;

/**
 * Created by Rohan
 * Simple array list adapter
 */

public class ListViewAdapter extends ArrayAdapter<String> {

    private ListActivity activity;
    private List<String> listItems;


    public ListViewAdapter(ListActivity context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.listItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from list item XML file and set tag
            convertView =  activity.getLayoutInflater().inflate(R.layout.item_listview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position));
        //handling buttons event
        holder.btnDelete.setOnClickListener(onDeleteListener(position, holder));
        return convertView;
    }

    /**
     * Handle delete button
     * @param position
     * @param holder
     * @return
     */
    private View.OnClickListener onDeleteListener(final int position, final ViewHolder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //simply remove the list item
                listItems.remove(position);
                //close the swipe layout
                holder.swipeLayout.close();
                //update adapter
                activity.updateAdapter();
            }
        };
    }

    /**
     * Holds a view of single list item.
     */
    private class ViewHolder {
        private TextView name;
        private View btnDelete;
        private SwipeLayout swipeLayout;

        public ViewHolder(View v) {
            swipeLayout = (SwipeLayout)v.findViewById(R.id.swipe_layout);
            btnDelete = v.findViewById(R.id.delete);
            name = (TextView) v.findViewById(R.id.name);
            swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        }
    }
}
