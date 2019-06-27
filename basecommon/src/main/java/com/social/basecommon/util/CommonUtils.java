package com.social.basecommon.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by jingbin on 2016/11/22.
 * 获取原生资源
 */
public class CommonUtils {

    /**
     * 随机颜色
     */
    public static int randomColor() {
        Random random = new Random();
        int red = random.nextInt(150) + 50;//50-199
        int green = random.nextInt(150) + 50;//50-199
        int blue = random.nextInt(150) + 50;//50-199
        return Color.rgb(red, green, blue);
    }

    /**
     * 得到年月日的"日"
     */
    private String getDate() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("dd");
        return dateFm.format(date);
    }


    public static Drawable getDrawable(Context context, int resid) {
        return ContextCompat.getDrawable(context.getApplicationContext(), resid);
//        return getResoure().getDrawable(resid);
    }

    public static int getColor(Context context, int resid) {
        return getResoure(context).getColor(resid);
    }

    public static Resources getResoure(Context context) {
        return context.getApplicationContext().getResources();
    }

    public static String[] getStringArray(Context context, int resid) {
        return getResoure(context).getStringArray(resid);
    }

    public static String getString(Context context, int resid) {
        return getResoure(context).getString(resid);
    }

    public static float getDimens(Context context, int resId) {
        return getResoure(context).getDimension(resId);
    }

    public static void removeSelfFromParent(View child) {

        if (child != null) {

            ViewParent parent = child.getParent();

            if (parent instanceof ViewGroup) {

                ViewGroup group = (ViewGroup) parent;

                group.removeView(child);
            }
        }
    }

}
