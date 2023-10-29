package op.wawa.wilt.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import op.wawa.wilt.gui.alt.GuiAltManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import viamcp.ViaMCP;

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
        this.buttonList.add(new GuiButton(114514, 5, this.height - 38, 20, 20, "Alt"));
        this.buttonList.add(new GuiButton(114515, 30, this.height - 38, 35, 20, "Multi"));
    }
    @Inject(method = "actionPerformed", at = @At(value = "RETURN"))
    private void actionPerformed(GuiButton button, CallbackInfo info) {
        if (button.id == 114514) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiAltManager());
        } else if (button.id == 114515) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiMultiplayer(this));
        }
    }
}
