package com.yz.yzdemo.mvpbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.yz.network.http.HttpErrorCallBack;
import com.yz.network.http.HttpErrorInfo;
import com.yz.utils.Density;
import com.yz.utils.EventBusUtils;
import com.yz.utils.ToastHelper;
import com.yz.yzdemo.R;

import butterknife.ButterKnife;


/**
 * 残梦
 * Created by dell on 2018/6/1.
 */

public abstract class BaseActivity<P> extends AppCompatActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在界面未初始化之前调用的初始化窗口
        initWidows();
        if (initArgs(getIntent().getExtras())) {
            //是否使用适配方案
            if(initDensity()) Density.setDefault(this);
            // 得到界面Id并设置到Activity界面中
            setContentView(getContentLayoutId());
            initBefore();
            initView(savedInstanceState);
            initData();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        doOnDestroy();
        super.onDestroy();
    }

    /**
     * 界面销毁时可以调用
     */
    protected void doOnDestroy() {

    }

    /**
     * 初始化控件调用之前
     */
    protected void initBefore() {
    }

    /**
     * 实例化P层
     * @return Presenter
     */
    protected abstract P createPresenter();

    /**
     * 初始化窗口
     */
    protected void initWidows() {

    }

    protected void intentActivity(Class newClass){
        Intent intent = new Intent(this,newClass);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
    }

    /**
     * 初始化相关参数
     *
     * @param bundle 参数Bundle
     * @return 如果参数正确返回True，错误返回False
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     *初始化适配方案 默认为适配
     * @return 是否使用今日头条适配
     */
    protected boolean initDensity(){
        return true;
    }

    /**
     * 得到当前界面的资源文件Id
     *
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected void initView(@Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mPresenter = createPresenter();
    }

    /**
     * 初始化数据
     */
    protected void initData() {

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
    public void onResume() {
        super.onResume();
        if (!"NewMainActivity".equals(getClass().getSimpleName())) {
//            MobclickAgent.onPageStart(getClass().getSimpleName());
        }
//        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        if (!"NewMainActivity".equals(getClass().getSimpleName())) {
//            MobclickAgent.onPageEnd(getClass().getSimpleName());
//        }
//        MobclickAgent.onPause(this);
    }

}
