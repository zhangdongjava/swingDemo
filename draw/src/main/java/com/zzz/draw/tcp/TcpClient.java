package com.zzz.draw.tcp;

import com.zzz.draw.code.MessageDecoder;
import com.zzz.draw.code.MessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TcpClient {
    public static String HOST = "127.0.0.1";
    public static int PORT = 6789;

    public  Bootstrap bootstrap;
    public  Channel channel ;

    public TcpClient(){
        bootstrap = getBootstrap();
        channel = getChannel(HOST,PORT);
    }
    /**
     * 初始化Bootstrap
     * @return
     */
    public  final Bootstrap getBootstrap(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("decoder", new MessageDecoder());
                pipeline.addLast("encoder", new MessageEncoder());
                pipeline.addLast("handler", new TcpClientHandler(TcpClient.this));
            }
        });
        b.option(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    public  final Channel getChannel(String host,int port){
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            return null;
        }
        return channel;
    }

    public  void sendMsg(Object msg) {
        if(channel!=null){
            try {
                channel.writeAndFlush(msg).sync();
            } catch (InterruptedException e) {
                System.out.println("消息发送失败!"+e.toString());
            }
        }else{
            System.out.println("消息发送失败,连接尚未建立!");
        }
    }

    public static void main(String[] args) throws Exception {

        try {
            long t0 = System.nanoTime();

            long t1 = System.nanoTime();
            System.out.println((t1-t0)/1000000.0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}