package com.mike.givemewingzz.feelinbored.Utils;

import retrofit.RequestInterceptor;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class BaseUrls {

    public static final String BASE_URL = "http://api.icndb.com";

    public static final RequestInterceptor mIntercepter = new BaseUrlIntercepter();

    private static class BaseUrlIntercepter extends BaseRequestIntercepter {

        @Override
        public void intercept(RequestFacade request) {
            super.intercept(request);
        }
    }

}
