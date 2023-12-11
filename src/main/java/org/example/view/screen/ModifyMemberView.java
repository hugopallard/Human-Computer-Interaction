package org.example.view.screen;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.example.controler.ModifyMemberController;
import org.example.model.Member;
import org.example.view.WrapperView;

import java.io.IOException;

public class ModifyMemberView extends WrapperView {
    private String viewName = " Hello ModifyMember View ";
    private final ModifyMemberController controller;

    public ModifyMemberView(Screen screen, ModifyMemberController controller) {
        super(screen);
        this.controller = controller;
    }

    @Override
    public void updateView() {
        // Clear the screen before drawing the newest view
        super.getScreen().clear();
        Member member = controller.getMember();
        // Write a text
        TextGraphics tg = super.getScreen().newTextGraphics();
        tg.putString(0, 0, "------------ " + viewName + " ----------");
        tg.putString(0, 1, "Firstname: ");
        if (member.getFirstname() != null) {
            tg.putString(0, 2, member.getFirstname());
            tg.putString(0, 3, "Lastname: ");
        }
        if (member.getFirstname() != null && member.getLastname() != null) {
            tg.putString(0, 4, member.getLastname());
            tg.putString(0, 5, "Email: ");
        }
        if (member.getFirstname() != null && member.getLastname() != null && member.getEmail() != null) {
            tg.putString(0, 6, member.getEmail());
            tg.putString(0, 7, "Password: ");
        }
        if (member.getFirstname() != null && member.getLastname() != null && member.getEmail() != null && member.getPassword() != null) {
            tg.putString(0, 8, member.getPassword());
            tg.putString(0, 10, "What do you want to do ?");
            tg.putString(0, 11, "Print all members of your gym [1]");
            tg.putString(0, 12, "Add a new member [2]");
            tg.putString(0, 13, "Main menu [3]");
            tg.putString(0, 14, "Exit menu [4]");
        }
        // Refresh the screen to show the changes
        try {
            super.getScreen().refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
