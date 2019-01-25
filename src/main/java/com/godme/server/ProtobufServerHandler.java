package com.godme.server;

import com.godme.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtobufServerHandler extends SimpleChannelInboundHandler<Message.Cat> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.Cat msg) throws Exception {
        System.out.println("添加了一只猫\n" + msg.toString() + "======================");
    }
}
