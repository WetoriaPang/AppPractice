package com.jeffrey.apppractice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeffrey.apppractice.R;
import com.jeffrey.apppractice.activity.base.BaseActivity;
import com.jeffrey.apppractice.view.fragment.HomeFragment;
import com.jeffrey.apppractice.view.fragment.MessageFragment;
import com.jeffrey.apppractice.view.fragment.MineFragment;

/**
 * Created by pangjiaqi on 2017/4/15.
 * 创建首页所有的fragment
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {


    private TextView mTVHomeImage;
    private TextView mTvMessageImage;
    private TextView mTvMineImage;

    private RelativeLayout mRlHome;
    private RelativeLayout mRlMessage;
    private RelativeLayout mRlMine;

    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private FragmentManager mFragmentManager;
    private Fragment mCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        initViews();
        mHomeFragment = new HomeFragment();
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTranscation = mFragmentManager.beginTransaction();
        fragmentTranscation.replace(R.id.rl_content_layout, mHomeFragment);
        fragmentTranscation.commit();
    }

    /**
     * 初始化
     */
    private void initViews() {

        mRlHome = (RelativeLayout) findViewById(R.id.rl_home_layout);
        mRlHome.setOnClickListener(this);

        mRlMessage = (RelativeLayout) findViewById(R.id.rl_message_layout);
        mRlMessage.setOnClickListener(this);

        mRlMine = (RelativeLayout) findViewById(R.id.rl_mine_layout);
        mRlMine.setOnClickListener(this);

        mTVHomeImage = (TextView) findViewById(R.id.tv_home_image_view);
        mTvMessageImage = (TextView) findViewById(R.id.tv_message_image_view);
        mTvMineImage = (TextView) findViewById(R.id.tv_mine_image_view);
        mTVHomeImage.setBackgroundResource(R.drawable.comui_tab_home_selected);

    }

    /**
     * 隐藏fragment
     *
     * @param fragment
     * @param ft
     */
    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.rl_home_layout:
                mTVHomeImage.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mTvMessageImage.setBackgroundResource(R.drawable.comui_tab_message);
                mTvMineImage.setBackgroundResource(R.drawable.comui_tab_person);
                //隐藏其他fragment
                hideFragment(mMessageFragment, ft);
                hideFragment(mMineFragment, ft);
                //显示当前fragment
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    ft.add(R.id.rl_content_layout, mHomeFragment);
                } else {
                    mCurrent = mHomeFragment;
                    ft.show(mHomeFragment);
                }
                break;
            case R.id.rl_message_layout:
                mTVHomeImage.setBackgroundResource(R.drawable.comui_tab_home);
                mTvMessageImage.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mTvMineImage.setBackgroundResource(R.drawable.comui_tab_person);
                //隐藏其他fragment
                hideFragment(mHomeFragment, ft);
                hideFragment(mMineFragment, ft);
                //显示当前fragment
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                    ft.add(R.id.rl_content_layout, mMessageFragment);
                } else {
                    mCurrent = mMessageFragment;
                    ft.show(mMessageFragment);
                }
                break;
            case R.id.rl_mine_layout:
                mTVHomeImage.setBackgroundResource(R.drawable.comui_tab_home);
                mTvMessageImage.setBackgroundResource(R.drawable.comui_tab_message);
                mTvMineImage.setBackgroundResource(R.drawable.comui_tab_person_selected);
                //隐藏其他fragment
                hideFragment(mMessageFragment, ft);
                hideFragment(mHomeFragment, ft);
                //显示当前fragment
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    ft.add(R.id.rl_content_layout, mMineFragment);
                } else {
                    mCurrent = mMineFragment;
                    ft.show(mMineFragment);
                }
                break;
        }

        ft.commit();
    }
}
