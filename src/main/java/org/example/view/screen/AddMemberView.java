package org.example.view.screen;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.example.controler.AddMemberController;
import org.example.model.Member;
import org.example.view.WrapperView;

import java.io.IOException;

public class AddMemberView extends WrapperView {

    private final AddMemberController controller;

    public AddMemberView(Screen screen, AddMemberController controller) {
        super(screen);
        this.controller = controller;
    }

    public void updateView() {
        // Clear the screen before drawing the newest view
        super.getScreen().clear();
        // Get the model from the controller. It's a shallow copy because Member is not a primitive type.
        Member member = controller.getMember();
        TextGraphics tg = super.getScreen().newTextGraphics();
        tg.putString(0, 0, "------------ Hello AddMemberView ----------");
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

            tg.putString(0, 10, "The following member has been created: ");
            tg.putString(0,11, member.toString());
            tg.putString(0, 13, "What do you want to do ?");
            tg.putString(0, 14, "Print all members of your gym [1]");
            tg.putString(0, 15, "Main menu [3]");
            tg.putString(0, 16, "Exit menu [4]");
        }
        // Refresh the screen to show the changes
        try {
            super.getScreen().refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
