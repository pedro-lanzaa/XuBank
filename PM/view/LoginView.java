package view;

import javax.swing.*;
import java.awt.*;
import controller.BancoController;
import model.Cliente;

public class LoginView extends JFrame {
    private JTextField cpfField;
    private JPasswordField senhaField;
    private BancoController controller;

    public LoginView() {
        controller = BancoController.getInstance();
        setupUI();
    }

    private void setupUI() {
        setTitle("XuBank - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel logoPanel = new JPanel();
        JLabel titleLabel = new JLabel("XuBank");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        logoPanel.add(titleLabel);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(20);
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(cpfLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(cpfField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(senhaLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(senhaField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton loginButton = new JButton("Entrar");
        JButton backButton = new JButton("Voltar");

        backButton.addActionListener(e -> {
            new InicioView().setVisible(true);
            dispose();
        });

        loginButton.addActionListener(e -> {
            String cpf = cpfField.getText();
            String senha = new String(senhaField.getPassword());

            if (controller.autenticarCliente(cpf, senha)) {
                Cliente cliente = controller.buscarCliente(cpf);
                new OperacoesDaContaView(cliente).setVisible(true);
                dispose(); // Fecha a tela de login
            } else {
                JOptionPane.showMessageDialog(this, "CPF ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);

        add(logoPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
