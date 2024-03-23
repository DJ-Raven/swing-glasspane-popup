# Swing Glasspane Popup
Java Swing UI popup dialog custom using glasspane with flatlaf include the animation style, and drawer menu

<img src="https://github.com/DJ-Raven/swing-glasspane-popup/blob/main/screenshot/sample%20dark.png" alt="sample dark" width="400"/>&nbsp;
<img src="https://github.com/DJ-Raven/swing-glasspane-popup/blob/main/screenshot/sample%20light.png" alt="sample light" width="400"/>

## Installation
This project library do not available in maven central. so you can install with the jar library
- Copy jar library file to the root project. exp : `library/swing-glasspane-popup-1.5.0.jar`
- Add this code to `pom.xml`
``` xml
<dependency>
    <groupId>raven.popup</groupId>
    <artifactId>swing-glasspane-popup</artifactId>
    <version>1.5.0</version>
    <scope>system</scope>
    <systemPath>${basedir}/library/swing-glasspane-popup-1.5.0.jar</systemPath>
</dependency>
```
- Other library are use with this library
``` xml
<dependency>
  <groupId>com.formdev</groupId>
  <artifactId>flatlaf</artifactId>
  <version>3.4</version>
</dependency>

<dependency>
  <groupId>com.formdev</groupId>
  <artifactId>flatlaf-extras</artifactId>
  <version>3.4</version>
</dependency>

<dependency>
    <groupId>com.miglayout</groupId>
    <artifactId>miglayout-swing</artifactId>
    <version>11.3</version>
</dependency>
```

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

#### Push and Pop with `name`
``` java
GlassPanePopup.push(childComponent, "popupname");

GlassPanePopup.pop("popupname");
```

#### SimplePopupBorder

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
