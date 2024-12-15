package view;

import javax.swing.*;
import java.awt.*;
import controller.BancoController;
import model.Cliente;

public class CadastroView extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JPasswordField senhaField;
    private JPasswordField confirmaSenhaField;
    private BancoController controller;

    public CadastroView() {
        controller = BancoController.getInstance();
        setupUI();
    }

    private void setupUI() {
        setTitle("XuBank - Cadastro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Criar Nova Conta", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Nome:"), gbc);
        nomeField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("CPF:"), gbc);
        cpfField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(cpfField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Senha:"), gbc);
        senhaField = new JPasswordField(20);
        gbc.gridx = 1;
        mainPanel.add(senhaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Confirmar Senha:"), gbc);
        confirmaSenhaField = new JPasswordField(20);
        gbc.gridx = 1;
        mainPanel.add(confirmaSenhaField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        cadastrarButton.addActionListener(e -> cadastrarCliente());
        voltarButton.addActionListener(e -> {
            new InicioView().setVisible(true);
            dispose();
        });

        buttonPanel.add(cadastrarButton);
        buttonPanel.add(voltarButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private void cadastrarCliente() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String senha = new String(senhaField.getPassword());
        String confirmaSenha = new String(confirmaSenhaField.getPassword());

        if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
            return;
        }

        if (!senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem!");
            return;
        }

        if (controller.buscarCliente(cpf) != null) {
            JOptionPane.showMessageDialog(this, "CPF já cadastrado!");
            return;
        }

        Cliente cliente = controller.cadastrarCliente(nome, cpf, senha);

        SelecionarConta dialog = new SelecionarConta(this);
        dialog.setVisible(true);

        String tipoConta = dialog.getSelectedType();
        if (tipoConta != null) {
            controller.criarContaPorTipo(cliente, tipoConta);
            JOptionPane.showMessageDialog(this, "Conta criada com sucesso!");
            new OperacoesDaContaView(cliente).setVisible(true);
            dispose();
        }
    }
}