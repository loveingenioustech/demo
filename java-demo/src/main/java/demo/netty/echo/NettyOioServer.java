package demo.netty.echo;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import org.jetbrains.annotations.NotNull;

/**
 * 类名称：NettyOioServer
 * 类描述：Netty 阻塞式IO示例
 * 创建时间：2016年2月1日 上午9:24:07
 * 修改时间：2016年2月1日 上午9:24:07
 * 修改备注：
 * 
 * @version
 */
public class NettyOioServer
{
    public void server(int port) throws Exception
    {
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));

        // Use OioEventLoopGroup to allow blocking mode (Old-IO)
        EventLoopGroup group = new OioEventLoopGroup();
        try
        {
            ServerBootstrap b = new ServerBootstrap(); // 1
            b.group(group) // 2
                    .channel(OioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    { // 3
                        @Override
                        public void initChannel(@NotNull SocketChannel ch) throws Exception
                        {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter()
                            { // 4
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception
                                {
                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE); // 5
                                }
                            });
                        }
                    });
            ChannelFuture f = b.bind().sync(); // 6
            f.channel().closeFuture().sync();
        }
        finally
        {
            group.shutdownGracefully().sync(); // 7
        }
    }
}