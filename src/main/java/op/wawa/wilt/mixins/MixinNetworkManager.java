package op.wawa.wilt.mixins;

import io.netty.channel.*;
import net.minecraft.network.NettyCompressionDecoder;
import net.minecraft.network.NettyCompressionEncoder;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import viamcp.utils.NettyUtil;

import java.net.InetAddress;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {
    @Shadow
    private Channel channel;

    /**
     * @author WaWa
     * @reason viaMcp
     */
    @Overwrite
    public void setCompressionTreshold(int var1) {
        if (var1 >= 0) {
            if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
                ((NettyCompressionDecoder)this.channel.pipeline().get("decompress")).setCompressionTreshold(var1);
            } else {
                //this.channel.pipeline().addBefore("decoder", "decompress", new NettyCompressionDecoder(var1));
                NettyUtil.decodeEncodePlacement(channel.pipeline(), "decoder", "decompress", new NettyCompressionDecoder(var1));
            }

            if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder) {
                ((NettyCompressionEncoder)this.channel.pipeline().get("decompress")).setCompressionTreshold(var1);
            } else {
                //this.channel.pipeline().addBefore("encoder", "compress", new NettyCompressionEncoder(var1));
                NettyUtil.decodeEncodePlacement(channel.pipeline(), "encoder", "compress", new NettyCompressionEncoder(var1));
            }
        } else {
            if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
                this.channel.pipeline().remove("decompress");
            }

            if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder) {
                this.channel.pipeline().remove("compress");
            }
        }

    }

    @Inject(method={"channelRead0"}, at={@At(value="HEAD")}, cancellable=true)
    private void read(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callback) {
        if (packet instanceof C0APacketAnimation) {
            callback.cancel();
        }
    }

    @Inject(method={"sendPacket(Lnet/minecraft/network/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void send(Packet<?> packet, CallbackInfo callback) {
        if (packet instanceof C0APacketAnimation) {
            callback.cancel();
        }
    }
}
