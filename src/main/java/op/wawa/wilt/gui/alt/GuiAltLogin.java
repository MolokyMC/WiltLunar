package op.wawa.wilt.gui.alt;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import op.wawa.wilt.utils.FontUtils;
import org.lwjgl.input.Keyboard;

public class GuiAltLogin
extends GuiScreen {
    private final GuiScreen previousScreen;
    private AltLoginThread thread;
    private GuiTextField username;

    public GuiAltLogin(GuiScreen previousScreen) {
        this.previousScreen = previousScreen;
    }

    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1: {
                this.mc.displayGuiScreen(this.previousScreen);
                break;
            }
            case 0: {
                this.thread = new AltLoginThread(this.username.getText(), "");
                this.thread.start();
            }
        }
    }

    public void drawScreen(int x, int y, float z) {
        this.drawDefaultBackground();
        this.username.drawTextBox();
        FontUtils.drawCenteredString(mc.fontRendererObj, "Offline Alt Login", (float) this.width / 2, 20.0f, -1);
        FontUtils.drawCenteredString(mc.fontRendererObj, this.thread == null ? "§eWaiting..." : this.thread.getStatus(), this.width / 2, 29.0f, -1);
        if (this.username.getText().isEmpty()) {
            this.mc.fontRendererObj.drawStringWithShadow("Username / E-Mail", (float)(this.width / 2 - 96), 96.0f, -7829368);
        }
        super.drawScreen(x, y, z);
    }

    public void initGui() {
        int var3 = this.height / 4 + 24;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, var3 + 72 + 12, "Login"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, var3 + 72 + 12 + 24, "Back"));
        this.username = new GuiTextField(1, this.mc.fontRendererObj, this.width / 2 - 100, 90, 200, 20);
        this.username.setFocused(true);
        this.username.setMaxStringLength(200);
        Keyboard.enableRepeatEvents((boolean)true);
    }

    protected void keyTyped(char character, int key) {
        super.keyTyped(character, key);
        if (character == '\t' && this.username.isFocused()) {
            this.username.setFocused(!this.username.isFocused());
        }
        if (character == '\r') {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
        this.username.textboxKeyTyped(character, key);
    }

    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        this.username.mouseClicked(x, y, button);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    public void updateScreen() {
        this.username.updateCursorCounter();
    }
}

