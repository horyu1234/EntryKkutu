package kr.rvs.kkutu.network.netty;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import kr.rvs.kkutu.util.Static;

import java.util.logging.Level;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class WebSocketMonitor extends ChannelDuplexHandler {
    private final String name;

    public WebSocketMonitor(String name) {
        this.name = name;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        process(name + " send: ", msg);
        super.write(ctx, msg, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        process(name + " receive: ", msg);
        super.channelRead(ctx, msg);
    }

    private void process(String prefix, Object msg) {
        if (msg instanceof TextWebSocketFrame) {
            log(prefix, (TextWebSocketFrame) msg);
        }
    }

    private void log(String prefix, TextWebSocketFrame frame) {
        frame.retain();
        Static.log(Level.INFO, prefix + frame.text());
        frame.release();
    }
}
