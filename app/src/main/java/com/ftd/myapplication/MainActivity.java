package com.ftd.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String mClassName = MainActivity.class.getSimpleName();
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        initView();
        regReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregReceiver();
    }

    private void initView(){
//        findViewById(R.id.main_text).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
        findViewById(R.id.main_text).setOnClickListener(this);
        findViewById(R.id.bt_sendbroadcast).setOnClickListener(this);
        findViewById(R.id.bt_startservice).setOnClickListener(this);
        findViewById(R.id.bt_stopservice).setOnClickListener(this);
        findViewById(R.id.bt_bindService).setOnClickListener(this);
        findViewById(R.id.bt_stopbindService).setOnClickListener(this);
        findViewById(R.id.bt_senddybroadcast).setOnClickListener(this);
        findViewById(R.id.bt_sendlocalbroadcast).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_text:
                Intent textintent = new Intent(mContext, SecondActivity.class);
                textintent.putExtra("pageValue", "intent页面数据传输");
                textintent.putExtra("pageIntValue", 1);
                mContext.startActivity(textintent);

                break;
            case R.id.bt_sendbroadcast:
                Intent intent = new Intent();
                intent.setAction( IntentAction.FTD_ACTION );
                intent.putExtra( "key",  "value" );
                //定性广播
//                intent.setComponent(new ComponentName("com.ftd.myapplication", "com.ftd.myapplication.StaticReceiver"));
                //全局静态广播
                intent.addFlags( Intent.FLAG_INCLUDE_STOPPED_PACKAGES );
                intent.addFlags( 0x01000000 );//TODO 8.0禁止了这个做法，后续需要整体修正
                mContext.sendBroadcast( intent );
                break;
            case R.id.bt_senddybroadcast:
                Intent intentDY = new Intent();
                intentDY.setAction( IntentAction.FTD_ACTION_DY );
                mContext.sendBroadcast( intentDY );
                break;
            case R.id.bt_sendlocalbroadcast:
                Intent localIntentDY = new Intent();
                localIntentDY.setAction( IntentAction.FTD_ACTION_DY );
                LocalBroadcastManager.getInstance(mContext).sendBroadcast( localIntentDY );
                break;
            case R.id.bt_startservice:
                startService();
                break;
            case R.id.bt_stopservice:
                stopService();
                break;
            case R.id.bt_bindService:
                bindService();
                break;
            case R.id.bt_stopbindService:
                unbindServcie();
                break;
            default:
                break;
        }

    }


    private void regReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction( IntentAction.FTD_ACTION_DY );
        mContext.registerReceiver( mReceiver, intentFilter );

        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction( IntentAction.FTD_ACTION_DY );
        LocalBroadcastManager.getInstance( mContext.getApplicationContext() ).registerReceiver( mLocalReceiver, localIntentFilter );
    }

    private void unregReceiver() {
        mContext.unregisterReceiver( mReceiver );
        LocalBroadcastManager.getInstance( mContext.getApplicationContext() ).unregisterReceiver( mLocalReceiver );
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i( mClassName, "动态广播 onReceive enter:" + intent.getAction() );
            if (intent.getAction().equals( IntentAction.FTD_ACTION_DY )) {
                Log.i( mClassName, " 收到ACTION" );
            }
        }
    };

    private BroadcastReceiver mLocalReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i( mClassName, "本地动态广播 onReceive enter:" + intent.getAction() );
            if (intent.getAction().equals( IntentAction.FTD_ACTION_DY )) {
                Log.i( mClassName, " 收到ACTION" );
            }
        }
    };

    private void startService(){
        Intent intent = new Intent(this, MyService.class);
        this.startService(intent);
    }

    public void stopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //connected
            Log.i("MyService", "onServiceConnected called.");
        }

        /**
         * 	Called when a connection to the Service has been lost.
         *	This typically happens when the process hosting the service has crashed or been killed.
         *	This does not remove the ServiceConnection itself.
         *	this binding to the service will remain active,
         *	and you will receive a call to onServiceConnected when the Service is next running.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MyService", "onServiceDisconnected called.");
        }
    };

    /**
     * 绑定服务
     */
    public void bindService() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    /**
     * 解除绑定
     */
    public void unbindServcie() {
        unbindService(conn);
    }


}
