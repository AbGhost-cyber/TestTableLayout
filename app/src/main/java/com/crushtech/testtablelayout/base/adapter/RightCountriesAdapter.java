package com.crushtech.testtablelayout.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.crushtech.testtablelayout.R;
import com.crushtech.testtablelayout.models.TableModel;


public class RightCountriesAdapter extends BaseExpandableListAdapter {
    public static final String TAG = RightCountriesAdapter.class.getSimpleName();
    private final TableModel item;
    private final LayoutInflater inflater;
    private final Activity activity;

    public RightCountriesAdapter(Activity activity, TableModel item) {
        this.activity = activity;
        this.item = item;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return item.getChildren().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        TableModel object = (TableModel) getChild(0, childPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.table_right_item, null);
        }

        setUpItems(convertView, object);

        return convertView;
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

    @Override
    public int getChildrenCount(int groupPosition) {

        if (item == null || item.getChildren() == null) {
            return 0;
        }
        return item.getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return item;
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.table_right_item, null);
            convertView.setBackgroundColor(Color.GREEN);
        }
        TableModel item = (TableModel) getGroup(groupPosition);
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
}