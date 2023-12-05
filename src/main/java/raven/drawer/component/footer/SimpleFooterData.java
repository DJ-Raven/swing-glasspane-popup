package raven.drawer.component.footer;

public class SimpleFooterData {

    protected String title;
    protected String description;
    protected SimpleFooterStyle simpleFooterStyle;

    public SimpleFooterData setTitle(String title) {
        this.title = title;
        return this;
    }

    public SimpleFooterData setDescription(String description) {
        this.description = description;
        return this;
    }

    public SimpleFooterData setFooterStyle(SimpleFooterStyle simpleFooterStyle) {
        this.simpleFooterStyle = simpleFooterStyle;
        return this;
    }
}
