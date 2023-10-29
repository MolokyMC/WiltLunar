package op.wawa.wilt.mixins;

import net.minecraft.client.renderer.WorldRenderer;
import op.wawa.wilt.interfaces.IMixinWorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={WorldRenderer.class})
public class MixinWorldRenderer
implements IMixinWorldRenderer {
    @Shadow
    @Mutable
    private boolean isDrawing;
    @Shadow
    @Mutable
    private int drawMode;

    @Shadow
    public WorldRenderer tex(double u, double v) {
        return null;
    }

    @Shadow
    public WorldRenderer pos(double x, double y, double z) {
        return null;
    }

    @Shadow
    public void reset() {
    }

    @Override
    public void addVertexWithUV(double p_178985_1_, double p_178985_3_, double p_178985_5_, double p_178985_7_, double p_178985_9_) {
        this.tex(p_178985_7_, p_178985_9_);
        this.pos(p_178985_1_, p_178985_3_, p_178985_5_);
    }

    @Override
    public void startDrawingQuads() {
        this.startDrawing(7);
    }

    @Override
    public void startDrawing(int mode) {
        if (this.isDrawing) {
            throw new IllegalStateException("Already building!");
        }
        this.isDrawing = true;
        this.reset();
        this.drawMode = mode;
    }
}

