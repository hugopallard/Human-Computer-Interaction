package org.example.view.panel;

import org.example.Main;
import org.example.controler.HandleMemberController;
import org.example.view.WrapperView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleMemberPanel extends WrapperView implements ActionListener {

    public final static String ID = "HandleMemberPanel";

    private final JButton deleteMemberButton;
    private final JButton modifyMemberButton;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private HandleMemberController controller;

    public HandleMemberPanel(HandleMemberController controller, CardLayout cardLayout, JPanel cardPanel) {
        super(new JPanel());
        this.controller = controller;
        super.getPanel().setPreferredSize(new Dimension(Main.WIDTH - 250, Main.HEIGHT));
        JLabel label = new JLabel("HandleMemberPanel");
        super.getPanel().add(label);

        JLabel memberInfo = new JLabel(controller.getMember().toString());
        super.getPanel().add(memberInfo);

        this.deleteMemberButton = new JButton("Delete member");
        this.modifyMemberButton = new JButton("Modify member");
        this.deleteMemberButton.addActionListener(this);
        this.modifyMemberButton.addActionListener(this);
        super.getPanel().add(deleteMemberButton);
        super.getPanel().add(modifyMemberButton);

        this.controller = controller;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    }

    @Override
    public void updateView() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteMemberButton) {
            controller.deleteMember();
        } else if (e.getSource() == modifyMemberButton) {
            controller.handleModifyMember(cardLayout, cardPanel);
        }
    }
}
