package com.whatever.nurseapp.nurseapp.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.whatever.nurseapp.nurseapp.R;

/**
 * Created by derrickJ on 2017/7/4.
 */

public class LoadListView extends ListView implements AbsListView.OnScrollListener {

    private View footer;
    private int totalItemCount;
    private int lastVisibleItem;
    private boolean isLoading;//正在加载
    private ILoadMoreDateListener loadMoreDateListener;

    public LoadListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadListView(Context context) {
        this(context,null);
    }

    private void initView(Context context) {
        footer=LayoutInflater.from(context).inflate(R.layout.layout_footer_more, null);
        footer.findViewById(R.id.footer).setVisibility(View.GONE);
        this.addFooterView(footer);
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (lastVisibleItem==totalItemCount&&scrollState==SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading=true;
                footer.findViewById(R.id.footer).setVisibility(View.VISIBLE);
                //加载更多数据
                loadMoreDateListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem=firstVisibleItem+visibleItemCount;
        this.totalItemCount=totalItemCount;
    }

    public void setOnILoadMoreDateListener(ILoadMoreDateListener iLoadMoreDateListener){
        this.loadMoreDateListener=iLoadMoreDateListener;
    }

    /**
     * 通知加载完毕
     */
    public void loadComplete(){
        isLoading=false;
        footer.findViewById(R.id.footer).setVisibility(View.GONE);
    }


    /**加载更多数据的回调接口
     * @author Administrator
     *
     */
    public interface ILoadMoreDateListener{

        public void onLoad();
    }

//    private View foot;      //底部加载布局
//    private int totalItemCount;     //总数量
//    private int lastVisiableItem;   //最后一个可见的item
//    private boolean isLoading;      //是否正在加载
//    private LoadListerer loadListerer;
//
//    public LoadListView(Context context) {
//        super(context);
//        initView(context);
//    }
//
//    public LoadListView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initView(context);
//    }
//
//    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initView(context);
//    }
//
//    /**
//     * 添加底部加载布局到ListView
//     *
//     * @param context
//     */
//    private void initView(Context context) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        foot = inflater.inflate(R.layout.layout_footer_more, null);
//        foot.findViewById(R.id.load_layput).setVisibility(View.GONE);
//        this.addFooterView(foot);
//        this.setOnScrollListener(this);
//    }
//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        if (totalItemCount == lastVisiableItem && scrollState == SCROLL_STATE_IDLE) {
//            if (!isLoading) {
//                isLoading = true;
//                //加载更多数据
//                foot.findViewById(R.id.load_layput).setVisibility(View.VISIBLE);
//                loadListerer.load();        //调用MainActivity的load方法
//            }
//
//        }
//    }
//
//    public void loadingComplete() {
//        isLoading = false;
//        foot.findViewById(R.id.load_layput).setVisibility(View.GONE);
//    }
//
//    /**
//     * @param view
//     * @param firstVisibleItem 第一个可见的item
//     * @param visibleItemCount 可见item的数量
//     * @param totalItemCount   item的总数
//     */
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        lastVisiableItem = firstVisibleItem + visibleItemCount;
//        this.totalItemCount = totalItemCount;
//    }
//
//    public void setInterface(LoadListerer loadListerer) {
//        this.loadListerer = loadListerer;       //拿到MainActivity的接口
//    }
//
//    public interface LoadListerer {
//        public void load();
//    }
}