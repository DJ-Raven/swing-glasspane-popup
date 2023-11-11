package raven.drawer.component.menu;

public class MenuValidation {

    protected boolean keepMenuValidationIndex;
    protected boolean removeLabelWhenEmptyMenu;

    public MenuValidation() {
        this(true, true);
    }

    public MenuValidation(boolean keepMenuValidationIndex, boolean removeLabelWhenEmptyMenu) {
        this.keepMenuValidationIndex = keepMenuValidationIndex;
        this.removeLabelWhenEmptyMenu = removeLabelWhenEmptyMenu;
    }

    public boolean menuValidation(int index, int subIndex) {
        return true;
    }

    public boolean labelValidation(int index) {
        return true;
    }
}
