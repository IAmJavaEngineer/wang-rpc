package com.ydlclass.channelhandler;

import com.ydlclass.channelhandler.handler.MySimpleChannelInboundHandler;
import com.ydlclass.channelhandler.handler.YrpcRequestEncoder;
import com.ydlclass.channelhandler.handler.YrpcResponseDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author it楠老师
 * @createTime 2023-07-02
 */
public class ConsumerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
            // netty自带的日志处理器
            .addLast(new LoggingHandler(LogLevel.DEBUG))
            // 消息编码器
            .addLast(new YrpcRequestEncoder())
            // 入栈的解码器
            .addLast(new YrpcResponseDecoder())
            // 处理结果
            .addLast(new MySimpleChannelInboundHandler());
        
    }
}
