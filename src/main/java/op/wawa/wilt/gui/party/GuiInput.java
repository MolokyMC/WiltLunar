package op.wawa.wilt.gui.party;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import op.wawa.wilt.gui.ClickableButton;
import op.wawa.wilt.gui.InputField;
import op.wawa.wilt.module.party.Sender;

import java.awt.*;

public class GuiInput extends GuiScreen {
    private InputField inputField;
    private ClickableButton confirm;
    private final String fieldId;
    private final VexViewButton confirmButton;

    public GuiInput(String fieldId, VexViewButton confirmButton) {
        this.fieldId = fieldId;
        this.confirmButton = confirmButton;
    }

    @Override
    public void initGui() {
        inputField = new InputField(width / 2 - 50, height / 2 - 30, 100, 20);
        confirm = new ClickableButton(width / 2, height / 2 + 30, 50, 20, confirmButton.getName()) {
            @Override
            public void clicked() {
                Sender.joinParty(inputField.getText(), fieldId, confirmButton.getId());
            }
        };
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.enableBlend();
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("花雨庭组队系统", width / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth("花雨庭组队系统"), height / 2 - 72, new Color(216, 216, 216).getRGB());
        inputField.drawTextBox();
        confirm.drawScreen();
        GlStateManager.disableBlend();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        inputField.mouseClicked(mouseX, mouseY, mouseButton);
        confirm.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        inputField.keyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void updateScreen() {
        inputField.updateCursorCounter();
        super.updateScreen();
    }
}
