package com.ftd.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

/**
 * @创建者 FTD
 * @创建时间 on 2020/1/20
 * @描述 静态广播接受者
 */
public class StaticReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.d("静态广播", "onReceive enter");
        String getActionStr = intent.getAction();
        if(TextUtils.equals(getActionStr, IntentAction.FTD_ACTION)){
            Log.d("静态广播", "收到静态注册测试广播");
        }
    }
}
