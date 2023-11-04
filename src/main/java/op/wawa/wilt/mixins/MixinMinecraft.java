package op.wawa.wilt.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.Session;
import op.wawa.wilt.Wilt;
import op.wawa.wilt.interfaces.IMixinMinecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft implements IMixinMinecraft {
    @Mutable
    @Final
    @Shadow
    private Session session;
    @Shadow
    public EntityPlayerSP thePlayer;

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 1, shift = At.Shift.AFTER))
    public void startGame(CallbackInfo ci) {
        Wilt.INSTANCE.startGame();
    }

    @Inject(method = "startGame", at = @At("RETURN"))
    public void startGameReturn(CallbackInfo ci) {
        Display.setTitle(Display.getTitle() + " | Powered by " + Wilt.MOD_NAME);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
