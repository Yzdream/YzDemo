package com.yz.data.http;

import com.yz.network.http.RetofitHttp;

public class RetrofitService {

    private static Api api;

    private static RetrofitService service;
    private static RetrofitService longTimeService;

    public static RetrofitService getService(){
        if (service == null){
            synchronized (RetrofitService.class) {
                if (service == null) {
                    service = new RetrofitService();
                    api = RetofitHttp.getInstance().getRetrofit().create(Api.class);
                }
            }
        }
        return service;
    }

    public static RetrofitService getLongTimeService(){
        if (longTimeService == null){
            synchronized (RetrofitService.class) {
                if (longTimeService == null) {
                    longTimeService = new RetrofitService();
                    api = RetofitHttp.getLongTimeInstance().getRetrofit().create(Api.class);
                }
            }
        }
        return service;
    }

    public Api getApi(){
        return api;
    }

}
