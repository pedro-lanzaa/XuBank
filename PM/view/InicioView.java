package view;

import javax.swing.*;
import java.awt.*;

public class InicioView extends JFrame {

    public InicioView() {
        setupUI();
    }

    private void setupUI() {
        setTitle("Bem-vindo ao XuBank");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel logoPanel = new JPanel();
        JLabel titleLabel = new JLabel("XuBank");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        logoPanel.add(titleLabel);

        JPanel descPanel = new JPanel();
        descPanel.setLayout(new BorderLayout());
        JTextArea descText = new JTextArea(
                "Bem-vindo ao XuBank, sua fintech do futuro!\n" +
                        "Oferecemos serviços financeiros modernos e seguros:\n" +
                        "• Conta Corrente com limite flexível\n" +
                        "• Poupança com rendimento garantido\n" +
                        "• Investimentos diversificados\n" +
                        "• Renda Fixa com excelentes taxas");
        descText.setEditable(false);
        descText.setBackground(getBackground());
        descText.setFont(new Font("Arial", Font.PLAIN, 14));
        descPanel.add(descText, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton loginButton = new JButton("Entrar");
        JButton cadastroButton = new JButton("Criar Conta");

        loginButton.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });

        cadastroButton.addActionListener(e -> {
            new CadastroView().setVisible(true);
            dispose();
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(cadastroButton);

        add(logoPanel, BorderLayout.NORTH);
        add(descPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        JButton adminButton = new JButton("PAINEL DO ADM");

        loginButton.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });

        cadastroButton.addActionListener(e -> {
            new CadastroView().setVisible(true);
            dispose();
        });

        adminButton.addActionListener(e -> {
            String senha = JOptionPane.showInputDialog(this,
                    "Digite a senha de administrador:",
                    "Acesso Restrito",
                    JOptionPane.QUESTION_MESSAGE);

            if (senha != null && senha.equals("admin")) {
                new AdminView().setVisible(true);
                dispose();
            } else if (senha != null) {
                JOptionPane.showMessageDialog(this,
                        "Senha incorreta!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(cadastroButton);
        buttonPanel.add(adminButton);

        add(logoPanel, BorderLayout.NORTH);
        add(descPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }
}