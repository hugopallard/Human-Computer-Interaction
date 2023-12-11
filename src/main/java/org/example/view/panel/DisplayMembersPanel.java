package org.example.view.panel;

import org.example.Main;
import org.example.controler.DisplayMembersController;
import org.example.model.Member;
import org.example.view.WrapperView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayMembersPanel extends WrapperView {

    public final static String ID = "DisplayMembersPanel";
    private JList<Member> membersJList;

    private final DisplayMembersController controller;

    public DisplayMembersPanel(DisplayMembersController controller, CardLayout cardLayout, JPanel cardPanel) {
        super(new JPanel(new BorderLayout()));
        super.getPanel().setPreferredSize(new Dimension(Main.WIDTH - 250, Main.HEIGHT));

        JLabel label = new JLabel("DisplayMembersPanel");
        label.setPreferredSize(new Dimension(Main.WIDTH - 250, Main.HEIGHT - 500));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        super.getPanel().add(label, BorderLayout.NORTH);
        this.controller = controller;

        buildMembersJList(cardLayout, cardPanel);

        super.getPanel().add(membersJList, BorderLayout.CENTER);

    }

    private void buildMembersJList(CardLayout cardLayout, JPanel cardPanel) {

        DefaultListModel<Member> listModel = new DefaultListModel<>();
        for (Member member : controller.getClimbingGym().getMembers()) {
            listModel.addElement(member);
        }
        membersJList = new JList<>(listModel);

        // Get the selected item and perform your action
        membersJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Member selectedMember = membersJList.getSelectedValue();
                System.out.println("Clicked on: " + selectedMember);
                controller.handleMemberDisplay(selectedMember, cardLayout, cardPanel);
            }
        });
    }

    @Override
    public void updateView() {

    }
}
