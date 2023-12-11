package org.example.controler;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.example.model.ClimbingGym;
import org.example.model.Member;
import org.example.view.WrapperView;
import org.example.view.panel.DisplayMembersPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddMemberController {
    private final Member member;
    private final ClimbingGym climbingGym;
    private final DisplayMembersController displayMembersController;
    private WrapperView view;
    private int currentSelectedLine = 0;

    public AddMemberController(Member member, ClimbingGym climbingGym, WrapperView view, DisplayMembersController displayMembersController) {
        this.member = member;
        this.climbingGym = climbingGym;
        this.view = view;
        this.displayMembersController = displayMembersController;
    }

    public void updateView() {
        view.getScreen().setCursorPosition(new TerminalPosition(0, currentSelectedLine + 2));
        view.getScreen().scrollLines(0, currentSelectedLine + 2, -1);
        view.updateView();
    }

    public void addNewMember(String firstName, String lastName, String email, String password, CardLayout cardLayout, JPanel cardPanel) {
        member.setFirstname(firstName);
        member.setLastname(lastName);
        member.setEmail(email);
        member.setPassword(password);
        climbingGym.getMembers().add(member);

        cardPanel.remove(displayMembersController.getView().getPanel());
        DisplayMembersPanel jpanel = new DisplayMembersPanel(displayMembersController, cardLayout, cardPanel);
        cardPanel.add(jpanel.getPanel(), DisplayMembersPanel.ID);
        cardLayout.show(cardPanel, DisplayMembersPanel.ID);
    }

    public void addNewMember(ClimbingGym climbingGym, Terminal terminal, Screen screen) throws IOException {
        // Ask user to write a field and store the result in the controller (then update the view)
        addField("firstName", terminal);
        screen.setCursorPosition(new TerminalPosition(0, 2));
        screen.refresh();
        addField("lastName", terminal);
        screen.setCursorPosition(new TerminalPosition(0, 4));
        screen.refresh();
        addField("email", terminal);
        screen.setCursorPosition(new TerminalPosition(0, 6));
        screen.refresh();
        addField("password", terminal);
        screen.setCursorPosition(new TerminalPosition(0, 8));
        screen.refresh();
        climbingGym.getMembers().add(member);
    }

    public void addField(String field, Terminal terminal) throws IOException {
        KeyStroke pressedKey = terminal.readInput();
        if (pressedKey.getKeyType() == KeyType.Character && (pressedKey.getCharacter() == '1' || pressedKey.getCharacter() == '3')) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (pressedKey.getKeyType() == KeyType.Enter) {
                System.out.println("\nEnter");
                // We simply exit the function is the stringBuilder is empty when going to the next field, because it means that the user didn't modify the field
                if (sb.isEmpty()) {
                    return;
                }
                break;
            } else if (pressedKey.getKeyType() == KeyType.EOF) {
                continue;
            }
            System.out.print(pressedKey.getCharacter());
            sb.append(pressedKey.getCharacter());
            pressedKey = terminal.readInput();
        }
        switch (field) {
            case "firstName" -> member.setFirstname(sb.toString());
            case "lastName" -> member.setLastname(sb.toString());
            case "email" -> member.setEmail(sb.toString());
            case "password" -> member.setPassword(sb.toString());
        }
        updateView();
    }

    public void setView(WrapperView view) {
        this.view = view;
    }

    public Member getMember() {
        return member;
    }
}
