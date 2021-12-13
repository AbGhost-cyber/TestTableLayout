package com.crushtech.testtablelayout;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.UNSPECIFIED;
import static android.view.View.MeasureSpec.makeMeasureSpec;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.crushtech.testtablelayout.base.RefreshParams;
import com.crushtech.testtablelayout.base.adapter.AbsCommonAdapter;
import com.crushtech.testtablelayout.base.adapter.LeftRegionsAdapter;
import com.crushtech.testtablelayout.base.adapter.RightRegionsAdapter;
import com.crushtech.testtablelayout.models.OnlineSaleBean;
import com.crushtech.testtablelayout.models.TableModel;
import com.crushtech.testtablelayout.utils.WeakHandler;
import com.crushtech.testtablelayout.widget.SyncHorizontalScrollView;
import com.crushtech.testtablelayout.widget.pullfresh.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private SparseArray<TextView> mTitleTvArray;
    //表格部分
    private TextView tv_table_title_left;
    private LinearLayout right_title_container;
    private ExpandableListView leftListView;
    private ExpandableListView rightListView;
    private AbsCommonAdapter<TableModel> mLeftAdapter, mRightAdapter;
    private SyncHorizontalScrollView titleHorScv;
    private SyncHorizontalScrollView contentHorScv;
    private AbPullToRefreshView pulltorefreshview;
    private ScrollView scrollView;
    private LinearLayout foolishL;
    private int pageNo = 0;
    private LeftRegionsAdapter leftRegionsAdapter;
    private RightRegionsAdapter rightRegionsAdapter;
    private final WeakHandler mHandler = new WeakHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = makeMeasureSpec(listView.getWidth() * listAdapter.getGroupCount(), EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            if (groupItem != null) {
                groupItem.measure(desiredWidth, UNSPECIFIED);

                totalHeight += groupItem.getMeasuredHeight();

                if (((listView.isGroupExpanded(i)) && (i != group))
                        || ((!listView.isGroupExpanded(i)) && (i == group))) {
                    for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                        View listItem = listAdapter.getChildView(i, j, false, null,
                                listView);
                        if (listItem != null) {
                            listItem.measure(desiredWidth, UNSPECIFIED);
                            totalHeight += listItem.getMeasuredHeight();
                        }

                    }
                }
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            int height = totalHeight
                    + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
            if (height < 10)
                height = 200;
            params.height = height;
            listView.setLayoutParams(params);
            listView.requestLayout();
        }
    }

    public void init() {
        findByid();
        //setData();
    }


    public void findByid() {
        pulltorefreshview = findViewById(R.id.pulltorefreshview);
        pulltorefreshview.setPullRefreshEnable(false);
        tv_table_title_left = findViewById(R.id.tv_table_title_left);
        tv_table_title_left.setText("All Items");
        leftListView = findViewById(R.id.left_container_listview);
        scrollView = findViewById(R.id.pull_refresh_scroll);
        rightListView = findViewById(R.id.right_container_listview);
        right_title_container = findViewById(R.id.right_title_container);
        //getLayoutInflater().inflate(R.layout.table_right_title, right_title_container);
        setUpM();
        titleHorScv = findViewById(R.id.title_horsv);
        contentHorScv = findViewById(R.id.content_horsv);
        // 设置两个水平控件的联动
        titleHorScv.setScrollView(contentHorScv);
        contentHorScv.setScrollView(titleHorScv);
        //  findTitleTextViewIds();
        initTableView();
        setListener();
    }

    public void setUpM() {
        for (int i = 0; i < 15; i++) {
            View view = getLayoutInflater().inflate(R.layout.table_right_title, right_title_container, false);
            ((TextView) view.findViewById(R.id.tv_table_title_0)).setText("列标题 " + i);
            right_title_container.addView(view);
        }
    }


    public void initTableView() {
        //TOP LEFT
        ArrayList<TableModel> list = new ArrayList<>();
        ArrayList<TableModel> TOP250 = new ArrayList<>();
        ArrayList<TableModel> theMan = new ArrayList<>();
        theMan.add(new TableModel("LETS GO", null));
        TOP250.add(new TableModel("The Man", theMan));
        list.add(new TableModel("TOP 250", TOP250));
        list.add(new TableModel("Now SHOWING", TOP250));
        list.add(new TableModel("Coming Soon", TOP250));

        //TOP RIGHT
        ArrayList<TableModel> list1 = new ArrayList<>();
        ArrayList<TableModel> subList = new ArrayList<>();
        ArrayList<TableModel> subList1 = new ArrayList<>();
        subList1.add(newOne(new ArrayList<>()));
        subList.add(newOne(subList1));
        list1.add(newOne(subList));
        list1.add(newOne(subList));
        list1.add(newOne(subList));

        leftRegionsAdapter = new LeftRegionsAdapter(this, list);
        rightRegionsAdapter = new RightRegionsAdapter(this, list1);


        leftListView.setAdapter(leftRegionsAdapter);
        rightListView.setAdapter(rightRegionsAdapter);
        setListViewHeight(leftListView, -1);
        setListViewHeight(rightListView, -1);

        rightRegionsAdapter.setChildClickListener((parent, v, groupPosition, id) -> {
            if (!parent.isGroupExpanded(groupPosition)) {
                leftRegionsAdapter.getCurrentListView().expandGroup(groupPosition);
            } else {
                leftRegionsAdapter.getCurrentListView().collapseGroup(groupPosition);
            }
            return false;
        });
        leftRegionsAdapter.setChildClickListener((parent, v, groupPosition, id) -> {
            if (!parent.isGroupExpanded(groupPosition)) {
                rightRegionsAdapter.getCurrentListView().expandGroup(groupPosition);
            } else {
                rightRegionsAdapter.getCurrentListView().collapseGroup(groupPosition);
            }
            return false;
        });
    }


    public void setListener() {
        pulltorefreshview.setOnHeaderRefreshListener(view -> mHandler.postDelayed(() -> {
            //        pageNo = 0;
            //      doGetDatas(0, RefreshParams.REFRESH_DATA);
        }, 1000));
        pulltorefreshview.setOnFooterLoadListener(view -> mHandler.postDelayed(() -> doGetDatas(pageNo, RefreshParams.LOAD_DATA), 1000));

        handleExpandOnce(leftListView);
        handleExpandOnce(rightListView);
        leftListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            //rightRegionsAdapter.childExpandState = -1;
            if (!parent.isGroupExpanded(groupPosition)) {
                setListViewHeight(parent, groupPosition);
                setListViewHeight(rightListView, groupPosition);
                rightListView.expandGroup(groupPosition);
            } else {
                rightListView.collapseGroup(groupPosition);
                // leftRegionsAdapter.childExpandState = -1;
            }
            return false;
        });

        rightListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            // leftRegionsAdapter.childExpandState = -1;
            if (!parent.isGroupExpanded(groupPosition)) {
                setListViewHeight(parent, groupPosition);
                setListViewHeight(leftListView, groupPosition);
                leftListView.expandGroup(groupPosition);
            } else {
                leftListView.collapseGroup(groupPosition);
                //rightRegionsAdapter.childExpandState = -1;
            }
            return false;
        });

    }

    public void setData() {
        // doGetDatas(0, RefreshParams.REFRESH_DATA);
    }

    public void handleExpandOnce(ExpandableListView listView) {
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

    //模拟网络请求
    public void doGetDatas(int pageno, int state) {
        List<OnlineSaleBean> onlineSaleBeanList = new ArrayList<>();
        for (int i = pageno * 20; i < 20 * (pageno + 1); i++) {
            onlineSaleBeanList.add(new OnlineSaleBean("行标题" + i));
        }
        if (state == RefreshParams.REFRESH_DATA) {
            pulltorefreshview.onHeaderRefreshFinish();
        } else {
            pulltorefreshview.onFooterLoadFinish();
        }
        //  setDatas(onlineSaleBeanList, state);
    }


    private TableModel newOne(List<TableModel> children) {
        TableModel tableMode = new TableModel();
        tableMode.setLeftTitle("null");
        tableMode.setText0("null");
        tableMode.setText1("null");
        tableMode.setText2("null");
        tableMode.setText3("null");
        tableMode.setText4("null");
        tableMode.setText5("null");
        tableMode.setText6("null");
        tableMode.setText7("null");
        tableMode.setText8("null");
        tableMode.setText9("null");
        tableMode.setText10("null");
        tableMode.setText11("null");
        tableMode.setText12("null");
        tableMode.setText13("null");
        tableMode.setText14("null");
        tableMode.setChildren(children);
        return tableMode;
    }

    private void setDatas(List<OnlineSaleBean> onlineSaleBeanList, int type) {
        if (onlineSaleBeanList.size() > 0) {
            List<TableModel> mDatas = new ArrayList<>();
            for (int i = 0; i < onlineSaleBeanList.size(); i++) {
                OnlineSaleBean onlineSaleBean = onlineSaleBeanList.get(i);
                TableModel tableMode = new TableModel();
                tableMode.setOrgCode(onlineSaleBean.getOrgCode());
                tableMode.setLeftTitle(onlineSaleBean.getCompanyName());
                tableMode.setText0(onlineSaleBean.getOrgCode() + "");
                tableMode.setText1(onlineSaleBean.getAreaName() + "");
                tableMode.setText2(onlineSaleBean.getSaleAll() + "");
                tableMode.setText3(onlineSaleBean.getSaleAllOneNow() + "");
                tableMode.setText4(onlineSaleBean.getSaleAllLast() + "");
                tableMode.setText5(onlineSaleBean.getSaleAllOneNowLast() + "");
                tableMode.setText6(onlineSaleBean.getSaleAllRate() + "");
                tableMode.setText7(onlineSaleBean.getSaleAllOneNowRate() + "");
                tableMode.setText8(onlineSaleBean.getRetailSale() + "");
                tableMode.setText9(onlineSaleBean.getRetailSaleOneNow() + "");
                tableMode.setText10(onlineSaleBean.getRetailSaleLast() + "");
                tableMode.setText11(onlineSaleBean.getRetailSaleOneNowLast() + "");
                tableMode.setText12(onlineSaleBean.getRetailSaleRate() + "");
                tableMode.setText13(onlineSaleBean.getRetailSaleOneNowRate() + "");
                tableMode.setText14(onlineSaleBean.getOnlineSale() + "");
                mDatas.add(tableMode);
            }
            boolean isMore = type == RefreshParams.LOAD_DATA;
//            mLeftAdapter.addGroupData(mDatas, rightChildren, grandChildrenRgt, isMore);
//            mRightAdapter.addGroupData(mDatas, leftChildren, isMore);
            //加载数据成功，增加页数
            pageNo++;
            mDatas.clear();
        } else {
            //数据为null
            if (type == RefreshParams.REFRESH_DATA) {
                mLeftAdapter.clearData(true);
                mRightAdapter.clearData(true);
                //显示数据为空的视图
            } else if (type == RefreshParams.LOAD_DATA) {
                Toast.makeText(MainActivity.this, "请求json失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}