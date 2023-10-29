package op.wawa.wilt.gui.germ;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class GermPage extends GuiScreen {
    private final String uuid;
    private final LinkedHashSet<GermButton> buttons = new LinkedHashSet<>();

    public GermPage(String uuid, ArrayList<GermButton> germModButtons) {
        this.uuid = uuid;
        if (germModButtons.isEmpty()) {
            mc.displayGuiScreen(null);
            return;
        }
        buttons.addAll(germModButtons);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.enableBlend();
        mc.fontRendererObj.drawString("Germ Menu", width / 2, height / 2 - 60, new Color(216, 216, 216).getRGB());
        int y = height / 2 - 20;
        for (GermButton button : buttons) {
            button.drawButton(uuid, width / 2, y, mouseX, mouseY);
            y += 40;
        }
        GlStateManager.disableBlend();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (GermButton button : buttons) {
            button.mouseClicked(uuid);
        }
    }
}
