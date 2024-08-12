package view;

import controller.HabitosController;

import model.entities.Habitos;
import model.entities.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegisterHabitPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JFrame mainFrame;
    private HabitosController habitosController;
    private Usuarios usuarioLogado; // Adicionando usuário logado
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RegisterHabitPanel(JFrame mainFrame, Usuarios usuarioLogado) { // Recebendo usuário logado no construtor
        this.mainFrame = mainFrame;
        this.usuarioLogado = usuarioLogado; // Salvando o usuário logado
        this.habitosController = new HabitosController();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Título:");
        JTextField titleField = new JTextField(15);
        JLabel descriptionLabel = new JLabel("Descrição:");
        JTextField descriptionField = new JTextField(15);
        JLabel experienceLabel = new JLabel("Experiência:");
        JTextField experienceField = new JTextField(15);
        JLabel coinsLabel = new JLabel("Moedas:");
        JTextField coinsField = new JTextField(15);
        JLabel dueDateLabel = new JLabel("Data de Vencimento (DD/MM/AAAA):");
        JTextField dueDateField = new JTextField(15);

        JButton registerButton = new JButton("Registrar");

        // Adicionando ActionListener para o botão de registrar
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionField.getText();
                String experienceStr = experienceField.getText();
                String coinsStr = coinsField.getText();
                String dueDateStr = dueDateField.getText();

                double experience = 0;
                double coins = 0;
                LocalDate dueDate = null;

                try {
                    experience = Double.parseDouble(experienceStr);
                    coins = Double.parseDouble(coinsStr);
                    dueDate = LocalDate.parse(dueDateStr, DATE_FORMATTER);

                    // Criando um novo objeto Habitos
                    Habitos habitos = new Habitos();
                    habitos.setTitulo(title);
                    habitos.setDescricao(description);
                    habitos.setExperiencia(experience);
                    habitos.setMoedaHabi(coins);
                    habitos.setDataVencimento(java.sql.Date.valueOf(dueDate)); // Converting LocalDate to java.sql.Date
                    habitos.setUsuario(usuarioLogado); // Vinculando o usuário logado

                    // Adicionando o hábito ao controlador
                    habitosController.create(habitos, usuarioLogado);

                    // Exibindo mensagem de sucesso
                    JOptionPane.showMessageDialog(RegisterHabitPanel.this, "Hábito registrado com sucesso!");

                    // Limpando os campos de entrada
                    titleField.setText("");
                    descriptionField.setText("");
                    experienceField.setText("");
                    coinsField.setText("");
                    dueDateField.setText("");

                    // Voltando ao painel anterior
                    mainFrame.getContentPane().removeAll();
                    mainFrame.getContentPane().add(new MainPanel(mainFrame, usuarioLogado)); 
                    mainFrame.revalidate();
                    mainFrame.repaint();

                } catch (NumberFormatException | DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(RegisterHabitPanel.this, "Erro ao registrar hábito. Verifique os dados informados.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botão para voltar
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getContentPane().removeAll();
                mainFrame.getContentPane().add(new MainPanel(mainFrame, usuarioLogado)); // Substitua PreviousPanel pelo nome do painel anterior
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        // Configurando o layout com GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);
        gbc.gridx = 1;
        add(titleField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(descriptionLabel, gbc);
        gbc.gridx = 1;
        add(descriptionField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(experienceLabel, gbc);
        gbc.gridx = 1;
        add(experienceField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(coinsLabel, gbc);
        gbc.gridx = 1;
        add(coinsField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        add(dueDateLabel, gbc);
        gbc.gridx = 1;
        add(dueDateField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        add(registerButton, gbc);
        gbc.gridy++;
        add(backButton, gbc); // Adicionando o botão "Voltar"
    }
}
