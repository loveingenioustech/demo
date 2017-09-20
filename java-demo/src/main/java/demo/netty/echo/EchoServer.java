package demo.netty.echo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.jetbrains.annotations.NotNull;

public class EchoServer
{
    private final int port = 9000;

    public static void main(String[] args) throws Exception
    {
        new EchoServer().start();
    }

    public void start() throws Exception
    {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try
        {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        public void initChannel(@NotNull SocketChannel ch) throws Exception
                        {
                            // 绑定 EchoServerHandler
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            // sync waits for the server to close
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        }
        finally
        {
            // 释放所有资源
            group.shutdownGracefully().sync();
        }
    }

}
