package raven.popup.component;

public abstract class PopupController {

    private boolean consume;

    protected boolean getConsume() {
        return consume;
    }

    public void consume() {
        consume = true;
    }

    public abstract void closePopup();
}
