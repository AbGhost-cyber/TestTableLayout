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


public class LeftCountriesAdapter extends BaseExpandableListAdapter {
    public static final String TAG = LeftCountriesAdapter.class.getSimpleName();
    private final TableModel item;
    private final LayoutInflater inflater;
    private final Activity activity;

    public LeftCountriesAdapter(Activity activity, TableModel item) {
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
            convertView = inflater.inflate(R.layout.table_left_item, null);

            Resources r = activity.getResources();
            float px40 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());
            convertView.setPadding(
                    convertView.getPaddingLeft() + (int) px40,
                    convertView.getPaddingTop(),
                    convertView.getPaddingRight(),
                    convertView.getPaddingBottom());
        }

        TextView name = (TextView) convertView.findViewById(R.id.tv_table_content_item_left);
        name.setText(object.getLeftTitle());

        return convertView;
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
            convertView = inflater.inflate(R.layout.table_left_item, null);
            convertView.setBackgroundColor(Color.GREEN);
            Resources r = activity.getResources();
            float px20 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, r.getDisplayMetrics());
            convertView.setPadding(
                    convertView.getPaddingLeft() + (int) px20,
                    convertView.getPaddingTop(),
                    convertView.getPaddingRight(),
                    convertView.getPaddingBottom());
        }

        TextView name = (TextView) convertView.findViewById(R.id.tv_table_content_item_left);
        TableModel item = (TableModel) getGroup(groupPosition);
        if (item != null)
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