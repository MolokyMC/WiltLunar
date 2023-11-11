package op.wawa.wilt.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import op.wawa.wilt.gui.alt.GuiAltManager;
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
@Mixin(value={GuiOptions.class})
public abstract class MixinGameSettings extends GuiScreen {
    //@Shadow
    //protected List<GuiButton> buttonList;

    @Inject(method = "initGui", at = @At(value="RETURN"))
    public void createButtons(CallbackInfo ci) {
        this.buttonList.add(new GuiButton(114514, 5, this.height - 38, 60, 20, "OfflineAlt"));
        this.buttonList.add(new GuiButton(114515, 5, this.height - 61, 60, 20, "MultiPlayer"));
    }
    @Inject(method = "actionPerformed", at = @At(value = "RETURN"))
    private void actionPerformed(GuiButton button, CallbackInfo info) {
        if (button.id == 114514) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiAltManager());
        } else if (button.id == 114515) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiMultiplayer(this));
        }
    }
}
