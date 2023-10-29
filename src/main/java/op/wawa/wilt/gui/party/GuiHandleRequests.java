package op.wawa.wilt.gui.party;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import op.wawa.wilt.gui.ClickableButton;
import op.wawa.wilt.module.party.Sender;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @projectName: MIN
 * @author: vlouboos
 * @date: 2023-07-30 18:36:07
 */
public class GuiHandleRequests extends GuiScreen {
    private final ArrayList<Request> requests;
    private ArrayList<ClickableButton> buttons;

    public GuiHandleRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    @Override
    public void initGui() {
        buttons = new ArrayList<>();
        int i = -40;
        for (Request request : requests) {
            buttons.add(new ClickableButton(width / 2 + 45, height / 2 + i, 10, 10, "√") {
                @Override
                public void clicked() {
                    Sender.clickButton(request.getAcceptId());
                    requests.remove(request);
                    if (requests.isEmpty()) {
                        mc.displayGuiScreen(null);
                    } else {
                        mc.displayGuiScreen(new GuiHandleRequests(requests));
                    }
                }
            });
            buttons.add(new ClickableButton(width / 2 + 70, height / 2 + i, 10, 10, "×") {
                @Override
                public void clicked() {
                    Sender.clickButton(request.getDenyId());
                    requests.remove(request);
                    if (requests.isEmpty()) {
                        mc.displayGuiScreen(null);
                    } else {
                        mc.displayGuiScreen(new GuiHandleRequests(requests));
                    }
                }
            });
            i += 20;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("花雨庭组队系统", width / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth("花雨庭组队系统"), height / 2 - 72, new Color(216, 216, 216).getRGB());
        int i = -44;
        for (Request request : requests) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(request.getName(), width / 2 - 80, height / 2 + i, new Color(216, 216, 216).getRGB());
            buttons.forEach(ClickableButton::drawScreen);
            i += 20;
        }
    }

    @Override
    @SuppressWarnings("all")
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        buttons.forEach(button -> button.mouseClicked(mouseX, mouseY, mouseButton));
    }
}
