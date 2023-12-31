package op.wawa.wilt.gui.alt;

import java.util.ArrayList;
import java.util.List;

public class AltManager {
    static List<Alt> alts;
    static Alt lastAlt;

    public AltManager() {
        AltManager.init();
    }

    public static void init() {
        AltManager.setupAlts();
        AltManager.getAlts();
    }

    public Alt getLastAlt() {
        return lastAlt;
    }

    public void setLastAlt(Alt alt) {
        lastAlt = alt;
    }

    public static void setupAlts() {
        alts = new ArrayList<Alt>();
    }

    public static List<Alt> getAlts() {
        return alts;
    }
}

