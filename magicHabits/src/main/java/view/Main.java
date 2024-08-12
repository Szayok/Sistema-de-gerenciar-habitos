package view;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Magic Habits");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        
        // Iniciar com a tela de login
        mainFrame.setContentPane(new LoginPanel(mainFrame));
        mainFrame.setVisible(true);
    }
}
