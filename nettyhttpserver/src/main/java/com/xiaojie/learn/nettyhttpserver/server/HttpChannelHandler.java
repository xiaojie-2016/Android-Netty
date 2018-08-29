package com.xiaojie.learn.nettyhttpserver.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class HttpChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        ByteBuf content = msg.content();
        byte[] bts = new byte[content.readableBytes()];
        content.readBytes(bts);
        String result = null;
        if (msg.method() == HttpMethod.GET) {
            String url = msg.uri();
            result = "get method and paramters is " + url.substring(url.indexOf("?") + 1);
        } else if (msg.method() == HttpMethod.POST) {
            result = "post method and paramters is " + new String(bts);
        }
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set("content-Type", "text/html;charset=UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("<title>netty http server</title>")
                .append("</head>")
                .append("<body>")
                .append(result)
                .append("</body>")
                .append("</html>\r\n");
        ByteBuf responseBuf = Unpooled.copiedBuffer(sb, CharsetUtil.UTF_8);
        response.content().writeBytes(responseBuf);
        responseBuf.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
