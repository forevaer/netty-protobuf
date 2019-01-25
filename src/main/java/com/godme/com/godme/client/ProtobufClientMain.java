
package com.godme.com.godme.client;

import com.godme.protobuf.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProtobufClientMain {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            Channel channel =  bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ProtobufClientInitializer()).connect("localhost",8989).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                show("add cat : name color gender");
                String[] attr = reader.readLine().split(" ");
                Message.Cat cat = Message.Cat.newBuilder().setName(attr[0]).setColor(attr[1]).setGender(attr[2]).build();
                channel.writeAndFlush(cat);
            }
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
    public static void show(String message){
        System.out.println(message);
    }
}
