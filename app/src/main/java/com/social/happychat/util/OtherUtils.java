package com.social.happychat.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author conanaiflj
 */
public class OtherUtils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static long lastClickTime;

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */


    /**
     * 在某种获取不到 Context 的情况下，即可以使用才方法获取 Context
     * <p>
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("请先调用init()方法");
    }
    //==============================================================================================延时任务封装 end

    //----------------------------------------------------------------------------------------------延时任务封装 start

    /**
     * 倒计时
     *
     * @param textView 控件
     * @param waitTime 倒计时总时长
     * @param interval 倒计时的间隔时间
     * @param hint     倒计时完毕时显示的文字
     */
    public static void countDown(final TextView textView, long waitTime, long interval, final String hint) {
        textView.setEnabled(false);
        android.os.CountDownTimer timer = new android.os.CountDownTimer(waitTime, interval) {

            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.format("剩下 %d S", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setText(hint);
            }
        };
        timer.start();
    }

    /**
     * 手动计算出listView的高度，但是不再具有滚动效果
     *
     * @param listView
     */
    public static void fixListViewHeight(ListView listView) {
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        if (listAdapter == null) {
            return;
        }
        for (int index = 0, len = listAdapter.getCount(); index < len; index++) {
            View listViewItem = listAdapter.getView(index, null, listView);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有子项的高度
            totalHeight += listViewItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    //---------------------------------------------MD5加密-------------------------------------------

    /**
     * 生成MD5加密32位字符串
     *
     * @param MStr :需要加密的字符串
     * @return
     */
    public static String Md5(String MStr) {
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(MStr.getBytes());
            return bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(MStr.hashCode());
        }
    }

    // MD5内部算法---------------不能修改!
    private static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
    //============================================MD5加密============================================

    /**
     * 根据资源名称获取资源 id
     * <p>
     * 不提倡使用这个方法获取资源,比其直接获取ID效率慢
     * <p>
     * 例如
     * getResources().getIdentifier("ic_launcher", "drawable", getPackageName());
     *
     * @param context
     * @param name
     * @param defType
     * @return
     */
    public final static int getResIdByName(Context context, String name, String defType) {
        return context.getResources().getIdentifier("ic_launcher", "drawable", context.getPackageName());
    }

    public static boolean isFastClick(int millisecond) {
        long curClickTime = System.currentTimeMillis();
        long interval = (curClickTime - lastClickTime);

        if (0 < interval && interval < millisecond) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            return true;
        }
        lastClickTime = curClickTime;
        return false;
    }

    /**
     * Edittext 首位小数点自动加零，最多两位小数
     *
     * @param editText
     */
    public static void setEdTwoDecimal(EditText editText) {
        setEdDecimal(editText, 2);
    }

    /**
     * @param editText
     */
    public static void setEdType(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void
            beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void
            onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = editText.getText().toString();
                String str = stringFilter(editable);
                if (!editable.equals(str)) {
                    editText.setText(str);
                    //设置新的光标所在位置
                    editText.setSelection(str.length());
                }
            }

            @Override
            public void
            afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * // 只允许数字和汉字
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {

        String regEx = "[^0-9\u4E00-\u9FA5]";//正则表达式
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static void setEdDecimal(EditText editText, int count) {
        if (count < 0) {
            count = 0;
        }

        count += 1;

        editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);

        //设置字符过滤
        final int finalCount = count;
        editText.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (".".contentEquals(source) && dest.toString().length() == 0) {
                    return "0.";
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int mlength = dest.toString().substring(index).length();
                    if (mlength == finalCount) {
                        return "";
                    }
                }

                if (dest.toString().equals("0") && source.equals("0")) {
                    return "";
                }

                return null;
            }
        }});
    }

    /**
     * @param editText       输入框控件
     * @param number         位数
     *                       1 -> 1
     *                       2 -> 01
     *                       3 -> 001
     *                       4 -> 0001
     * @param isStartForZero 是否从000开始
     *                       true -> 从 000 开始
     *                       false -> 从 001 开始
     */
    public static void setEditNumberAuto(final EditText editText, final int number, final boolean isStartForZero) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setEditNumber(editText, number, isStartForZero);
                }
            }
        });
    }

    /**
     * @param editText       输入框控件
     * @param number         位数
     *                       1 -> 1
     *                       2 -> 01
     *                       3 -> 001
     *                       4 -> 0001
     * @param isStartForZero 是否从000开始
     *                       true -> 从 000 开始
     *                       false -> 从 001 开始
     */
    public static void setEditNumber(EditText editText, int number, boolean isStartForZero) {
        StringBuilder s = new StringBuilder(editText.getText().toString());
        StringBuilder temp = new StringBuilder();

        int i;
        for (i = s.length(); i < number; ++i) {
            s.insert(0, "0");
        }
        if (!isStartForZero) {
            for (i = 0; i < number; ++i) {
                temp.append("0");
            }

            if (s.toString().equals(temp.toString())) {
                s = new StringBuilder(temp.substring(1) + "1");
            }
        }
        editText.setText(s.toString());
    }

    /**
     * 获取
     * @return
     */
    public static Handler getBackgroundHandler() {
        HandlerThread thread = new HandlerThread("background");
        thread.start();
        return new Handler(thread.getLooper());
    }


    /**
     * 将yyyy-MM-dd HH:mm:ss转化为MM-dd HH:mm:ss
     * @param dateStr
     * @return
     */
    public static String convertTimeStr(String dateStr){
        if(TextUtils.isEmpty(dateStr)){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format2 = new SimpleDateFormat("MM-dd HH:mm");
        try {
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dateStr);
            return format2.format(date);
//            System.out.print(format2.format(date));
            // 获取日期实例
//            Calendar calendar = Calendar.getInstance();
//            // 将日历设置为指定的时间
//            calendar.setTime(date);
//            // 获取年
//            int year = calendar.get(Calendar.YEAR);
//            // 这里要注意，月份是从0开始。
//            int month = calendar.get(Calendar.MONTH);
//            // 获取天
//            int day = calendar.get(Calendar.DAY_OF_MONTH);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 将yyyy-MM转化为yyyy年MM月
     * @param dateStr
     * @return
     */
    public static String convertTimeStr2(String dateStr){
        if(TextUtils.isEmpty(dateStr)){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月");
        try {
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dateStr);
            return format2.format(date);
//            System.out.print(format2.format(date));
            // 获取日期实例
//            Calendar calendar = Calendar.getInstance();
//            // 将日历设置为指定的时间
//            calendar.setTime(date);
//            // 获取年
//            int year = calendar.get(Calendar.YEAR);
//            // 这里要注意，月份是从0开始。
//            int month = calendar.get(Calendar.MONTH);
//            // 获取天
//            int day = calendar.get(Calendar.DAY_OF_MONTH);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 手机号用****号隐藏中间数字
     *
     * @param phone
     * @return
     */
    public static String settingphone(String phone) {
        String phone_s = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return phone_s;
    }

    /**
     *
     * @param startTime
     * @param endTime
     * @return 返回true  startTime<endTime
     */
    public static boolean compareTime(String startTime, String endTime) {
        return startTime.compareTo(endTime)<=0;
    }

    /**
     *
     * @return
     */
    public static String addZeroForNumber(int origin){
        String s = String.format("%02d", origin);
        return s;
    }

}
