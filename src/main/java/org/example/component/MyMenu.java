package org.example.component;

import org.example.Main;
import org.example.controler.AddMemberController;
import org.example.controler.DisplayMembersController;
import org.example.view.panel.AddMemberPanel;
import org.example.view.panel.ClimbingGymPanel;
import org.example.view.panel.DisplayMembersPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenu extends JPanel implements ActionListener {
    private final DisplayMembersController displayMembersController;
    private final AddMemberController addMemberController;
    private JButton displayMembersButton;
    private JButton addMemberButton;
    private JButton mainMenuButton;
    private JButton exitButton;
    private final CardLayout cardLayout;
    private final JPanel cardPanel;

    public MyMenu(CardLayout cardLayout, JPanel cardPanel, DisplayMembersController displayMembersController, AddMemberController addMemberController) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        
        this.setPreferredSize(new Dimension(Main.WIDTH - 550, Main.HEIGHT));
        displayMembersButton = new JButton("Display members");
        addMemberButton = new JButton("Add member");
        mainMenuButton = new JButton("Main menu ");
        exitButton = new JButton("Exit ");

        displayMembersButton.addActionListener(this);
        addMemberButton.addActionListener(this);
        mainMenuButton.addActionListener(this);
        exitButton.addActionListener(this);

        this.displayMembersController = displayMembersController;
        this.addMemberController = addMemberController;

        // Finally we update the view for the first time (drawing it)
        this.add(displayMembersButton);
        this.add(addMemberButton);
        this.add(mainMenuButton);
        this.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayMembersButton.setEnabled(true);
        addMemberButton.setEnabled(true);
        mainMenuButton.setEnabled(true);
        exitButton.setEnabled(true);
        if (e.getSource() == displayMembersButton) {
            DisplayMembersPanel displayMembersPanel = new DisplayMembersPanel(displayMembersController, cardLayout, cardPanel);
            cardPanel.add(displayMembersPanel.getPanel(), DisplayMembersPanel.ID);
            displayMembersController.setView(displayMembersPanel);
            cardLayout.show(cardPanel, DisplayMembersPanel.ID);
        } else if (e.getSource() == addMemberButton) {
            addMemberButton.setEnabled(false);
            AddMemberPanel addMemberPanel = new AddMemberPanel(addMemberController, cardLayout, cardPanel);
            cardPanel.add(addMemberPanel.getPanel(), AddMemberPanel.ID);
            addMemberController.setView(addMemberPanel);
            cardLayout.show(cardPanel, AddMemberPanel.ID);
        }else if (e.getSource() == mainMenuButton) {
            mainMenuButton.setEnabled(false);
            cardLayout.show(cardPanel, ClimbingGymPanel.ID);
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
