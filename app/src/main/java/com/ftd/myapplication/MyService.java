package com.ftd.myapplication;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @创建者 FTD
 * @创建时间 on 2020/1/20
 * @描述 测试服务
 */

public class MyService extends Service {

    private static final String TAG = "MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate called.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand called.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind called.");
        return null;
    }

//    @Override
//    public IBinder onBind(Intent intent) {
//        Log.i(TAG, "onBind called.");
//        return new Binder() {};
//    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind called.");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy called.");
    }
}
