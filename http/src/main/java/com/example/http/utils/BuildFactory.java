package com.example.http.utils;

import com.example.http.HttpUtils;

import static com.example.http.HttpUtils.API_GANKIO;
import static com.example.http.HttpUtils.API_NETEASE;
import static com.example.http.HttpUtils.API_REAL;

/**
 * @author jingbin
 * @data 2018/2/9
 * @Description
 */

public class BuildFactory {

    private static BuildFactory instance;
    private Object gankHttps;
    private Object neteaseHttps;
    private Object realHttps;

    public static BuildFactory getInstance() {
        if (instance == null) {
            synchronized (BuildFactory.class) {
                if (instance == null) {
                    instance = new BuildFactory();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> a, String type) {

        switch (type) {
            case API_GANKIO:
                if (gankHttps == null) {
                    synchronized (BuildFactory.class) {
                        if (gankHttps == null) {
                            gankHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
                        }
                    }
                }
                return (T) gankHttps;
            case API_NETEASE:
                if (neteaseHttps == null) {
                    synchronized (BuildFactory.class) {
                        if (neteaseHttps == null) {
                            neteaseHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
                        }
                    }
                }
                return (T) neteaseHttps;
            case API_REAL:
                if (realHttps == null) {
                    synchronized (BuildFactory.class) {
                        if (realHttps == null) {
                            realHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
                        }
                    }
                }
                return (T) realHttps;
            default:
                if (gankHttps == null) {
                    synchronized (BuildFactory.class) {
                        if (gankHttps == null) {
                            gankHttps = HttpUtils.getInstance().getBuilder(type).build().create(a);
                        }
                    }
                }
                return (T) gankHttps;
        }
    }

}
