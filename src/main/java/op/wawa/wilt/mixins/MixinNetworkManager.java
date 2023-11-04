package op.wawa.wilt.mixins;

import io.netty.channel.*;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.raphimc.vialoader.netty.CompressionReorderEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {
    @Shadow
    private Channel channel;
    @Inject(
            method = {"setCompressionTreshold"},
            at = {@At("RETURN")}
    )
    public void reOrderPipeline(int p_setCompressionTreshold_1_, CallbackInfo ci) {
        this.channel.pipeline().fireUserEventTriggered(CompressionReorderEvent.INSTANCE);
    }

/*    @Inject(method={"channelRead0"}, at={@At(value="HEAD")}, cancellable=true)
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
    }*/
}
