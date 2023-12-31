package com.example.firstdemo.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.firstdemo.R;
import com.example.firstdemo.activity.BaseActivity;
import com.example.firstdemo.adapter.MyPagerAdapter;
import com.example.firstdemo.entity.TabEntity;
import com.example.firstdemo.fragment.HomeFragment;
import com.example.firstdemo.fragment.MyFragment;
import com.example.firstdemo.fragment.QuestionFragment;
import com.example.firstdemo.fragment.SystemFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private String[] mTitles = {"首页", "问答", "体系", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.home_unselect, R.mipmap.question_unselect,
            R.mipmap.system_unselect, R.mipmap.my_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.home_selected2, R.mipmap.question_selected2,
            R.mipmap.system_selected2, R.mipmap.my_selected2};


    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //起始页面动画
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initData() {

        mFragments.add(HomeFragment.newInstance());
        mFragments.add(QuestionFragment.newInstance());
        mFragments.add(SystemFragment.newInstance());
        mFragments.add(MyFragment.newInstance());

        //封装按钮实体对象
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        //把实体按钮对象放入导航栏
        commonTabLayout.setTabData(mTabEntities);
        //绑定点击事件
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        //预加载fragment
        viewPager.setOffscreenPageLimit(mFragments.size());

        //滑动时按钮跟着改变
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //通过adapter绑定fragment到viewpage中
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitles, mFragments));


    }

}