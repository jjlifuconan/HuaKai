package com.social.basecommon.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.model.stream.HttpUriLoader;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Ensures that Glide's generated API is created for the Contact Uri sample.
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
  // Intentionally empty.

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client);
//        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
//        val factory = new OkHttpUrlLoader.Factory(client);
//        val factory = new HttpUriLoader.Factory();
//        registry.replace(GlideUrl.class, InputStream.class, factory);
//
        glide.getRegistry().replace(GlideUrl.class, InputStream.class, factory);


    }
}
