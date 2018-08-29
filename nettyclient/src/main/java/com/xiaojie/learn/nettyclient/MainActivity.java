package com.xiaojie.learn.nettyclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaojie.learn.nettyclient.netty.EchoClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                new EchoClient("192.168.31.125", 8888).start();
            }
        }).start();
    }
}
