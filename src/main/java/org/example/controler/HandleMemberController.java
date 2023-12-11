package org.example.controler;

import org.example.model.ClimbingGym;
import org.example.model.Member;
import org.example.view.WrapperView;
import org.example.view.panel.HandleMemberPanel;
import org.example.view.panel.ModifyMemberPanel;

import javax.swing.*;
import java.awt.*;

public class HandleMemberController {
    private final DisplayMembersController displayMembersController;
    private WrapperView view;
    private final Member member;
    private final ClimbingGym climbingGym;

    public HandleMemberController(Member member, ClimbingGym climbingGym, WrapperView view, DisplayMembersController displayMembersController) {
        this.member = member;
        this.view = view;
        this.climbingGym = climbingGym;
        this.displayMembersController = displayMembersController;
    }

    public void updateView() {
        this.view.updateView();
    }

    public void deleteMember() {
        climbingGym.getMembers().remove(member);
        System.out.println("Deleted member: " + member.toString());
    }

    public void handleModifyMember(CardLayout cardLayout, JPanel cardPanel) {
        ModifyMemberController modifyMemberController = new ModifyMemberController(member, climbingGym, null, displayMembersController);
        ModifyMemberPanel modifyMemberPanel = new ModifyMemberPanel(modifyMemberController, cardLayout, cardPanel);
        modifyMemberController.setView(modifyMemberPanel);
        cardPanel.add(modifyMemberPanel.getPanel(), ModifyMemberPanel.ID);
        cardLayout.show(cardPanel, ModifyMemberPanel.ID);
    }

    public void setView(WrapperView view) {
        this.view = view;
    }

    public WrapperView getView() {
        return view;
    }

    public Member getMember() {
        return member;
    }
}
