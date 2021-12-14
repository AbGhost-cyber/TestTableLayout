package com.crushtech.testtablelayout.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.crushtech.testtablelayout.R;
import com.crushtech.testtablelayout.models.TableModel;
import com.crushtech.testtablelayout.widget.MyExpandedListView;

import java.util.HashMap;
import java.util.List;


public class LeftRegionsAdapter extends BaseExpandableListAdapter {


    private final List<TableModel> items;
    private final Activity activity;
    private final LayoutInflater inflater;
    public int childExpandState = -1;
    public int curPos = -1;
   // private MyExpandedListView currentListView;
    private ExpandableListView.OnGroupClickListener childClickListener;
    private HashMap<Integer, MyExpandedListView> views = new HashMap<>();

    public LeftRegionsAdapter(Activity activity, List<TableModel> items) {
        this.items = items;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public HashMap<Integer, MyExpandedListView> getViews() {
        return views;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public void setChildClickListener(ExpandableListView.OnGroupClickListener childClickListener) {
        this.childClickListener = childClickListener;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        TableModel item = (TableModel) getChild(groupPosition, childPosition);
        MyExpandedListView expandedListView = (MyExpandedListView) convertView;
        if (convertView == null) {
            expandedListView = new MyExpandedListView(activity);
            expandedListView.setGroupIndicator(null);
        }
        LeftCountriesAdapter adapter = new LeftCountriesAdapter(activity, item);
        expandedListView.setAdapter(adapter);
        //setCurrentListView(expandedListView);

        for (int c = 0; c < adapter.getGroupCount(); c++) {
            expandedListView.expandGroup(c);
        }
       // handleExpandOnce(expandedListView);
        expandedListView.setOnGroupClickListener((parent1, v, groupPosition1, id) -> {
            childClickListener.onGroupClick(parent1, v, childPosition, id);
            Log.d("TAG", "getChildView: " + getChildrenCount(groupPosition) + ":" + childPosition);
            return false;
        });
        views.put(childPosition, expandedListView);
        return expandedListView;
    }

    public void handleExpandOnce(final ExpandableListView listView) {
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    listView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<TableModel> children = items.get(groupPosition).getChildren();
        if (children == null) return 0;
        return children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        TableModel item = (TableModel) getGroup(groupPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.table_left_item, null);
            convertView.setBackgroundColor(Color.parseColor("#F7F3E3"));
        }
        TextView name = (TextView) convertView.findViewById(R.id.tv_table_content_item_left);
        name.setText(item.getLeftTitle());

        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}