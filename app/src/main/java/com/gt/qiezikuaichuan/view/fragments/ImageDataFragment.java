package com.gt.qiezikuaichuan.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.gt.qiezikuaichuan.R;
import com.gt.qiezikuaichuan.base.BaseFragment;
import com.gt.qiezikuaichuan.model.ImageFile;
import com.gt.qiezikuaichuan.utils.ImageUtil;
import com.gt.qiezikuaichuan.view.MaterialDesignView.recyclerView.CommonAdapter;
import com.gt.qiezikuaichuan.view.MaterialDesignView.recyclerView.HeaderdAndFooterWrapper;
import com.gt.qiezikuaichuan.view.MaterialDesignView.recyclerView.ViewHolder;
import com.gt.qiezikuaichuan.view.activitys.SelectFileActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class ImageDataFragment extends BaseFragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addtoDisposables(createObservable(0, 20).subscribe(new Consumer<List<ImageFile>>() {
            @Override
            public void accept(List<ImageFile> imageFiles) throws Exception {
                setRecycleView(imageFiles);
            }
        }));
    }

    private void setRecycleView(List<ImageFile> imageFiles) {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(selectFileActivity, 3);
        adapter = new CommonAdapter<ImageFile>(selectFileActivity, R.layout.image_data_item_layout, imageFiles) {

            @Override
            public void convert(ViewHolder holder, ImageFile imageFile, int position) {
                holder.setImage(R.id.image_reitem, imageFile.getPath());
            }
        };
        re_imagedata.setLayoutManager(gridLayoutManager);
        re_imagedata.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                final int LastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    addtoDisposables(createObservable(LastVisibleItemPosition, LastVisibleItemPosition + 20).subscribe(new Consumer<List<ImageFile>>() {
                        @Override
                        public void accept(List<ImageFile> imageFiles) throws Exception {
                            adapter.addDatas(imageFiles);
                        }
                    }));
                }
            }
        });
        re_imagedata.setAdapter(adapter);
    }

    private Observable<List<ImageFile>> createObservable(final int start, final int end) {
        return Observable.just(1)
                .map(new Function<Object, List<ImageFile>>() {
                    @Override
                    public List<ImageFile> apply(Object o) throws Exception {
                        if (imagemap == null) {
                            imagemap = ImageUtil.scanImage();
                        }
                        List<ImageFile> paths = imagemap.get("paths");
                        if (end < paths.size()) {
                            return paths.subList(start, end);
                        } else {
                            return paths.subList(start, paths.size() - 1);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


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
    private HashMap<String, List<ImageFile>> imagemap;
    private CommonAdapter adapter;

    @BindView(R.id.re_imagedata)
    RecyclerView re_imagedata;

}
