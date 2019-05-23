package com.yz.yzdemo.mvpbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yz.network.http.HttpErrorCallBack;
import com.yz.network.http.HttpErrorInfo;
import com.yz.utils.ToastHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 残梦
 * Created by dell on 2018/3/8.
 */

public abstract class BaseFragment<P> extends Fragment {

    protected P mPresenter;

    protected View mRoot;
    protected Unbinder mRootUnBinder;

    // 标示是否第一次初始化界面
    protected boolean mIsFirstInitView = true;
    protected boolean isViewInitiated; //控件是否初始化完成

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            int layId = getContentLayoutId();
            // 初始化当前的跟布局，但是不在创建时就添加到container里边
            View root = inflater.inflate(layId, container, false);
            initView(root);
            mRoot = root;
        } else {
            if (mRoot.getParent() != null) {
                // 把当前Root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsFirstInitView) {
            // 触发一次以后就不会触发
            mIsFirstInitView = false;
            // 触发
            onFirstInit();
        }
        isViewInitiated = true;

        // 当View创建完成后初始化数据
        initData();
    }

    /**
     * 得到当前界面的资源文件Id
     *
     * @return 资源文件Id
     */
    @LayoutRes
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected void initView(View root) {
        if (mRootUnBinder == null) {
            mRootUnBinder = ButterKnife.bind(this, root);
        }
        mPresenter = createPresenter();
    }

    /**
     * 当首次初始化数据的时候会调用的方法
     */
    protected void onFirstInit() {

    }

    /**
     * 实例化P层
     * @return Presenter
     */
    protected abstract P createPresenter();

    /**
     * 初始化数据
     */
    protected void initData() {

    }


    protected void intentActivity(Class newClass){
        Intent intent = new Intent(getActivity(),newClass);
        startActivity(intent);
    }

    public void showError(Throwable throwable){
        HttpErrorInfo.getErrorInfo(throwable, new HttpErrorCallBack() {
            @Override
            public void showError(String msg) {
                showToast(msg);
            }

            @Override
            public void onLogin(String msg) {
                showToast(msg);
            }
        });
    }

    public void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)){
            ToastHelper.showLongToast(msg);
        }
    }

    public void intentLogin(){
//        EventBusUtils.postSticky(new EventLogin());
//        intentActivity(LoginActivity.class);
    }

    public void showLoading() {

    }

    public void hideLoading() {

    }

    @Override
    public void onDestroy() {
        if (mRootUnBinder!=null) {
            mRootUnBinder.unbind();
        }
        doOnFinish();
        super.onDestroy();
    }

    /**
     * 当销毁的时候使用
     */
    protected void doOnFinish() {

    }

}
