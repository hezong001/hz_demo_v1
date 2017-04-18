package com.hz.singletoast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat mFormat = new SimpleDateFormat(FORMAT_ONE);
    private MyReceiver mMyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        /*
            动态注册广播
         */
        registerReceiver(mMyReceiver, intentFilter);
    }

    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                ToastUtils.showToast(context, "屏幕关闭了");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                ToastUtils.showToast(context, "屏幕打开了");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
            反注册广播
         */
        if (mMyReceiver != null) {
            unregisterReceiver(mMyReceiver);
        }
    }

    /**
     * while Button onclick handle this
     *
     * @param view
     */
    public void SingleToast(View view) {
        String format1 = mFormat.format(new Date().getTime());
        ToastUtils.showToast(this, "" + format1);
    }
}
