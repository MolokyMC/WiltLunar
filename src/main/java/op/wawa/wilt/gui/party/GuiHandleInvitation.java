package op.wawa.wilt.gui.party;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import op.wawa.wilt.gui.ClickableButton;
import op.wawa.wilt.module.party.Sender;

import java.awt.*;

/**
 * @projectName: MIN
 * @author: vlouboos
 * @date: 2023-07-20 20:55:37
 */
public class GuiHandleInvitation extends GuiScreen {
    private ClickableButton accept;
    private ClickableButton deny;
    private final VexViewButton acceptButton;
    private final VexViewButton denyButton;

    public GuiHandleInvitation(VexViewButton acceptButton, VexViewButton denyButton) {
        this.acceptButton = acceptButton;
        this.denyButton = denyButton;
    }

    @Override
    public void initGui() {
        accept = new ClickableButton(width / 2, height / 2 - 20, 100, 20, acceptButton.getName()) {
            @Override
            public void clicked() {
                Sender.clickButton(acceptButton.getId());
            }
        };
        deny = new ClickableButton(width / 2, height / 2 + 20, 100, 20, denyButton.getName()) {
            @Override
            public void clicked() {
                Sender.clickButton(denyButton.getId());
            }
        };
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.enableBlend();
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("花雨庭组队系统", width / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth("花雨庭组队系统"), height / 2 - 72, new Color(216, 216, 216).getRGB());
        accept.drawScreen();
        deny.drawScreen();
        GlStateManager.disableBlend();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        accept.mouseClicked(mouseX, mouseY, mouseButton);
        deny.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
