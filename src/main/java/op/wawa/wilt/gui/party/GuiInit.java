package op.wawa.wilt.gui.party;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import op.wawa.wilt.gui.ClickableButton;
import op.wawa.wilt.module.party.Sender;

import java.awt.*;

public class GuiInit extends GuiScreen {
    private ClickableButton create;
    private ClickableButton join;
    private final VexViewButton createButton;
    private final VexViewButton joinButton;

    public GuiInit(VexViewButton createButton, VexViewButton joinButton) {
        this.createButton = createButton;
        this.joinButton = joinButton;
    }

    @Override
    public void initGui() {
        create = new ClickableButton(width / 2, height / 2 - 20, 100, 20, createButton.getName()) {
            @Override
            public void clicked() {
                Sender.clickButton(createButton.getId());
            }
        };
        join = new ClickableButton(width / 2, height / 2 + 20, 100, 20, joinButton.getName()) {
            @Override
            public void clicked() {
                Sender.clickButton(joinButton.getId());
            }
        };
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("花雨庭组队系统", width / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth("花雨庭组队系统"), height / 2 - 72, new Color(216, 216, 216).getRGB());
        create.drawScreen();
        join.drawScreen();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        create.mouseClicked(mouseX, mouseY, mouseButton);
        join.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
