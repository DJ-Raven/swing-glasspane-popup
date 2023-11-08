# Swing Glasspane Popup
Java Swing UI popup dialog custom using glasspane

## libraries
- [FlatLaf](https://github.com/JFormDesigner/FlatLaf) - FlatLaf library for the modern UI design theme
- [MigLayout](https://github.com/mikaelgrev/miglayout) - MigLayout library for flexible layout management

## Sample Code

``` java
//  Install with jframe

GlassPanePopup.install(jframe);

//  Show glasspane popup

String action[] = {"Cancel", "OK"};
GlassPanePopup.showPopup(new SimplePopupBorder(component, "Sample Message", action, new PopupCallbackAction() {
    @Override
    public void action(PopupController controller, int action) {
        if (action == 0) {
            //  action cancel
        } else if (action == 1) {
            //  action ok
        }
    }
}));
```
### SimplePopupBorder

``` java
public SimplePopupBorder(Component component,
                         String title,
                         SimplePopupBorderOption option,
                         String[] action,
                         PopupCallbackAction callbackAction);
```
``` java
new SimplePopupBorderOption()
                    .setRoundBorder(30)
                    .setWidth(500)
                    .useScroll();
```


## Screenshot
<img src="https://github.com/DJ-Raven/swing-glasspane-popup/blob/main/screenshot/sample%20dark.png" alt="sample dark" width="400"/>&nbsp;
<img src="https://github.com/DJ-Raven/swing-glasspane-popup/blob/main/screenshot/sample%20light.png" alt="sample light" width="400"/>
