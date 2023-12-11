package org.example.view.panel;

import org.example.Main;
import org.example.controler.AddMemberController;
import org.example.view.WrapperView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMemberPanel extends WrapperView implements ActionListener {

    public final static String ID = "AddMemberPanel";
    private final JButton submitButton;
    private final JTextField firstName;
    private final JTextField lastName;
    private final JTextField password;
    private final JTextField email;
    private final AddMemberController controller;
    private final CardLayout cardLayout;
    private final JPanel panelContainer;

    public AddMemberPanel(AddMemberController controller, CardLayout cardLayout, JPanel panelContainer) {
        super(new JPanel(new BorderLayout()));
        super.getPanel().setPreferredSize(new Dimension(Main.WIDTH - 250, Main.HEIGHT));

        this.controller = controller;
        this.cardLayout = cardLayout;
        this.panelContainer = panelContainer;

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 120, 40, 120));
        JLabel titleLabel = new JLabel("AddMemberPanel");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        super.getPanel().add(titlePanel, BorderLayout.NORTH);

        JPanel fieldPanel = new JPanel(new GridLayout(0, 1, 0, 30));
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        // Create a text input field
        firstName = new JTextField(20);
        lastName = new JTextField(20);
        password = new JTextField(20);
        email = new JTextField(40);
        fieldPanel.add(firstName);
        fieldPanel.add(lastName);
        fieldPanel.add(password);
        fieldPanel.add(email);
        super.getPanel().add(fieldPanel, BorderLayout.CENTER);

        JPanel submitPanel = new JPanel(new BorderLayout());
        submitPanel.setBorder(BorderFactory.createEmptyBorder(40, 120, 15, 120));
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitPanel.add(submitButton, BorderLayout.CENTER);
        super.getPanel().add(submitPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            controller.addNewMember(firstName.getText(), lastName.getText(), email.getText(), password.getText(), cardLayout, panelContainer);
        }
    }

    @Override
    public void updateView() {

    }
}
