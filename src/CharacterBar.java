package javaapplication1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterBar extends JFrame implements ActionListener {
    private JButton[] characterButtons;

    public CharacterBar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load images of character buttons (replace with actual image paths)
        ImageIcon adam = new ImageIcon("Adam-1.png");
        ImageIcon tree = new ImageIcon("tree.png");
        ImageIcon tank = new ImageIcon("tank.png");
        ImageIcon tankman = new ImageIcon("StickTankMan.png");

        characterButtons = new JButton[4];

        // Create buttons for each character
        for (int i = 0; i < characterButtons.length; i++) {
            characterButtons[i] = new JButton();
            ImageIcon icon = (i == 0 ? adam : (i == 1 ? tree : (i == 2 ? tank : tankman)));
            characterButtons[i].setIcon(getScaledImageIcon(icon, 100, 100));
            characterButtons[i].setPreferredSize(new Dimension(100, 150)); // Set the preferred size for the buttons
            characterButtons[i].setActionCommand(Integer.toString(i));
            characterButtons[i].addActionListener(this);

            // Create a JLabel for the text and set its position
            String labelText = (i == 0 ? "20 points" : (i == 1 ? "10 points" : (i == 2 ? "20 points" : "40 points")));
            JLabel label = new JLabel(labelText, SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.BOTTOM); // Align text to the bottom
            characterButtons[i].setLayout(new BorderLayout());
            characterButtons[i].add(label, BorderLayout.SOUTH);
        }

        JPanel buttonPanel = new JPanel();
        for (JButton button : characterButtons) {
            buttonPanel.add(button);
        }

        add(buttonPanel);

        // Set the size of the JFrame based on the button size
        int frameWidth = characterButtons.length * 100 + 20; // Add 20 for padding
        int frameHeight = 200; // Set your desired height
        setSize(frameWidth, frameHeight);

        setLocationRelativeTo(null);
    }

    private ImageIcon getScaledImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int selectedIndex = Integer.parseInt(command);
        System.out.println("Character " + (selectedIndex + 1) + " selected.");  // Replace with your logic
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CharacterBar characterBar = new CharacterBar();
            characterBar.setVisible(true);
        });
    }
}
