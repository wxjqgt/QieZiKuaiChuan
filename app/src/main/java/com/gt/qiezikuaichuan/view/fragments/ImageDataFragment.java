package com.gt.qiezikuaichuan.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gt.qiezikuaichuan.R;
import com.gt.qiezikuaichuan.base.BaseFragment;
import com.gt.qiezikuaichuan.model.ImageFile;
import com.gt.qiezikuaichuan.utils.ImageUtil;
import com.gt.qiezikuaichuan.view.MaterialDesignView.recyclerView.CommonAdapter;
import com.gt.qiezikuaichuan.view.MaterialDesignView.recyclerView.ViewHolder;
import com.gt.qiezikuaichuan.view.activitys.SelectFileActivity;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ImageDataFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Disposable disposable = Observable.just(1)
                .map(new Function<Object, List<ImageFile>>() {
                    @Override
                    public List<ImageFile> apply(Object o) throws Exception {
                        List<ImageFile> paths = ImageUtil.scanImage().get("paths");
                        return paths;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ImageFile>>() {
                    @Override
                    public void accept(List<ImageFile> imageFiles) throws Exception {
                        setRecycleView(imageFiles);
                    }
                });

        addtoDisposables(disposable);

    }

    private void setRecycleView(List<ImageFile> imageFiles) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(selectFileActivity, 3);
        adapter = new CommonAdapter<ImageFile>(selectFileActivity, R.layout.image_data_item_layout, imageFiles) {

            @Override
            public void convert(ViewHolder holder, ImageFile imageFile, int position) {
                holder.setImage(R.id.image_reitem, imageFile.getPath());
            }
        };
        re_imagedata.setLayoutManager(gridLayoutManager);
        re_imagedata.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        selectFileActivity = (SelectFileActivity) context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_data;
    }

    public ImageDataFragment() {
    }

    public static ImageDataFragment newInstance() {
        ImageDataFragment fragment = new ImageDataFragment();
        return fragment;
    }

    private SelectFileActivity selectFileActivity;

    private CommonAdapter adapter;

    @BindView(R.id.re_imagedata)
    RecyclerView re_imagedata;

}
