package com.gt.qiezikuaichuan.view.activitys;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gt.qiezikuaichuan.R;
import com.gt.qiezikuaichuan.adapters.ViewPagerFragmentAdapter;
import com.gt.qiezikuaichuan.base.BaseActivity;
import com.gt.qiezikuaichuan.view.fragments.AppDataFragment;
import com.gt.qiezikuaichuan.view.fragments.FileDataFragment;
import com.gt.qiezikuaichuan.view.fragments.ImageDataFragment;
import com.gt.qiezikuaichuan.view.fragments.MovieDataFragment;
import com.gt.qiezikuaichuan.view.fragments.MusicDataFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SelectFileActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton_return:
                finish();
                break;
            case R.id.imageButton_search:

                break;
            default:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        dataViewTitle.setTextColor(Color.WHITE);

        imageButtonReturn.setOnClickListener(this);
        imageButtonSearch.setOnClickListener(this);
        initMagicIndicator();

        initViewPager();
    }

    private void initViewPager() {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FileDataFragment.newInstance());
        fragments.add(AppDataFragment.newInstance());
        fragments.add(MovieDataFragment.newInstance());
        fragments.add(MusicDataFragment.newInstance());
        fragments.add(ImageDataFragment.newInstance());

        data_Viewpager.setAdapter(new ViewPagerFragmentAdapter(fragments, getSupportFragmentManager()));

    }

    private void initMagicIndicator() {

        mTitleDataList.add("文件");
        mTitleDataList.add("应用");
        mTitleDataList.add("视频");
        mTitleDataList.add("音乐");
        mTitleDataList.add("图片");

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.colorPrimary));
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data_Viewpager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(getResources().getColor(R.color.colorPrimary));
                indicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                return indicator;
            }
        });
        indicator.setNavigator(commonNavigator);

        ViewPagerHelper.bind(indicator, data_Viewpager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_file;
    }

    private List<String> mTitleDataList = new ArrayList<>();

    @BindView(R.id.dataViewTitle)
    TextView dataViewTitle;

    @BindView(R.id.data_view_pager)
    ViewPager data_Viewpager;

    @BindView(R.id.data_magic_indicator)
    MagicIndicator indicator;

    @BindView(R.id.imageButton_return)
    ImageButton imageButtonReturn;

    @BindView(R.id.imageButton_search)
    ImageButton imageButtonSearch;

}
