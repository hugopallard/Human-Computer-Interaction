package org.example.view.screen;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.example.controler.DisplayMembersController;
import org.example.model.ClimbingGym;
import org.example.view.WrapperView;

import java.io.IOException;

public class DisplayMembersView extends WrapperView {
    private String viewName = " Hello DisplayMember View ";
    private final DisplayMembersController controller;

    public DisplayMembersView(Screen screen, DisplayMembersController controller) {
        super(screen);
        this.controller = controller;
    }

    @Override
    public void updateView() {
        // Clear the screen before drawing the newest view
        super.getScreen().clear();

        ClimbingGym climbingGym = controller.getClimbingGym();
        TextGraphics tg = super.getScreen().newTextGraphics();

        // Draw a welcoming banner
        for (int column = 0; column < super.getScreen().getTerminalSize().getColumns(); column++) {
            tg.setCharacter(column, 0, '-');
        }
        for (int column = 0; column < (super.getScreen().getTerminalSize().getColumns() / 2) - (this.viewName.length() / 2); column++) {
            tg.setCharacter(column, 1, '-');
        }
        tg.putString((super.getScreen().getTerminalSize().getColumns() / 2) - (this.viewName.length() / 2), 1, this.viewName);
        for (int column = (super.getScreen().getTerminalSize().getColumns() / 2) + (this.viewName.length() / 2); column < super.getScreen().getTerminalSize().getColumns(); column++) {
            tg.setCharacter(column, 1, '-');
        }
        for (int column = 0; column < super.getScreen().getTerminalSize().getColumns(); column++) {
            tg.setCharacter(column, 2, '-');
        }

        tg.putString(0, 4, "What do you want to do ?");
        tg.putString(0, 5, "Add a new member [2]");
        tg.putString(0, 6, "Main menu [3]");
        tg.putString(0, 7, "Exit [4]");
        // Draw all Members
        tg.putString(0, controller.getInitialOffset(), "All members of your gym:");
        // We need an offset of 5 because we've drawn element to the screen before drawing members.
        for (int i = 0; i < climbingGym.getMembers().size(); i++) {
            TextGraphics memberTg = super.getScreen().newTextGraphics();
            if (controller.getCurrentSelectedLine() - controller.getInitialOffset() == i) {
                memberTg.putString(0, i + controller.getInitialOffset(), "Member: " + climbingGym.getMembers().get(i).toString(), SGR.BOLD);
                memberTg.setForegroundColor(TextColor.ANSI.BLUE);
                memberTg.setBackgroundColor(TextColor.ANSI.GREEN);
            }
            memberTg.putString(0, i + controller.getInitialOffset(), "Member: " + climbingGym.getMembers().get(i).toString());
        }

        // Refresh the screen to show the changes
        try {
            super.getScreen().refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
