package com.xiaojie.learn.nettydemo.netty;

import android.util.Log;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
    private static final String TAG = "EchoServer";

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start(){
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            Log.e(TAG, "start: " + EchoServer.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync();
//            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "start: ", e);
        }finally {
            group.shutdownGracefully();
        }
    }
}
