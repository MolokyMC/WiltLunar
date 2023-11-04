package op.wawa.wilt.mixins;

import op.wawa.wilt.viaforge.ViaForge;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

@Mixin(C08PacketPlayerBlockPlacement.class)
public abstract class MixinC08PacketPlayerBlockPlacement {
    @Shadow private BlockPos position;

    @Shadow private int placedBlockDirection;

    @Shadow
    private ItemStack stack;

    @Shadow
    private float facingX;

    @Shadow
    private float facingY;

    @Shadow
    private float facingZ;

    // C08 Fixed, ah?
    /**
     * @author a
     * @reason a
     */
    @Overwrite()
    public void readPacketData(PacketBuffer p_readPacketData_1_)throws IOException{
        this.position = p_readPacketData_1_.readBlockPos();
        this.placedBlockDirection = p_readPacketData_1_.readUnsignedByte();
        this.stack = p_readPacketData_1_.readItemStackFromBuffer();
        float f = ViaForge.targetVersion.getVersion() <= 47 ? 16.0F : 1.0F;
        this.facingX = (float)p_readPacketData_1_.readUnsignedByte() / f;
        this.facingY = (float)p_readPacketData_1_.readUnsignedByte() / f;
        this.facingZ = (float)p_readPacketData_1_.readUnsignedByte() / f;
    }
    /**
     * @author a
     * @reason a
     */
    @Overwrite()
    public void writePacketData(PacketBuffer p_writePacketData_1_) throws IOException{
        p_writePacketData_1_.writeBlockPos(this.position);
        p_writePacketData_1_.writeByte(this.placedBlockDirection);
        p_writePacketData_1_.writeItemStackToBuffer(this.stack);
        float f = ViaForge.targetVersion.getVersion() <= ViaForge.NATIVE_VERSION.getVersion() ? 16.0F : 1.0F;
        p_writePacketData_1_.writeByte((int)(this.facingX * f));
        p_writePacketData_1_.writeByte((int)(this.facingY * f));
        p_writePacketData_1_.writeByte((int)(this.facingZ * f));
    }
}