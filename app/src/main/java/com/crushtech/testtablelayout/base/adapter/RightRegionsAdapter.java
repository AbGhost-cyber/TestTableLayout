package com.crushtech.testtablelayout.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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


public class RightRegionsAdapter extends BaseExpandableListAdapter {


    private final List<TableModel> items;
    private final Activity activity;
    private final LayoutInflater inflater;
    public int childExpandState = -1;
    public int curPos = -1;
   // private MyExpandedListView currentListView;
    private ExpandableListView.OnGroupClickListener childClickListener;
    private HashMap<Integer, MyExpandedListView> views = new HashMap<>();

    public RightRegionsAdapter(Activity activity, List<TableModel> items) {
        this.items = items;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setChildClickListener(ExpandableListView.OnGroupClickListener childClickListener) {
        this.childClickListener = childClickListener;
    }

//    public MyExpandedListView getCurrentListView() {
//        return currentListView;
//    }

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

//    public void setCurrentListView(MyExpandedListView currentListView) {
//        this.currentListView = currentListView;
//    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        TableModel item = (TableModel) getChild(groupPosition, childPosition);
        MyExpandedListView expandedListView = (MyExpandedListView) convertView;
        if (convertView == null) {
            expandedListView = new MyExpandedListView(activity);
            expandedListView.setGroupIndicator(null);
        }
        RightCountriesAdapter adapter = new RightCountriesAdapter(activity, item);
        expandedListView.setAdapter(adapter);
       // setCurrentListView(expandedListView);
        for (int c = 0; c < adapter.getGroupCount(); c++) {
            expandedListView.expandGroup(c);
        }
       // handleExpandOnce(expandedListView);
        expandedListView.setOnGroupClickListener((parent1, v, groupPosition1, id) -> {
            childClickListener.onGroupClick(parent1, v, childPosition, id);
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
            convertView = inflater.inflate(R.layout.table_right_item, null);
            convertView.setBackgroundColor(Color.parseColor("#F7F3E3"));
        }

        setUpItems(convertView, item);

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

    private void setUpItems(View convertView, TableModel item) {
        TextView tv_table_content_right_item0 = convertView.findViewById(R.id.tv_table_content_right_item0);
        TextView tv_table_content_right_item1 = convertView.findViewById(R.id.tv_table_content_right_item1);
        TextView tv_table_content_right_item2 = convertView.findViewById(R.id.tv_table_content_right_item2);
        TextView tv_table_content_right_item3 = convertView.findViewById(R.id.tv_table_content_right_item3);
        TextView tv_table_content_right_item4 = convertView.findViewById(R.id.tv_table_content_right_item4);
        TextView tv_table_content_right_item5 = convertView.findViewById(R.id.tv_table_content_right_item5);
        TextView tv_table_content_right_item6 = convertView.findViewById(R.id.tv_table_content_right_item6);
        TextView tv_table_content_right_item7 = convertView.findViewById(R.id.tv_table_content_right_item7);
        TextView tv_table_content_right_item8 = convertView.findViewById(R.id.tv_table_content_right_item8);
        TextView tv_table_content_right_item9 = convertView.findViewById(R.id.tv_table_content_right_item9);
        TextView tv_table_content_right_item10 = convertView.findViewById(R.id.tv_table_content_right_item10);
        TextView tv_table_content_right_item11 = convertView.findViewById(R.id.tv_table_content_right_item11);
        TextView tv_table_content_right_item12 = convertView.findViewById(R.id.tv_table_content_right_item12);
        TextView tv_table_content_right_item13 = convertView.findViewById(R.id.tv_table_content_right_item13);
        TextView tv_table_content_right_item14 = convertView.findViewById(R.id.tv_table_content_right_item14);

        tv_table_content_right_item0.setText(item.getText0());
        tv_table_content_right_item1.setText(item.getText1());
        tv_table_content_right_item2.setText(item.getText2());
        tv_table_content_right_item3.setText(item.getText3());
        tv_table_content_right_item4.setText(item.getText4());
        tv_table_content_right_item5.setText(item.getText5());
        tv_table_content_right_item6.setText(item.getText6());
        tv_table_content_right_item7.setText(item.getText7());
        tv_table_content_right_item8.setText(item.getText8());
        tv_table_content_right_item9.setText(item.getText9());
        tv_table_content_right_item10.setText(item.getText10());
        tv_table_content_right_item11.setText(item.getText11());
        tv_table_content_right_item12.setText(item.getText12());
        tv_table_content_right_item13.setText(item.getText13());
        tv_table_content_right_item14.setText(item.getText14());
    }
}