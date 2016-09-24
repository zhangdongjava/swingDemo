package com.zzz.draw.tcp;

import com.zzz.draw.bean.Message;
import com.zzz.draw.bean.MessageCode;
import com.zzz.draw.code.MessageDecoder;
import com.zzz.draw.code.MessageEncoder;
import com.zzz.draw.util.ThreadUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TcpClient {
    public static String HOST = "127.0.0.1";
    public static int PORT = 6789;

    public  Bootstrap bootstrap;
    public  Channel channel ;

    private volatile BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    private static  TcpClient INSTANCE ;
    /**
     * 连接锁
     */
    private static Object linkLock = new Object();

    public TcpClient(){
        INSTANCE = this;
        sendMessage();
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
            while(channel == null){
                channel = bootstrap.connect(host, port).sync().channel();
            }
        } catch (Exception e) {
            return null;
        }
        return channel;
    }



    public  void sendTest() {
        for (int i = 0; i < 100; i++) {
            Message message = new Message();
            message.setBody("测试数据!".getBytes());
            message.setType(MessageCode.STRING);
            sendMsg(message);
        }
    }

    public  void sendMsg(Message msg) {
        try {
            messageQueue.put(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启消息发送线程
     */
    private void sendMessage(){
        ThreadUtil.submit(()->{
            try {
                while(true){
                    Message message = messageQueue.take();
                    while(channel ==null){
                            reConnect();
                    }
                    sendMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private  void sendMessage(Object message) throws InterruptedException {
        channel.writeAndFlush(message).await();
    }

    public static void reConnect() {
        try {
            Channel res = INSTANCE.getChannel(HOST, PORT);
            while (res == null) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("重新连接。。。!");
                res = INSTANCE.getChannel(HOST,PORT);
            }
            System.out.println("连接成功!。。。!");
        } catch (Exception e) {
            System.out.println(e.toString());
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