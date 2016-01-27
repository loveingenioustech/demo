package demo.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>
{


    /* (non-Javadoc)  
     * <p>Title: channelRead0</p>  
     * <p>Description: called after data is received from the server. </p>  
     * @param ctx
     * @param in
     * @throws Exception  
     * @see io.netty.channel.SimpleChannelInboundHandler#channelRead0(io.netty.channel.ChannelHandlerContext, java.lang.Object)  
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception
    {
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    /* (non-Javadoc)  
     * <p>Title: channelActive</p>  
     * <p>Description: called after the connection to the server is established. </p>  
     * @param ctx
     * @throws Exception  
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)  
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }

}
