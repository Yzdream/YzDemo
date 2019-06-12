package com.yz.network.http;


public class HttpMethods {

    public final static String BASE_URL = "";

    private RequestBodyHelper bodyHelper = RequestBodyHelper.getInstance();
    private static final HttpMethods INSTANCE = new HttpMethods();

    public static HttpMethods getInStance() {
        return INSTANCE;
    }





}
