package op.wawa.wilt.mixins;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import op.wawa.wilt.Wilt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Skid or Made By WaWa
 *
 * @author WaWa
 * 2023/7/31 16:18
 */
@Mixin(value={GuiMultiplayer.class})
public abstract class MixinMultiplayer extends GuiScreen {
    //@Shadow
    //protected List<GuiButton> buttonList;

    @Inject(method = "createButtons", at = @At(value="HEAD"))
    public void createButtons(CallbackInfo ci) {
        this.buttonList.add(Wilt.INSTANCE.getAsyncSlider());
    }
//    @Inject(
//            method = {"actionPerformed"},
//            at = {@At("RETURN")}
//    )
//    public void handleCustomButtonAction(GuiButton p_actionPerformed_1_, CallbackInfo ci) {
///*        if (p_actionPerformed_1_.id == 1337) {
//            this.mc.displayGuiScreen(new GuiProtocolSelector(this));
//        }*/
//
//    }
}
