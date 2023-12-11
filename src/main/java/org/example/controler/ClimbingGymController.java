package org.example.controler;

import com.googlecode.lanterna.screen.Screen;
import org.example.model.ClimbingGym;
import org.example.view.WrapperView;

import java.io.IOException;

public class ClimbingGymController {
    private final ClimbingGym model;
    private WrapperView view;

    public ClimbingGymController(ClimbingGym model, WrapperView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView() {
        this.view.updateView();
    }

    public void setView(WrapperView view) {
        this.view = view;
    }
}
