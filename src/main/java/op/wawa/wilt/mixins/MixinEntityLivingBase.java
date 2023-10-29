package op.wawa.wilt.mixins;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import viamcp.ViaMCP;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends MixinEntity {

    @ModifyConstant(method = "onLivingUpdate", constant = @Constant(doubleValue = 0.005D))
    private double refactor1_9MovementThreshold(double constant) {
        if (ViaMCP.getInstance().getVersion() <= 47)
            return 0.005D;
        return 0.003D;
    }
}
