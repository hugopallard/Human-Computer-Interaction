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

public class ModifyMemberController {

    private final Member member;
    private final ClimbingGym climbingGym;
    private final DisplayMembersController displayMembersController;
    private WrapperView view;

    public ModifyMemberController(Member member, ClimbingGym climbingGym, WrapperView view, DisplayMembersController displayMembersController) {
        this.member = member;
        this.climbingGym = climbingGym;
        this.view = view;
        this.displayMembersController = displayMembersController;
    }

    public void updateView() {
        this.view.updateView();
    }

    public void modifyMember(String firstName, String lastName, String email, String password, CardLayout cardLayout, JPanel cardPanel){
        member.setFirstname(firstName);
        member.setLastname(lastName);
        member.setEmail(email);
        member.setPassword(password);

        cardPanel.remove(displayMembersController.getView().getPanel());
        DisplayMembersPanel jpanel = new DisplayMembersPanel(displayMembersController, cardLayout, cardPanel);
        cardPanel.add(jpanel.getPanel(), DisplayMembersPanel.ID);
        cardLayout.show(cardPanel, DisplayMembersPanel.ID);
    }

    public void modifyMember(Screen screen, Terminal terminal) throws IOException {
        screen.setCursorPosition(new TerminalPosition(0, 2));
        screen.refresh();
        modifyMemberField("firstName", terminal);
        screen.setCursorPosition(new TerminalPosition(0, 4));
        screen.refresh();
        modifyMemberField("lastName", terminal);
        screen.setCursorPosition(new TerminalPosition(0, 6));
        screen.refresh();
        modifyMemberField("email", terminal);
        screen.setCursorPosition(new TerminalPosition(0, 8));
        screen.refresh();
        modifyMemberField("password", terminal);
    }

    public void modifyMemberField(String field, Terminal terminal) throws IOException {
        KeyStroke pressedKey = terminal.readInput();
        if (pressedKey.getKeyType() == KeyType.Character && (pressedKey.getCharacter() == '2' || pressedKey.getCharacter() == '3') || pressedKey.getCharacter() == '4') {
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
            case "firstName" -> {
                member.setFirstname(sb.toString());
            }
            case "lastName" -> {
                member.setLastname(sb.toString());
            }
            case "email" -> {
                member.setEmail(sb.toString());
            }
            case "password" -> {
                member.setPassword(sb.toString());
            }
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
