package op.wawa.wilt.mixins;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.raphimc.vialoader.netty.CompressionReorderEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {
    @Shadow @Final private NetworkManager netManager;

    @Inject(
            method = {"handleCustomPayload"},
            at = {@At(value = "INVOKE", target = "Lnet/minecraft/network/PacketThreadUtil;checkThreadAndEnqueue(Lnet/minecraft/network/Packet;Lnet/minecraft/network/INetHandler;Lnet/minecraft/util/IThreadListener;)V", ordinal = 0, shift = At.Shift.AFTER)},
            cancellable = true)
    public void handleCustomPayload(S3FPacketCustomPayload packetIn, CallbackInfo ci) {
        if (packetIn.getChannelName().equalsIgnoreCase("REGISTER")) {
            final PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
            buffer.writeBytes("FML|HS FML FML|MP FML FORGE germplugin-netease hyt0 armourers".getBytes());
            this.netManager.sendPacket(new C17PacketCustomPayload("REGISTER", buffer));
            ci.cancel();
        }
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
