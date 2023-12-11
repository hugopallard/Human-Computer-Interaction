package org.example.controler;

import com.googlecode.lanterna.TerminalPosition;
import org.example.model.ClimbingGym;
import org.example.model.Member;
import org.example.view.WrapperView;
import org.example.view.panel.HandleMemberPanel;

import javax.swing.*;
import java.awt.*;

public class DisplayMembersController {

    private final ClimbingGym climbingGym;
    private WrapperView view;

    private final int initialOffset = 9;
    private int currentSelectedLine = initialOffset;

    public DisplayMembersController(ClimbingGym climbingGym, WrapperView view) {
        this.climbingGym = climbingGym;
        this.view = view;
    }

    public void updateView() {
        view.updateView();
    }

    public void handleMemberDisplay(Member selectedMember, CardLayout cardLayout, JPanel cardPanel) {
        HandleMemberController handleMemberController = new HandleMemberController(selectedMember, climbingGym, null, this);
        HandleMemberPanel handleMemberPanel = new HandleMemberPanel(handleMemberController, cardLayout, cardPanel);
        handleMemberController.setView(handleMemberPanel);
        cardPanel.add(handleMemberPanel.getPanel(), HandleMemberPanel.ID);
        cardLayout.show(cardPanel, HandleMemberPanel.ID);
    }

    public void moveDown() {
        if (currentSelectedLine < climbingGym.getMembers().size() - 1 + initialOffset) {
            currentSelectedLine++;
            System.out.println("Scrolling through members [DOWN]");
            // We should be able to select a member here and call a view to display it.
            view.getScreen().setCursorPosition(new TerminalPosition(0, currentSelectedLine));
        }
    }

    public void moveUp() {
        if (currentSelectedLine > initialOffset) {
            currentSelectedLine--;
            System.out.println("Scrolling through members [UP]");
            // We should be able to select a member here and call a view to display it.
            view.getScreen().setCursorPosition(new TerminalPosition(0, currentSelectedLine));
        }

    }

    public int getCurrentSelectedLine() {
        return currentSelectedLine;
    }

    public int getInitialOffset() {
        return initialOffset;
    }

    public void setView(WrapperView view) {
        this.view = view;
    }

    public ClimbingGym getClimbingGym() {
        return climbingGym;
    }

    public WrapperView getView() {
        return view;
    }
}
