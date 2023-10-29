package op.wawa.wilt.gui.alt;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import op.wawa.wilt.utils.FontUtils;
import org.lwjgl.input.Keyboard;

public class GuiAltLogin
extends GuiScreen {
    private GuiPasswordField password;
    private final GuiScreen previousScreen;
    private AltLoginThread thread;
    private GuiTextField username;
    private GuiTextField combined;

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
                if (this.combined.getText().isEmpty()) {
                    this.thread = new AltLoginThread(this.username.getText(), this.password.getText());
                } else if (!this.combined.getText().isEmpty() && this.combined.getText().contains(":")) {
                    String u = this.combined.getText().split(":")[0];
                    String p = this.combined.getText().split(":")[1];
                    this.thread = new AltLoginThread(u.replaceAll(" ", ""), p.replaceAll(" ", ""));
                } else {
                    this.thread = new AltLoginThread(this.username.getText(), this.password.getText());
                }
                this.thread.start();
            }
        }
    }

    public void drawScreen(int x, int y, float z) {
        this.drawDefaultBackground();
        this.username.drawTextBox();
        this.password.drawTextBox();
        this.combined.drawTextBox();
        FontUtils.drawCenteredString(mc.fontRendererObj, "Alt Login", this.width / 2, 20.0f, -1);
        FontUtils.drawCenteredString(mc.fontRendererObj, this.thread == null ? "\u00a7eWaiting..." : this.thread.getStatus(), this.width / 2, 29.0f, -1);
        if (this.username.getText().isEmpty()) {
            this.mc.fontRendererObj.drawStringWithShadow("Username / E-Mail", (float)(this.width / 2 - 96), 66.0f, -7829368);
        }
        if (this.password.getText().isEmpty()) {
            this.mc.fontRendererObj.drawStringWithShadow("Password", (float)(this.width / 2 - 96), 106.0f, -7829368);
        }
        if (this.combined.getText().isEmpty()) {
            this.mc.fontRendererObj.drawStringWithShadow("Email:Password", (float)(this.width / 2 - 96), 146.0f, -7829368);
        }
        super.drawScreen(x, y, z);
    }

    public void initGui() {
        int var3 = this.height / 4 + 24;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, var3 + 72 + 12, "Login"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, var3 + 72 + 12 + 24, "Back"));
        this.username = new GuiTextField(1, this.mc.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        this.password = new GuiPasswordField(this.mc.fontRendererObj, this.width / 2 - 100, 100, 200, 20);
        this.combined = new GuiTextField(var3, this.mc.fontRendererObj, this.width / 2 - 100, 140, 200, 20);
        this.username.setFocused(true);
        this.username.setMaxStringLength(200);
        this.password.func_146203_f(200);
        this.combined.setMaxStringLength(200);
        Keyboard.enableRepeatEvents((boolean)true);
    }

    protected void keyTyped(char character, int key) {
        super.keyTyped(character, key);
        if (character == '\t' && (this.username.isFocused() || this.combined.isFocused() || this.password.isFocused())) {
            this.username.setFocused(!this.username.isFocused());
            this.password.setFocused(!this.password.isFocused());
            this.combined.setFocused(!this.combined.isFocused());
        }
        if (character == '\r') {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
        this.username.textboxKeyTyped(character, key);
        this.password.textboxKeyTyped(character, key);
        this.combined.textboxKeyTyped(character, key);
    }

    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        this.username.mouseClicked(x, y, button);
        this.password.mouseClicked(x, y, button);
        this.combined.mouseClicked(x, y, button);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    public void updateScreen() {
        this.username.updateCursorCounter();
        this.password.updateCursorCounter();
        this.combined.updateCursorCounter();
    }
}

