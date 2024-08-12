package view;

import controller.UsuariosController;
import model.entities.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JFrame mainFrame;
    private UsuariosController usuariosController;

    private static final long serialVersionUID = 1L;

    public LoginPanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.usuariosController = new UsuariosController(); // Iniciando o controller
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcomeLabel = new JLabel("MagicHabits");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Senha:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                Usuarios user = usuariosController.findByEmail(email);

                if (user != null && user.getSenha().equals(password)) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Logado com Sucesso!");
                    mainFrame.setContentPane(new MainPanel(mainFrame, user));
                    mainFrame.revalidate();
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Email errado ou Senha errada.");
                }
            }
        });

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(new RegisterPanel(mainFrame));
                mainFrame.revalidate();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(welcomeLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(registerButton, gbc);
    }
}
