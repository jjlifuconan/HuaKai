package com.social.basecommon.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.social.basecommon.R;


/**
 * author:conanaiflj
 * date:2018/5/25 0025
 * description:
 */
public class ToastUtil {
    private static Toast toast;

    private static void makeText(Context context, String content, int duration){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, content, duration);
        toast.show();
    }

    private static void makeText(Context context, @StringRes int id, int duration){
        makeText(context, context.getApplicationContext().getResources().getString(id), duration);
    }


    public static void show(Context context, @StringRes int id){
        makeText(context, id ,Toast.LENGTH_LONG);
    }

    public static void showLong(Context context, @StringRes int id){
        makeText(context, id ,Toast.LENGTH_LONG);
    }

    public static void showShort(Context context, @StringRes int id){
        makeText(context, id ,Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String content){
        makeText(context, content ,Toast.LENGTH_LONG);
    }

    public static void showLong(Context context, String content){
        makeText(context, content ,Toast.LENGTH_LONG);
    }

    public static void showShort(Context context, String content){
        makeText(context, content ,Toast.LENGTH_SHORT);
    }


}
