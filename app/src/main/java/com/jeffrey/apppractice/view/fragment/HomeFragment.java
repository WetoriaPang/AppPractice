package com.jeffrey.apppractice.view.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jeffrey.apppractice.R;
import com.jeffrey.apppractice.view.BaseFragment;

/**
 * Created by pangjiaqi on 2017/4/15.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    /**
     * UI
     */
    private View mHomeView;
    private View mContentView;
    private ListView mListView;
    private TextView mQRCodeView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHomeView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mHomeView;
    }

    /**
     * 初始化控件
     */
    private void initView() {

        mQRCodeView = (TextView) mHomeView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView = (TextView) mHomeView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = (TextView) mHomeView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = (ListView) mHomeView.findViewById(R.id.list_view_home);
        mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) mHomeView.findViewById(R.id.loading_view);
        AnimationDrawable animation = (AnimationDrawable) mLoadingView.getDrawable();
        animation.start();

    }

    /**
     * 控件的点击事件处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

    }

    /**
     * listview item点击处理
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
