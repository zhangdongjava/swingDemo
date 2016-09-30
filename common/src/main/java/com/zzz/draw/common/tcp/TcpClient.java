package com.zzz.draw.common.tcp;

import com.zzz.draw.common.bean.Message;
import com.zzz.draw.common.code.MessageDecoder;
import com.zzz.draw.common.code.MessageEncoder;
import com.zzz.draw.common.util.ThreadUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TcpClient {
    public  String ip = "127.0.0.1";
    public  int port = 6789;

    public Bootstrap bootstrap;
    public Channel channel;

    private volatile BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    private static TcpClient INSTANCE;
    /**
     * 连接锁
     */

    public TcpClient(int port ,String ip,SimpleChannelInboundHandler<Message> handler) {
        INSTANCE = this;
        this.ip = ip;
        this.port = port;
        sendMessage();
        bootstrap = getBootstrap(handler);
        channel = getChannel(ip, port);
    }

    /**
     * 初始化Bootstrap
     *
     * @return Bootstrap
     */
    public final Bootstrap getBootstrap(SimpleChannelInboundHandler<Message> handler) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("decoder", new MessageDecoder());
                pipeline.addLast("encoder", new MessageEncoder());
                pipeline.addLast("handler", handler);
            }
        });
        b.option(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    public final Channel getChannel(String host, int port) {
        try {
            channel = bootstrap.connect(host, port).sync().channel();
            while (channel == null) {
                channel = bootstrap.connect(host, port).sync().channel();
            }
        } catch (Exception e) {
            return null;
        }
        return channel;
    }


    public void sendMsg(Message msg) {
        try {
            messageQueue.put(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启消息发送线程
     */
    private void sendMessage() {
        new ThreadUtil().submit(() -> {
            try {
                while (!Thread.interrupted()) {
                    Message message = messageQueue.take();
                    while (channel == null) {
                        reConnect();
                    }
                    sendMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void sendMessage(Object message) throws InterruptedException {
        channel.writeAndFlush(message).await();
    }

    public  void reConnect() {
        try {
            Channel res = INSTANCE.getChannel(ip, port);
            while (res == null) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("重新连接。。。!");
                res = INSTANCE.getChannel(ip, port);
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
            System.out.println((t1 - t0) / 1000000.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}