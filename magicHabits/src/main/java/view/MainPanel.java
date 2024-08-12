package view;

import javax.swing.*;

import model.entities.Usuarios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JFrame mainFrame;
    private Usuarios usuarioLogado;

    public MainPanel(JFrame mainFrame, Usuarios usuarioLogado) {
        this.mainFrame = mainFrame;
        this.usuarioLogado = usuarioLogado;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Bem vindo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton registerHabitButton = new JButton("Registrar Hábito");
        registerHabitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(new RegisterHabitPanel(mainFrame, usuarioLogado));
                mainFrame.revalidate();
            }
        });

        JButton viewHabitsButton = new JButton("Ver Hábitos");
        viewHabitsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(new ViewHabitsPanel(mainFrame, usuarioLogado));
                mainFrame.revalidate();
            }
        });

        JButton viewPetsButton = new JButton("Ver Pets");
        viewPetsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(new ViewPetsPanel(mainFrame, usuarioLogado));
                mainFrame.revalidate();
            }
        });

        JButton logoutButton = new JButton("Sair");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(new LoginPanel(mainFrame));
                mainFrame.revalidate();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(registerHabitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(viewHabitsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(viewPetsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(logoutButton, gbc);
    }
}
