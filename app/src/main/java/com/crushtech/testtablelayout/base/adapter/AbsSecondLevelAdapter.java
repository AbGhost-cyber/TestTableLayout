package com.crushtech.testtablelayout.base.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbsSecondLevelAdapter<T> extends BaseExpandableListAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> groups;
    protected final int mItemLayoutId;
    protected List<List<T>> children;

    public AbsSecondLevelAdapter(Context context, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = itemLayoutId;
        groups = new ArrayList<T>();
        children = new ArrayList<>();
    }

    public void addItemData(T mBean, boolean isRefresh) {

        groups.add(mBean);
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }

    public void addItemData(T mBean, int index, boolean isRefresh) {

        groups.add(index, mBean);
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }

    public void addItemData(Collection<? extends T> mCommonbeans, int index, boolean isRefresh) {

        groups.addAll(index, mCommonbeans);
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }

    public void addItemData(Collection<? extends T> mCommonbeans, boolean isRefresh) {

        groups.addAll(mCommonbeans);
        if (isRefresh) {
            notifyDataSetChanged();
        }
    }

    public void addGroupData(List<T> groups, List<List<T>> children, boolean isMore) {

        if (!isMore) {
            this.groups.clear();
        }
        if (groups != null) {

            this.groups.addAll(groups);
        }
        if (children != null) {

            this.children.addAll(children);
        }
        notifyDataSetChanged();
    }


    public void remove(int pos) {
        if (groups != null && groups.size() > 0) {
            groups.remove(pos);
            notifyDataSetChanged();
        }
    }

    public void clearData(boolean clear) {
        if (clear) {
            if (groups != null && groups.size() > 0) {
                groups.clear();
                notifyDataSetChanged();
            }
        }
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final AbsViewHolder viewHolder = getViewHolder(
                groupPosition, convertView, parent
        );
        convert(viewHolder, (T) getGroup(groupPosition), groupPosition);
        View v = viewHolder.getConvertView();
        v.setBackgroundColor(Color.parseColor("#F7F3E3"));
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final AbsViewHolder viewHolder = getViewHolder(
                groupPosition, convertView, parent
        );
        convert(viewHolder, (T) getChild(groupPosition, childPosition), groupPosition);
        return viewHolder.getConvertView();
    }

    @Override
// ?????????????????????
    public int getGroupCount() {
        return groups.size();
    }

    //??????????????????????????????????????????
    @Override
    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    //        ???????????????????????????
    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    //?????????????????????ID, ??????ID??????????????????
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //??????????????????ID, ??????ID??????????????????
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //???????????????????????????????????????ID, ??????????????????????????????????????????????????????
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //??????????????????????????????????????????
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public abstract void convert(AbsViewHolder helper, T item, int pos);

    private AbsViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return AbsViewHolder.get(
                mContext, convertView, parent, mItemLayoutId, position
        );
    }

    public List<T> getDatas() {
        return groups;
    }

}