package com.social.happychat.ui.home.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.social.basecommon.util.DensityUtil;
import com.social.basecommon.util.ToastUtil;
import com.social.happychat.R;
import com.social.happychat.ui.home.interfaces.DialogFragmentDataCallback;


/**
 * author:conanaiflj
 * date:2018/8/16 0016
 * description:
 */
public class CommentDialogFragment extends DialogFragment implements View.OnClickListener{
    private View root;
    private EditText commentEditText;
    private ImageView btn_send;
    private InputMethodManager inputMethodManager;
    private DialogFragmentDataCallback dataCallback;

    private boolean isSoftAutoPushUp = false;//是不是一进来第一次自动弹出软键盘

    @Override
    public void onAttach(Context context) {
        if (!(getActivity() instanceof DialogFragmentDataCallback)) {
            throw new IllegalStateException("DialogFragment 所在的 activity 必须实现 DialogFragmentDataCallback 接口");
        }
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog mDialog = new Dialog(getActivity(), R.style.BottomDialog);
//        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_fragment_comment_layout);
        mDialog.setCanceledOnTouchOutside(true);

        Window window = mDialog.getWindow();
        WindowManager.LayoutParams layoutParams;
        if (window != null) {
            layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }

        root = mDialog.findViewById(R.id.root);
        commentEditText = (EditText) mDialog.findViewById(R.id.edit_comment);
        if(getArguments()!=null && !TextUtils.isEmpty(getArguments().getString("nickname"))){
            commentEditText.setHint("回复"+getArguments().getString("nickname")+"：");
        }
        btn_send = (ImageView) mDialog.findViewById(R.id.btn_send);
        btn_send.setEnabled(false);
        commentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s.toString().trim())){
                    btn_send.setEnabled(true);
                    btn_send.setImageResource(R.mipmap.video_evaluate_button_on);
                }else{
                    btn_send.setEnabled(false);
                    btn_send.setImageResource(R.mipmap.video_evaluate_button_off);
                }

            }
        });

        fillEditText();
        setSoftKeyboard();

        btn_send.setOnClickListener(this);
        return mDialog;
    }

    private void fillEditText() {
        dataCallback = (DialogFragmentDataCallback) getActivity();
        if(dataCallback.getCommentToWhichUserid() != 0){
            if(dataCallback.getCommentToWhichUserid() == getArguments().getInt("userId")){
                commentEditText.setText(dataCallback.getCommentText());
                commentEditText.setSelection(dataCallback.getCommentText().length());
            }
        }else{
            commentEditText.setText(dataCallback.getCommentText());
            commentEditText.setSelection(dataCallback.getCommentText().length());
        }
//        if (dataCallback.getCommentText().length() == 0) {
//            sendButton.setEnabled(false);
//            sendButton.setColorFilter(ContextCompat.getColor(getActivity(), R.color.iconCover));
//        }
    }

    private void setSoftKeyboard() {
        commentEditText.setFocusable(true);
        commentEditText.setFocusableInTouchMode(true);
        commentEditText.requestFocus();

        //为 commentEditText 设置监听器，在 DialogFragment 绘制完后立即呼出软键盘，呼出成功后即注销
        commentEditText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    if (inputMethodManager.showSoftInput(commentEditText, 0)) {
                        isSoftAutoPushUp = true;
                        commentEditText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });

        //为 root 设置监听器，监听软键盘弹出，如果软键盘收起销毁当前fragment
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //1、获取main在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                if(isSoftAutoPushUp){
                    //软键盘弹出后，收起
                    if(rect.bottom == DensityUtil.getHeight(getActivity())){
                        //软键盘收起状态
                        root.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        dismiss();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                if(btn_send.isEnabled()){
                    if(TextUtils.isEmpty(commentEditText.getText().toString().trim())){
                        ToastUtil.showShort(getContext(), "请输入评论内容");
                        return;
                    }
                    if(getArguments()!=null && !TextUtils.isEmpty(getArguments().getString("commentId"))){
                        //回复评论
                        dataCallback.submitCommentToSb(getArguments().getInt("userId"),getArguments().getString("nickname"),commentEditText.getText().toString().trim());
                    }else{
                        //回复帖子
                        dataCallback.submitCommentToPost(commentEditText.getText().toString().trim());
                    }
                    //扫尾
                    commentEditText.setText("");
                    dataCallback.setCommentText("");
                    dataCallback.setCommentToWhichUserid(0);
                    dataCallback.setCommentId("");
                    dismiss();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        dataCallback.setCommentText(commentEditText.getText().toString());
        dataCallback.setCommentToWhichUserid(getArguments().getInt("userId"));
        hideSoftInput(getContext(), commentEditText);
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        dataCallback.setCommentText(commentEditText.getText().toString());
        dataCallback.setCommentToWhichUserid(getArguments().getInt("userId"));
        hideSoftInput(getContext(), commentEditText);
        super.onCancel(dialog);
    }

    /**
     * 动态隐藏软键盘
     *
     * @param context 上下文
     * @param edit    输入框
     */
    public void hideSoftInput(Context context, EditText edit) {
        if(context != null){
            edit.clearFocus();
            InputMethodManager inputmanger = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(edit.getWindowToken(), 0);
        }
    }

}
