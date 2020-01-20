package com.ftd.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @创建者 FTD
 * @创建时间 on 2020/1/20
 * @描述 注释需谨慎，且行且珍惜
 */
public class SecondActivity extends AppCompatActivity {
    private String mClassName = SecondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        String intentValue = this.getIntent().getStringExtra("pageValue");
        int intentIntValue = this.getIntent().getIntExtra("pageIntValue",-1);

        Toast.makeText(this, intentValue, Toast.LENGTH_LONG).show();
        Log.d(mClassName, "intentValue:"+intentValue);
        Log.d(mClassName, "intentIntValue:"+intentIntValue);

//        Bundle bundle = this.getIntent().getExtras();
//        Log.d(mClassName, "bundle id:"+bundle.getInt("id"));
//        Log.d(mClassName, "bundle name:"+bundle.getString("name"));
    }

}
