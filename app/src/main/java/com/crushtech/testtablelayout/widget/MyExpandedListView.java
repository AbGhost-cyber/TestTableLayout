package com.crushtech.testtablelayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

public class MyExpandedListView extends ExpandableListView {

    public MyExpandedListView(Context context) {
        super(context);
    }

    public MyExpandedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyExpandedListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(999999,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}  