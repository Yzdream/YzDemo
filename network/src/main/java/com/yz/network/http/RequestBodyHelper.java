package com.yz.network.http;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestBodyHelper {
    private MediaType mediaType;
    private Gson gson;

    private RequestBodyHelper() {
        mediaType = MediaType.parse("application/json; charset=utf-8");
        gson = new Gson();
    }

    private static class SingletonHolder {
        private static final RequestBodyHelper INSTANCE = new RequestBodyHelper();
    }

    public static RequestBodyHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public RequestBody createObjectBody(Object o) {
        String json = gson.toJson(o);
        Logger.json(json);
        return RequestBody.create(mediaType, json);
    }

}
