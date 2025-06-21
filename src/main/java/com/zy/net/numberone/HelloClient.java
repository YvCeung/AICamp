package com.zy.net.numberone;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @Description
 * @Author zy
 * @Date 2025/6/21 14:52
 **/
public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        new Bootstrap()
                // 1
                .group(new NioEventLoopGroup())
                // 2
                .channel(NioSocketChannel.class)
                // 3
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    // 8 也是在连接建立后才会执行
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 4
                .connect("127.0.0.1",8080)
                // 5
                .sync()
                // 6
                .channel()
                // 7
                .writeAndFlush(new Date() + ": hello world");
    }
}
