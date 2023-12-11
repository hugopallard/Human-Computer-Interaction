package org.example;

import com.google.gson.Gson;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import org.example.component.MyMenu;
import org.example.controler.*;
import org.example.model.ClimbingGym;
import org.example.model.Member;
import org.example.view.panel.ClimbingGymPanel;
import org.example.view.panel.ModifyMemberPanel;
import org.example.view.screen.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Main {
    private static JFrame myFrame;
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    private static JPanel cardPanel;
    private static CardLayout cardLayout;
    private static String mode = "text-mode";
    private static ClimbingGymController climbingGymController;
    private static DisplayMembersController displayMembersController;
    private static HandleMemberController handleMemberController;
    private static AddMemberController addMemberController;
    private static ModifyMemberController modifyMemberController;
    private static ClimbingGym climbingGym;

    public static void main(String[] args) throws IOException {
        // Retrieving data of the ClimbingGym
        climbingGym = parseJSON();
        assert climbingGym != null;

        // Initialize controllers with an empty view (later, the view is set depending on which application mode it is)
        climbingGymController = new ClimbingGymController(climbingGym, null);
        displayMembersController = new DisplayMembersController(climbingGym, null);

        addMemberController = new AddMemberController(new Member(), displayMembersController.getClimbingGym(), null, displayMembersController);
        modifyMemberController = new ModifyMemberController(climbingGym.getMembers().get(displayMembersController.getCurrentSelectedLine() - displayMembersController.getInitialOffset()), displayMembersController.getClimbingGym(), null, displayMembersController);

        // Run the application differently based on selected mode
        if (Objects.equals(mode, "text-mode")) {
            runAsTextMode();
        } else {
            runAsSwing();
        }
    }

    private static void runAsSwing() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        ClimbingGymPanel climbingGymPanel = new ClimbingGymPanel();
        ModifyMemberPanel modifyMemberPanel = new ModifyMemberPanel(modifyMemberController, cardLayout, cardPanel);

        cardPanel.add(climbingGymPanel.getPanel(), ClimbingGymPanel.ID);
        cardPanel.add(modifyMemberPanel.getPanel(), ModifyMemberPanel.ID);

        cardLayout.show(cardPanel, ClimbingGymPanel.ID);

        climbingGymController.setView(climbingGymPanel);
        modifyMemberController.setView(modifyMemberPanel);

        MyMenu myMenu = new MyMenu(cardLayout, cardPanel, displayMembersController, addMemberController);

        myFrame = new JFrame();
        myFrame.setSize(WIDTH, HEIGHT);
        myFrame.setLayout(new BorderLayout());
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(cardPanel, BorderLayout.CENTER);
        myFrame.add(myMenu, BorderLayout.WEST);
        myFrame.setVisible(true);
    }

    private static void runAsTextMode() throws IOException {
        String currentView = "climbingGymView";
        KeyStroke pressedKey;

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        // Because I am on Windows, I will always get a SwingTerminalFrame from the DefaultTerminalFactory constructor. The following if allows to properly handle the exit operation when closing the window.
        if (terminal instanceof final SwingTerminalFrame swingTerm) {
            swingTerm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        // Instead of redrawing the whole screen every time something changes, the Screen object calculate the difference and only apply the changes
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        TerminalSize terminalSize = terminal.getTerminalSize();
        System.out.println("Terminal Size: " + terminalSize.toString());

        climbingGymController.setView(new ClimbingGymView(screen));
        displayMembersController.setView(new DisplayMembersView(screen, displayMembersController));
        addMemberController.setView(new AddMemberView(screen, addMemberController));
        modifyMemberController.setView(new ModifyMemberView(screen, modifyMemberController));

        // Finally we update the view for the first time (drawing it)
        climbingGymController.updateView();

        // We create a loop to listen to the user's keyboard input
        while (true) {
            // We read the user's input
            pressedKey = terminal.readInput();
            // System.out.println(pressedKey);
            // Here we check keys to switch views
            if (pressedKey.getKeyType() == KeyType.Character) {
                switch (pressedKey.getCharacter().toString()) {
                    case "1" -> {
                        currentView = "displayMembersView";
                        displayMembersController.updateView();
                    }
                    case "2" -> {
                        currentView = "addMemberView";
                        // Draw initial view
                        addMemberController.updateView();
                        // Adding a new member
                        addMemberController.addNewMember(climbingGym, terminal, screen);
                    }
                    case "3" -> {
                        currentView = "climbingGymView";
                        climbingGymController.updateView();
                    }
                    case "4" -> {
                        System.out.println("Closing program");
                        screen.stopScreen();
                        System.exit(0);
                    }
                }
            }
            // Here we handle actions based on the currentView
            if (currentView.equals("displayMembersView")) {
                if (pressedKey.getKeyType() == KeyType.ArrowDown) {
                    // We scroll down
                    displayMembersController.moveDown();
                    displayMembersController.updateView();
                } else if (pressedKey.getKeyType() == KeyType.ArrowUp) {
                    // We scroll up
                    displayMembersController.moveUp();
                    displayMembersController.updateView();
                } else if (pressedKey.getKeyType() == KeyType.Enter) {
                    currentView = "handleMemberView";
                    handleMemberController = new HandleMemberController(climbingGym.getMembers().get(displayMembersController.getCurrentSelectedLine() - displayMembersController.getInitialOffset()), climbingGym, null, displayMembersController);
                    HandleMemberView handleMemberView = new HandleMemberView(screen, handleMemberController);
                    handleMemberController.setView(handleMemberView);
                    // Draw initial view
                    handleMemberController.updateView();
                }
            }
            if (currentView.equals("handleMemberView")) {
                if (pressedKey.getKeyType() == KeyType.Character && pressedKey.getCharacter() == 'D') {
                    handleMemberController.deleteMember();
                    currentView = "displayMembersView";
                    displayMembersController.updateView();
                } else if (pressedKey.getKeyType() == KeyType.Character && pressedKey.getCharacter() == 'M') {
                    currentView = "modifyMemberView";
                    modifyMemberController.updateView();
                    modifyMemberController.modifyMember(screen, terminal);
                    displayMembersController.updateView();
                }
            }
        }
    }

    private static ClimbingGym parseJSON() {
        try {
            // Gathering data from local root json file.
            ClimbingGym climbingGym = new Gson().fromJson(new FileReader("data.json"), ClimbingGym.class);
            System.out.println(climbingGym);
            return climbingGym;
        } catch (FileNotFoundException e) {
            System.out.println("File not found at location: " + "data.json");
        }
        return null;
    }


}