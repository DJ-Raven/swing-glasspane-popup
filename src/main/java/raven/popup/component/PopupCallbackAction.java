package raven.popup.component;

public interface PopupCallbackAction {

    public static final int CLOSE = -1;

    public void action(PopupController controller, int action);
}
