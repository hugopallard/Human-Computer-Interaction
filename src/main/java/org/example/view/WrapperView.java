package org.example.view;

import com.googlecode.lanterna.screen.Screen;
import org.example.component.MyMenu;

import javax.swing.*;

public abstract class WrapperView {
    private JPanel panel;
    private Screen screen;

    // Constructor
    public WrapperView(JPanel panel) {
        this.panel = panel;
    }

    public WrapperView(Screen screen){
        this.screen = screen;
    }

    public abstract void updateView();

    // Getter
    public JPanel getPanel() {
        return panel;
    }

    public Screen getScreen() {
        return screen;
    }

}
