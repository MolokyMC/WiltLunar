package op.wawa.wilt.mixins;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import op.wawa.wilt.utils.FixedSoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Skid or Made By WaWa
 *
 * @author WaWa
 * @date 2023/7/31 16:18
 */
@Mixin(value={ItemBlock.class})
public abstract class MixinItemBlock {

    /**
     * @author a
     * @reason a
     */
    @Overwrite
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        return FixedSoundEngine.onItemUse((ItemBlock) (Object) this, stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
    }
}
