package com.yz.yzdemo.mvpbase;

import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V extends BaseView>{

    public CompositeSubscription compositeSubscription;

    public V mView;

    public BasePresenter(V mvpView) {
        this.mView = mvpView;
        compositeSubscription = new CompositeSubscription();
    }

    public void onDestroy(){
        if (compositeSubscription!=null&& compositeSubscription.hasSubscriptions())
            compositeSubscription.unsubscribe();
    }
}
