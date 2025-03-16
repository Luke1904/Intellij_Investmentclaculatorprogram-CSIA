import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// MainMenu class represents the main menu of the application
public class MainMenu implements ActionListener {

    // GUI components
    public JFrame frame = new JFrame("Investment calculator"); // Main frame for the menu
    public JLabel label = new JLabel("Main Menu"); // Label for the menu title
    public JButton button1 = new JButton("Go to investment calculator"), // Button to open the calculator
            button2 = new JButton("Open saves"); // Button to open the saves directory
    public JPanel panel = new JPanel(); // Panel to hold the menu components

    // Constructor to initialize the main menu
    public MainMenu() {

        // Create the saves folder if it doesn't exist
        FolderManager.createSaveFolder();

        // Get the screen size for dynamic positioning
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

        // Set up the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the frame is closed
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        frame.setLayout(null); // Use absolute positioning for components
        frame.setVisible(true); // Make the frame visible

        // Set up the "Go to investment calculator" button
        button1.setBounds((ss.width / 2) - (200 / 2), (ss.height / 2) - (30 / 2) - 30, 200, 30); // Position the button
        button1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); // Add a raised border
        button1.setFocusable(false); // Disable focus for the button
        frame.add(button1); // Add the button to the frame

        // Set up the "Open saves" button
        button2.setBounds((ss.width / 2) - (200 / 2), (ss.height / 2) - (30 / 2) + (30 / 2), 200, 30); // Position the button
        button2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); // Add a raised border
        button2.setFocusable(false); // Disable focus for the button
        frame.add(button2); // Add the button to the frame

        // Set up the main menu label
        label.setBounds(ss.width / 2 - 63, ss.height / 2 - ((panel.getY() + button1.getY()) / 2) + 30, 300, 40); // Position the label
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Set the font for the label
        frame.add(label); // Add the label to the frame

        // Set up the main panel
        frame.add(panel); // Add the panel to the frame
        panel.setLayout(null); // Use absolute positioning for components within the panel
        panel.setBorder(BorderFactory.createLineBorder(Color.black)); // Add a border to the panel
        panel.setBounds(ss.width / 2 - 250, ss.height / 2 - 250, 500, 500); // Position and size the panel
        panel.setBackground(Color.LIGHT_GRAY); // Set the background color of the panel
        panel.setVisible(true); // Make the panel visible

        // Add action listeners to the buttons
        button1.addActionListener(e -> {
            if (e.getSource() == button1) { // Check if the source of the event is button1
                frame.dispose(); // Dispose the current frame
                CalculatorWindow calculate = new CalculatorWindow(); // Open the CalculatorWindow
            }
        });

        button2.addActionListener(e -> {
            if (e.getSource() == button2) { // Check if the source of the event is button2
                String homeDirectory = System.getProperty("user.home"); // Get the user's home directory
                String directoryPath = homeDirectory + "\\Desktop\\Saves"; // Path to the saves directory
                File directory = new File(directoryPath); // Create a File object for the directory
                try {
                    Desktop.getDesktop().open(directory); // Open the directory using the default file manager
                } catch (IOException a) {
                    a.printStackTrace(); // Print the stack trace if an error occurs
                }
            }
        });
    }

    // ActionListener implementation (not used in this class)
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}