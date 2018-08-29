package com.xiaojie.learn.nettydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xiaojie.learn.nettydemo.netty.EchoServer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                new EchoServer(8888).start();
            }
        }).start();
    }
}
