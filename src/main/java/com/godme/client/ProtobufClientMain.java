
package com.godme.client;

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
            String [] attr = null;
            while(true){
                Message.Animal.Builder animalBuilder = Message.Animal.newBuilder();
                show(" choose animal :  dog ? pig ? cat ?");
                String type = reader.readLine();
                switch (type){
                    case "pig":
                        show("add pig : name age gender");
                        attr = reader.readLine().split(" ");
                        animalBuilder.setAnimalType(Message.Animal.Animal_type.PIG)
                                .setPig(
                                        Message.Pig.newBuilder()
                                                .setName(attr[0])
                                                .setAge(Integer.valueOf(attr[1]))
                                                .setGender(attr[2])
                                );
                        break;
                    case "dog":
                        show("add dog : name hello");
                        attr  = reader.readLine().split(" ");
                        animalBuilder.setAnimalType(Message.Animal.Animal_type.DOG).setDog(
                                Message.Dog.newBuilder()
                                .setName(attr[0])
                                .setHello(attr[1])
                        );
                        break;
                    case "cat":
                        show("add cat : name color gender");
                        attr = reader.readLine().split(" ");
                        animalBuilder.setAnimalType(Message.Animal.Animal_type.CAT)
                                .setCat(
                                        Message.Cat.newBuilder()
                                        .setName(attr[0])
                                        .setColor(attr[1])
                                        .setGender(attr[3])
                                );
                        break;
                        default:break;
                }
                channel.writeAndFlush(animalBuilder.build());
            }
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
    public static void show(String message){
        System.out.println(message);
    }
}
