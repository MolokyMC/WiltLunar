package op.wawa.wilt.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Session;
import op.wawa.wilt.Wilt;
import op.wawa.wilt.interfaces.IMixinMinecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import viamcp.ViaMCP;
import viamcp.utils.AttackOrder;

@Mixin(Minecraft.class)
public class MixinMinecraft implements IMixinMinecraft {
    @Mutable
    @Final
    @Shadow
    private Session session;
    @Shadow
    public PlayerControllerMP playerController;
    @Shadow
    public EntityPlayerSP thePlayer;
    @Shadow
    public MovingObjectPosition objectMouseOver;

/*    @Redirect(method={"clickMouse"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V"))
    private void fixAttackOrder_VanillaSwing(EntityPlayerSP instance) {
        AttackOrder.sendConditionalSwing(this.objectMouseOver);
    }

    @Redirect(method={"clickMouse"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/multiplayer/PlayerControllerMP;attackEntity(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/entity/Entity;)V"))
    private void fixAttackOrder_VanillaAttack(PlayerControllerMP instance, EntityPlayer a, Entity b) {
        AttackOrder.sendFixedAttack(this.thePlayer, this.objectMouseOver.entityHit);
    }*/

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
