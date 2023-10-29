package op.wawa.wilt.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import viamcp.ViaMCP;

import java.util.List;

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
        this.buttonList.add(ViaMCP.getInstance().asyncSlider);
    }
}
