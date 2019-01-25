package com.godme.client;


import com.godme.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtobufClientHandler extends SimpleChannelInboundHandler<Message .Cat> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.Cat msg) throws Exception {
        System.out.println("do nothing");
    }
}
