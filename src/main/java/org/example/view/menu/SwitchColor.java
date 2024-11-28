package org.example.view.menu;
import org.example.controller.factory.MenuState;

import javax.swing.*;
import java.awt.*;


public class SwitchColor implements AppCommand{
    private JRadioButtonMenuItem radioButton;

    private boolean useDefault;

    private MenuState menuState;

    private Color defaultColor;
    @Override
    public void execute() {
        radioButton.setSelected(!useDefault);
        Color color = useDefault
                ? defaultColor
                : JColorChooser.showDialog(null, "Выбор цвета", Color.BLACK);
        menuState.setColor(color);
    }
}
