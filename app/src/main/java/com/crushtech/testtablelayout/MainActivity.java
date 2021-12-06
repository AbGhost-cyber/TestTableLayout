package com.crushtech.testtablelayout;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.crushtech.testtablelayout.base.RefreshParams;
import com.crushtech.testtablelayout.base.adapter.AbsCommonAdapter;
import com.crushtech.testtablelayout.base.adapter.AbsViewHolder;
import com.crushtech.testtablelayout.models.OnlineSaleBean;
import com.crushtech.testtablelayout.models.TableModel;
import com.crushtech.testtablelayout.utils.WeakHandler;
import com.crushtech.testtablelayout.widget.SyncHorizontalScrollView;
import com.crushtech.testtablelayout.widget.pullfresh.AbPullToRefreshView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SparseArray<TextView> mTitleTvArray;
    //表格部分
    private TextView tv_table_title_left;
    private LinearLayout right_title_container;
    private ListView leftListView;
    private ListView rightListView;
    private AbsCommonAdapter<TableModel> mLeftAdapter, mRightAdapter;
    private SyncHorizontalScrollView titleHorScv;
    private SyncHorizontalScrollView contentHorScv;
    private AbPullToRefreshView pulltorefreshview;
    private int pageNo = 0;
    private final WeakHandler mHandler = new WeakHandler();
    private ArrayList<String> ss = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        findByid();
        setListener();
        setData();
    }

    // 2 items, 3...3

    public void findByid() {
        pulltorefreshview = findViewById(R.id.pulltorefreshview);
//        pulltorefreshview.setPullRefreshEnable(false);
        tv_table_title_left = findViewById(R.id.tv_table_title_left);
        tv_table_title_left.setText("all items");
        leftListView = findViewById(R.id.left_container_listview);
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
    }

    public void setUpM() {
        for (int i = 0; i < 15; i++) {
            View view = getLayoutInflater().inflate(R.layout.table_right_title, right_title_container,false);
            ((TextView) view.findViewById(R.id.tv_table_title_0)).setText("列标题 " + i);
            right_title_container.addView(view);
        }
    }

    /**
     * 初始化标题的TextView的item引用
     */
    private void findTitleTextViewIds() {
        mTitleTvArray = new SparseArray<>();
        for (int i = 0; i <= 20; i++) {
            try {
                Field field = R.id.class.getField("tv_table_title_" + 0);
                int key = field.getInt(R.id.my_edit_text_1);
                TextView textView = (TextView) findViewById(key);
                mTitleTvArray.put(key, textView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void initTableView() {
        mLeftAdapter = new AbsCommonAdapter<TableModel>(this, R.layout.table_left_item) {
            @Override
            public void convert(AbsViewHolder helper, TableModel item, int pos) {
                TextView tv_table_content_left = helper.getView(R.id.tv_table_content_item_left);
                tv_table_content_left.setText(item.getLeftTitle());
            }
        };
        mRightAdapter = new AbsCommonAdapter<TableModel>(this, R.layout.table_right_item) {
            @Override
            public void convert(AbsViewHolder helper, TableModel item, int pos) {
                TextView tv_table_content_right_item0 = helper.getView(R.id.tv_table_content_right_item0);
                TextView tv_table_content_right_item1 = helper.getView(R.id.tv_table_content_right_item1);
                TextView tv_table_content_right_item2 = helper.getView(R.id.tv_table_content_right_item2);
                TextView tv_table_content_right_item3 = helper.getView(R.id.tv_table_content_right_item3);
                TextView tv_table_content_right_item4 = helper.getView(R.id.tv_table_content_right_item4);
                TextView tv_table_content_right_item5 = helper.getView(R.id.tv_table_content_right_item5);
                TextView tv_table_content_right_item6 = helper.getView(R.id.tv_table_content_right_item6);
                TextView tv_table_content_right_item7 = helper.getView(R.id.tv_table_content_right_item7);
                TextView tv_table_content_right_item8 = helper.getView(R.id.tv_table_content_right_item8);
                TextView tv_table_content_right_item9 = helper.getView(R.id.tv_table_content_right_item9);
                TextView tv_table_content_right_item10 = helper.getView(R.id.tv_table_content_right_item10);
                TextView tv_table_content_right_item11 = helper.getView(R.id.tv_table_content_right_item11);
                TextView tv_table_content_right_item12 = helper.getView(R.id.tv_table_content_right_item12);
                TextView tv_table_content_right_item13 = helper.getView(R.id.tv_table_content_right_item13);
                TextView tv_table_content_right_item14 = helper.getView(R.id.tv_table_content_right_item14);

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

                //部分行设置颜色凸显
                item.setTextColor(tv_table_content_right_item0, item.getText0());
                item.setTextColor(tv_table_content_right_item5, item.getText5());
                item.setTextColor(tv_table_content_right_item10, item.getText10());
                item.setTextColor(tv_table_content_right_item14, item.getText14());

                for (int i = 0; i < 15; i++) {
                    View view = ((LinearLayout) helper.getConvertView()).getChildAt(i);
                    view.setVisibility(View.VISIBLE);
                }
            }
        };
        leftListView.setAdapter(mLeftAdapter);
        rightListView.setAdapter(mRightAdapter);
    }


    public void setListener() {
        pulltorefreshview.setOnHeaderRefreshListener(view -> mHandler.postDelayed(() -> {
            pageNo = 0;
            doGetDatas(0, RefreshParams.REFRESH_DATA);
        }, 1000));
        pulltorefreshview.setOnFooterLoadListener(view -> mHandler.postDelayed(() -> doGetDatas(pageNo, RefreshParams.LOAD_DATA), 1000));
        leftListView.setOnItemClickListener((parent, view, position, id) -> {
            //跳转界面
            Toast.makeText(MainActivity.this, "打开某条记录的单独详情", Toast.LENGTH_SHORT).show();
        });
    }

    public void setData() {
        doGetDatas(0, RefreshParams.REFRESH_DATA);
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
        setDatas(onlineSaleBeanList, state);
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
            boolean isMore;
            isMore = type == RefreshParams.LOAD_DATA;
            mLeftAdapter.addData(mDatas, isMore);
            mRightAdapter.addData(mDatas, isMore);
            //加载数据成功，增加页数
            pageNo++;
            mDatas.clear();
        } else {
            //数据为null
            if (type == RefreshParams.REFRESH_DATA) {
                mLeftAdapter.clearData(true);
                mRightAdapter.clearData(true);
                //显示数据为空的视图
                //                mEmpty.setShowErrorAndPic(getString(R.string.empty_null), 0);
            } else if (type == RefreshParams.LOAD_DATA) {
                Toast.makeText(MainActivity.this, "请求json失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}