package com.social.huakai.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.social.basecommon.activity.BaseActivity;
import com.social.huakai.R;
import com.social.huakai.databinding.ActivityLoginBinding;
import com.social.huakai.ui.main.MainActivity;

/**
 * @author Administrator
 * @date 2019/6/26 0026
 * @description:
 */
public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        playVideo();
        setListener();
    }

    private void setListener() {
        binding.tomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, MainActivity.class));
                finish();
            }
        });
    }

    private void playVideo() {
        binding.videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.loginmovie));
        binding.videoview.start();
        binding.videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
    }
}
