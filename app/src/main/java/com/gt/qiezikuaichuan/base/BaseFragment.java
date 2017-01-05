package com.gt.qiezikuaichuan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * Created by 巴巴 on 2017/1/4.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        distroyDisposable();
    }

    protected Boolean addtoDisposables(Disposable disposable){
        if (disposables == null){
            disposables = new ArrayList<>();
        }
        return disposables.add(disposable);
    }

    private void distroyDisposable(){
        if (disposables != null && disposables.size() > 0){
            for (Disposable disposable : disposables) {
                if (disposable != null && !disposable.isDisposed()){
                    disposable.dispose();
                }
            }
        }
    }

    private List<Disposable> disposables = null;
    private Unbinder unbinder;

}
