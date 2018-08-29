package com.xiaojie.learn.nettyhttpserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaojie.learn.nettyhttpserver.server.HttpServer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                new HttpServer(8080).start();
            }
        }).start();
    }
}
