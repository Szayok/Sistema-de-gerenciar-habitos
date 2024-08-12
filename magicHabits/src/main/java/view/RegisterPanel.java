package view;

import controller.UsuariosController;
import model.entities.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegisterPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JFrame mainFrame;
    private UsuariosController usuariosController;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RegisterPanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.usuariosController = new UsuariosController();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nameLabel = new JLabel("Nome de Usuário:");
        JTextField nameField = new JTextField(15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Senha:");
        JPasswordField passwordField = new JPasswordField(15);
        JLabel birthdateLabel = new JLabel("Data de Nascimento (DD/MM/AAAA):");
        JTextField birthdateField = new JTextField(15);

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String birthdateStr = birthdateField.getText();

                LocalDate birthdate = null;
                try {
                    birthdate = LocalDate.parse(birthdateStr, DATE_FORMATTER);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(RegisterPanel.this, "Data de nascimento inválida. Use o formato DD/MM/AAAA.");
                    return;
                }

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || birthdate == null) {
                    JOptionPane.showMessageDialog(RegisterPanel.this, "Todos os campos são obrigatórios.");
                } else {
                    Usuarios newUser = new Usuarios();
                    newUser.setNomeUsuario(name);
                    newUser.setEmail(email);
                    newUser.setSenha(password);
                    newUser.setDataNascimento(birthdateStr);

                    usuariosController.create(newUser);

                    JOptionPane.showMessageDialog(RegisterPanel.this, "Usuário registrado com sucesso!");
                    mainFrame.setContentPane(new LoginPanel(mainFrame));
                    mainFrame.revalidate();
                }
            }
        });

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
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
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(birthdateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(birthdateField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(backButton, gbc);
    }
}
