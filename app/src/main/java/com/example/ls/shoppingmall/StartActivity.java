package com.example.ls.shoppingmall;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.ls.shoppingmall.app.MainActivity;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class); // 从启动动画ui跳转到主ui
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                StartActivity.this.finish(); // 结束启动动画界面

            }
        }, 2000); // 启动动画持续3秒钟
    }
}
