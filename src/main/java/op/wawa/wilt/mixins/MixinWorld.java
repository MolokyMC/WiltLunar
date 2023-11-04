package op.wawa.wilt.mixins;

import net.minecraft.util.BlockPos;
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
@Mixin(value={World.class})
public abstract class MixinWorld {

    /**
     * @author a
     * @reason a
     */
    @Overwrite
    public boolean destroyBlock(BlockPos pos, boolean dropBlock) {
        return FixedSoundEngine.destroyBlock((World) (Object) this, pos, dropBlock);
    }
}
