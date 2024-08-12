package view;

import javax.swing.*;


import controller.PetsController;
import model.entities.Pets;
import model.entities.Usuarios;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent; // Import necessário para MouseEvent
import java.awt.event.MouseAdapter; // Import necessário para MouseAdapter
import java.util.List;

public class ViewPetsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JFrame mainFrame;
    private Usuarios usuarioLogado;
    private PetsController petsController;

    public ViewPetsPanel(JFrame mainFrame, Usuarios usuarioLogado) {
        this.mainFrame = mainFrame;
        this.usuarioLogado = usuarioLogado;
        this.petsController = new PetsController(); // Instância o controller para acessar os pets

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

       
        List<Pets> petsList = petsController.findall();

        
        JPanel petsPanel = new JPanel();
        petsPanel.setLayout(new BoxLayout(petsPanel, BoxLayout.Y_AXIS));

        
        for (Pets pet : petsList) {
            JPanel petPanel = createPetPanel(pet);
            petsPanel.add(petPanel);
        }

        JScrollPane scrollPane = new JScrollPane(petsPanel);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(new MainPanel(mainFrame, usuarioLogado));
                mainFrame.revalidate();
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }

    // Método para criar um painel para exibir os detalhes de um pet
    private JPanel createPetPanel(Pets pet) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel("Nome: " + pet.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(nameLabel, BorderLayout.NORTH);

        JLabel fomeLabel = new JLabel("Nível de Fome: " + pet.getNivelFome());
        JLabel felicidadeLabel = new JLabel("Nível de Felicidade: " + pet.getNivelFelicidade());

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(2, 1));
        detailsPanel.add(fomeLabel);
        detailsPanel.add(felicidadeLabel);

        panel.add(detailsPanel, BorderLayout.CENTER);

        // Adicionar ação ao clicar no painel do pet
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPetDetails(pet);
            }
        });

        return panel;
    }

    // Método para mostrar os detalhes completos de um pet
    private void showPetDetails(Pets pet) {
        JPanel petDetailsPanel = new JPanel();
        petDetailsPanel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Nome:");
        JLabel nameValueLabel = new JLabel(pet.getNome());
        JLabel fomeLabel = new JLabel("Nível de Fome:");
        JLabel fomeValueLabel = new JLabel(String.valueOf(pet.getNivelFome()));
        JLabel felicidadeLabel = new JLabel("Nível de Felicidade:");
        JLabel felicidadeValueLabel = new JLabel(String.valueOf(pet.getNivelFelicidade()));

        petDetailsPanel.add(nameLabel);
        petDetailsPanel.add(nameValueLabel);
        petDetailsPanel.add(fomeLabel);
        petDetailsPanel.add(fomeValueLabel);
        petDetailsPanel.add(felicidadeLabel);
        petDetailsPanel.add(felicidadeValueLabel);

        JOptionPane.showMessageDialog(this, petDetailsPanel, "Detalhes do Pet", JOptionPane.PLAIN_MESSAGE);
    }
}
