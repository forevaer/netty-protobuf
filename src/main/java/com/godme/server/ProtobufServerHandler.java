package com.godme.server;

import com.godme.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtobufServerHandler extends SimpleChannelInboundHandler<Message.Animal> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.Animal msg) throws Exception {
        System.out.println("添加了一只"+msg.getAnimalType()+"\n" + msg.toString() + "======================");
    }
}
