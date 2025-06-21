package com.zy.net.numberone;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Description
 * @Author zy
 * @Date 2025/6/21 14:49
 **/
public class HelloServer {
    public static void main(String[] args) {
        new ServerBootstrap()
                // 1
                .group(new NioEventLoopGroup())
                // 2
                .channel(NioServerSocketChannel.class)
                // 3
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    //初始化器只有在连接建立之后才会运行
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        // 5
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        // 6
                        nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                // 4
                .bind(8080);
    }
}
