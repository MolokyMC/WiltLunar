package op.wawa.wilt.utils;

import net.minecraft.client.gui.FontRenderer;

public class FontUtils {
    public static int drawCenteredString(FontRenderer f, String s, int x, int y, int color, boolean shadow){
        return f.drawString(s, x - f.getStringWidth(s) / 2F, y, color, shadow);
    }
    public static int drawCenteredString(FontRenderer f, String s, int x, int y, int color){
        return f.drawString(s, x - f.getStringWidth(s) / 2F, y, color, false);
    }

    public static int drawCenteredString(FontRenderer f, String s, float x, float y, int color, boolean shadow){
        return f.drawString(s, x - f.getStringWidth(s) / 2F, y, color, shadow);
    }
    public static int drawCenteredString(FontRenderer f, String s, float x, float y, int color){
        return f.drawString(s, x - f.getStringWidth(s) / 2F, y, color, false);
    }
}
