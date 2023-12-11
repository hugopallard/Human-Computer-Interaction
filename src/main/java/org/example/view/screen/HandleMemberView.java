package org.example.view.screen;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.example.controler.HandleMemberController;
import org.example.controler.ModifyMemberController;
import org.example.model.Member;
import org.example.view.WrapperView;

import javax.swing.*;
import java.io.IOException;

public class HandleMemberView extends WrapperView {
    private String viewName = " Hello HandleMember View ";
    private final HandleMemberController controller;



    public HandleMemberView(Screen screen, HandleMemberController controller) {
        super(screen);
        this.controller = controller;
    }

    public void updateView() {
        // Clear the screen before drawing the newest view
        super.getScreen().clear();
        //
        Member member = controller.getMember();
        // Write a text
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

        // Drawing the member we chose to act upon
        tg.putString(0,4, "You selected: "+ member.toString());

        tg.putString(0, 6, "What do you want to do:");
        tg.putString(0, 7, "Delete member [D]");
        tg.putString(0, 8, "Modify member's data [M]");
        tg.putString(0, 9, "Add a new member [2]");
        tg.putString(0, 10, "Main menu [3]");
        tg.putString(0, 11, "Exit [4]");

        // Refresh the screen to show the changes
        try {
            super.getScreen().refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
