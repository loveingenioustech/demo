package demo.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.jetbrains.annotations.NotNull;

/**
 * 类名称：EchoServerHandler
 * 类描述： The annotation @Sharable marks this class as one whose instances can be
 * shared among channels.
 * 创建时间：2016年1月27日 上午11:17:18
 * 修改时间：2016年1月27日 上午11:17:18
 * 修改备注：
 * 
 * @version
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter
{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, @NotNull Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }

}
