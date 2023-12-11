package org.example.view.panel;

import org.example.Main;
import org.example.controler.ModifyMemberController;
import org.example.view.WrapperView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyMemberPanel extends WrapperView implements ActionListener {

    public final static String ID = "ModifyMemberPanel";

    private final ModifyMemberController controller;

    private final JButton modifyButton;
    private final JTextField firstName;
    private final JTextField lastName;
    private final JTextField email;
    private final JTextField password;

    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public ModifyMemberPanel(ModifyMemberController controller, CardLayout cardLayout, JPanel cardPanel) {
        super(new JPanel());
        super.getPanel().setPreferredSize(new Dimension(Main.WIDTH - 250, Main.HEIGHT));

        this.controller = controller;
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 120, 40, 120));
        JLabel titleLabel = new JLabel("Modify Member Panel");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        super.getPanel().add(titlePanel, BorderLayout.NORTH);

        JPanel fieldPanel = new JPanel(new GridLayout(0, 1, 0, 30));
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        // Create a text input field
        firstName = new JTextField(20);
        lastName = new JTextField(20);
        email = new JTextField(40);
        password = new JTextField(20);

        firstName.setText(controller.getMember().getFirstname());
        lastName.setText(controller.getMember().getLastname());
        email.setText(controller.getMember().getEmail());
        password.setText(controller.getMember().getPassword());

        fieldPanel.add(firstName);
        fieldPanel.add(lastName);
        fieldPanel.add(email);
        fieldPanel.add(password);

        super.getPanel().add(fieldPanel, BorderLayout.CENTER);

        JPanel modifyPanel = new JPanel(new BorderLayout());
        modifyPanel.setBorder(BorderFactory.createEmptyBorder(40, 120, 15, 120));
        modifyButton = new JButton("Modify");
        modifyButton.addActionListener(this);
        modifyPanel.add(modifyButton, BorderLayout.CENTER);
        super.getPanel().add(modifyPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton) {
            controller.modifyMember(firstName.getText(), lastName.getText(), email.getText(), password.getText(), cardLayout, cardPanel);
        }
    }

    @Override
    public void updateView() {

    }
}
