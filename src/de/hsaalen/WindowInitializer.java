package de.hsaalen;


import javax.swing.*;

public class WindowInitializer extends JFrame {
    public static void initialize(JFrame frame, String title) {
        frame.setResizable(false);
        frame.pack();
        frame.setTitle(title);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initializeWindow() {
        WindowInitializer.initialize(this, "Snake");
    }
}
