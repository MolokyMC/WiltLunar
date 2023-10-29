package op.wawa.wilt.gui.party;

public class VexViewButton extends VexViewComponent {
    private final String name;

    public VexViewButton(String name, String id) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
