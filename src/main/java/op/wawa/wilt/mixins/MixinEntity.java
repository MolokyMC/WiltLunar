package op.wawa.wilt.mixins;

import op.wawa.wilt.viaforge.ViaForge;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Inject(method = "getCollisionBorderSize", at = @At("HEAD"), cancellable = true)
    private void getCollisionBorderSize(final CallbackInfoReturnable<Float> callbackInfoReturnable) {
        callbackInfoReturnable.setReturnValue(ViaForge.targetVersion.getVersion() <= ViaForge.NATIVE_VERSION.getVersion() ? 0.1f : 0.0f);
    }
}