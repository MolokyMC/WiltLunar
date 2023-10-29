package op.wawa.wilt.gui.alt;

import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import op.wawa.wilt.utils.FontUtils;

public class GuiRenameAlt
extends GuiScreen {
    private final GuiAltManager manager;
    private GuiTextField nameField;
    private String status = "\u00a7eWaiting...";
    private GuiPasswordField pwField;

    public GuiRenameAlt(GuiAltManager manager) {
        this.manager = manager;
    }

    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1: {
                this.mc.displayGuiScreen((GuiScreen)this.manager);
                break;
            }
            case 0: {
                this.manager.selectedAlt.setMask(this.nameField.getText());
                if (!this.pwField.getText().isEmpty()) {
                    this.manager.selectedAlt.setPassword(this.pwField.getText());
                }
                this.status = "\u00a7aEdited!";
            }
        }
    }

    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        FontUtils.drawCenteredString(mc.fontRendererObj, "Edit Alt", this.width / 2, 10.0f, -1);
        FontUtils.drawCenteredString(mc.fontRendererObj, this.status, this.width / 2, 20.0f, -1);
        this.nameField.drawTextBox();
        this.pwField.drawTextBox();
        if (this.nameField.getText().isEmpty()) {
            this.mc.fontRendererObj.drawStringWithShadow("New E-Mail", (float)this.width / 2.0f - 96.0f, 66.0f, -7829368);
        }
        if (this.pwField.getText().isEmpty()) {
            this.mc.fontRendererObj.drawStringWithShadow("New Password", (float)this.width / 2.0f - 96.0f, 106.0f, -7829368);
        }
        super.drawScreen(par1, par2, par3);
    }

    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 92 + 12, "Edit"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 116 + 12, "Cancel"));
        this.nameField = new GuiTextField(890, this.mc.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        this.pwField = new GuiPasswordField(this.mc.fontRendererObj, this.width / 2 - 100, 100, 200, 20);
    }

    protected void keyTyped(char par1, int par2) {
        this.nameField.textboxKeyTyped(par1, par2);
        this.pwField.textboxKeyTyped(par1, par2);
        if (par1 == '\t' && (this.nameField.isFocused() || this.pwField.isFocused())) {
            this.nameField.setFocused(!this.nameField.isFocused());
            this.pwField.setFocused(!this.pwField.isFocused());
        }
        if (par1 == '\r') {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);
        this.nameField.mouseClicked(par1, par2, par3);
        this.pwField.mouseClicked(par1, par2, par3);
    }
}

