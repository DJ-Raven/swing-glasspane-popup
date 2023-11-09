package raven.drawer.component.menu;

public class MenuAction {
    private boolean consume;

    private boolean selected;

    protected boolean getConsume() {
        return consume;
    }

    public void consume() {
        consume = true;
    }

    private void selected() {
        selected = true;
    }
}
