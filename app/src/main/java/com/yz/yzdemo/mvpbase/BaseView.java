package com.yz.yzdemo.mvpbase;

import android.content.Context;

public interface BaseView {

    Context getContext();

    void showToast(String msg);

    void showLoading();

    void hideLoading();

    void showError(Throwable throwable);
}
