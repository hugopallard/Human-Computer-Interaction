package org.example.view.panel;

import org.example.Main;
import org.example.view.WrapperView;
import javax.swing.*;
import java.awt.*;

public class ClimbingGymPanel extends WrapperView{

    public final static String ID = "ClimbingPanel";

    public ClimbingGymPanel() {
        super(new JPanel(new FlowLayout(FlowLayout.CENTER)));

        JLabel label = new JLabel("Welcome to your Climbing gym manager !");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        super.getPanel().setPreferredSize(new Dimension(Main.WIDTH - 250, Main.HEIGHT));
        super.getPanel().setLayout(new BorderLayout());
        super.getPanel().add(label, BorderLayout.CENTER);
    }

    @Override
    public void updateView() {

    }
}
