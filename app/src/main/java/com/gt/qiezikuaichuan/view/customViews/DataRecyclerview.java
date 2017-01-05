package com.gt.qiezikuaichuan.view.customViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by 巴巴 on 2017/1/5.
 */

public class DataRecyclerview extends RecyclerView {

    public DataRecyclerview(Context context) {
        super(context);
    }

    public DataRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DataRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }
}
