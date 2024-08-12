package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.HabitosController;
import model.entities.Habitos;
import model.entities.Usuarios;

public class ViewHabitsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JFrame mainFrame;
    private Usuarios usuarioLogado;
    private HabitosController habitosController;

    public ViewHabitsPanel(JFrame mainFrame, Usuarios usuarioLogado) {
        this.mainFrame = mainFrame;
        this.usuarioLogado = usuarioLogado;
        this.habitosController = new HabitosController(); // Instancia o controller para acessar os hábitos

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Lógica para buscar e mostrar os hábitos do usuário
        List<Habitos> habitsList = habitosController.findAllByUserId(usuarioLogado.getIdUsuario());

        // Painel para exibir os hábitos
        JPanel habitsPanel = new JPanel();
        habitsPanel.setLayout(new BoxLayout(habitsPanel, BoxLayout.Y_AXIS));

        // Criar botões para cada hábito
        for (Habitos habit : habitsList) {
            JButton habitButton = createHabitButton(habit);
            habitsPanel.add(habitButton);
        }

        JScrollPane scrollPane = new JScrollPane(habitsPanel);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(new MainPanel(mainFrame, usuarioLogado));
                mainFrame.revalidate();
            }
        });

        // Criar gráfico de barras
        JPanel chartPanel = createChartPanel(habitsList);

        add(scrollPane, BorderLayout.CENTER);
        add(chartPanel, BorderLayout.EAST);
        add(backButton, BorderLayout.SOUTH);
    }

    // Método para criar botão de hábito
    private JButton createHabitButton(Habitos habit) {
        JButton habitButton = new JButton(habit.getTitulo());
        if (habit.isCompleta()) {
            habitButton.setBackground(Color.GREEN); // Define cor verde se o hábito estiver completo
        }
        habitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar detalhes do hábito
                showHabitDetails(habit, habitButton);
            }
        });
        return habitButton;
    }

    // Método para mostrar detalhes do hábito
    private void showHabitDetails(Habitos habit, JButton habitButton) {
        JPanel habitDetailsPanel = new JPanel();
        habitDetailsPanel.setLayout(new GridLayout(6, 2));

        JLabel titleLabel = new JLabel("Título:");
        JTextField titleField = new JTextField(habit.getTitulo());
        JLabel descriptionLabel = new JLabel("Descrição:");
        JTextField descriptionField = new JTextField(habit.getDescricao());
        JLabel experienceLabel = new JLabel("Experiência:");
        JTextField experienceField = new JTextField(String.valueOf(habit.getExperiencia()));
        JLabel coinsLabel = new JLabel("Moedas:");
        JTextField coinsField = new JTextField(String.valueOf(habit.getMoedaHabi()));
        JLabel dueDateLabel = new JLabel("Data de Vencimento:");
        JTextField dueDateField = new JTextField(habit.getDataVencimento().toString());

        JButton markCompleteButton = new JButton("Marcar como Feito");
        markCompleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implementar lógica para marcar o hábito como feito
                habit.setCompleta(true);
                habitosController.updateById(habit); // Atualizar no banco de dados
                JOptionPane.showMessageDialog(habitDetailsPanel, "Hábito marcado como feito!");

                // Atualizar a cor do botão se o hábito for marcado como completo
                habitButton.setBackground(Color.GREEN);
            }
        });

        JButton saveButton = new JButton("Salvar");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Atualizar os valores do hábito
                habit.setTitulo(titleField.getText());
                habit.setDescricao(descriptionField.getText());
                habit.setExperiencia(Double.parseDouble(experienceField.getText()));
                habit.setMoedaHabi(Double.parseDouble(coinsField.getText()));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = dateFormat.parse(dueDateField.getText());
                    habit.setDataVencimento(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(habitDetailsPanel, "Formato de data inválido.");
                    return;
                }

                // Salvar no banco de dados
                habitosController.updateById(habit);
                JOptionPane.showMessageDialog(habitDetailsPanel, "Hábito atualizado com sucesso!");
            }
        });

        JButton deleteButton = new JButton("Deletar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(habitDetailsPanel, "Tem certeza que deseja deletar este hábito?",
                        "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    habitosController.delete(habit);
                    JOptionPane.showMessageDialog(habitDetailsPanel, "Hábito deletado com sucesso!");
                    // Fechar o JDialog após deletar o hábito
                    SwingUtilities.getWindowAncestor(habitDetailsPanel).dispose();
                    // Remover o botão da interface ao deletar
                    habitButton.setVisible(false);
                }
            }
        });

        habitDetailsPanel.add(titleLabel);
        habitDetailsPanel.add(titleField);
        habitDetailsPanel.add(descriptionLabel);
        habitDetailsPanel.add(descriptionField);
        habitDetailsPanel.add(experienceLabel);
        habitDetailsPanel.add(experienceField);
        habitDetailsPanel.add(coinsLabel);
        habitDetailsPanel.add(coinsField);
        habitDetailsPanel.add(dueDateLabel);
        habitDetailsPanel.add(dueDateField);
        habitDetailsPanel.add(markCompleteButton);
        habitDetailsPanel.add(saveButton);
        habitDetailsPanel.add(deleteButton);

        JDialog detailsDialog = new JDialog(mainFrame, "Detalhes do Hábito", true);
        detailsDialog.getContentPane().add(habitDetailsPanel);
        detailsDialog.pack();
        detailsDialog.setLocationRelativeTo(null);
        detailsDialog.setVisible(true);
    }

    // Método para criar o painel de gráfico
    private JPanel createChartPanel(List<Habitos> habitsList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int completedCount = 0;
        int notCompletedCount = 0;

        // Adicionar contagens ao dataset
        for (Habitos habit : habitsList) {
            if (habit.isCompleta()) {
                completedCount++;
            } else {
                notCompletedCount++;
            }
        }

        // Adicionar valores ao dataset
        dataset.addValue(completedCount, "Status", "Completos");
        dataset.addValue(notCompletedCount, "Status", "Não Completos");

        // Criar o gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Hábitos Completados",
                "Status",
                "Quantidade",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Personalizar o renderer de barras
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);  // Cor para "Completos"
        renderer.setSeriesPaint(1, Color.RED);    // Cor para "Não Completos"

        // Criar o painel do gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 200));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

}
