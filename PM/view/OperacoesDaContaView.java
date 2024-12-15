package view;

import controller.BancoController;
import model.Cliente;
import model.Conta;

import javax.swing.*;
import java.awt.*;

public class OperacoesDaContaView extends JFrame {
    private final Cliente cliente;
    private final BancoController controller;
    private JComboBox<Conta> contaComboBox;
    private JTextArea extratoArea;
    private JTextField valorField;

    public OperacoesDaContaView(Cliente cliente) {
        this.cliente = cliente;
        this.controller = BancoController.getInstance();
        setupUI();
        atualizarExtratoArea();
    }

    private void setupUI() {
        setTitle("XuBank - Operações");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));

        JPanel clientePanel = new JPanel(new GridLayout(2, 1, 5, 5));
        clientePanel.add(new JLabel("Cliente: " + cliente.getNome() + " - CPF: " + cliente.getCpf()));

        JPanel contaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        contaComboBox = new JComboBox<>(cliente.getContas().toArray(new Conta[0]));
        contaComboBox.addActionListener(e -> atualizarExtratoArea());

        JButton novaConta = new JButton("Nova Conta");
        novaConta.addActionListener(e -> criarNovaConta());

        contaPanel.add(new JLabel("Selecione a conta:"));
        contaPanel.add(contaComboBox);
        contaPanel.add(novaConta);

        clientePanel.add(contaPanel);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Sair");
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> logout());
        logoutPanel.add(logoutButton);

        topPanel.add(clientePanel, BorderLayout.CENTER);
        topPanel.add(logoutPanel, BorderLayout.EAST);

        JPanel operacoesPanel = new JPanel(new FlowLayout());
        valorField = new JTextField(10);
        JButton sacarBtn = new JButton("Sacar");
        JButton depositarBtn = new JButton("Depositar");

        sacarBtn.addActionListener(e -> realizarSaque());
        depositarBtn.addActionListener(e -> realizarDeposito());

        operacoesPanel.add(new JLabel("Valor: R$"));
        operacoesPanel.add(valorField);
        operacoesPanel.add(sacarBtn);
        operacoesPanel.add(depositarBtn);

        extratoArea = new JTextArea();
        extratoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(extratoArea);

        add(topPanel, BorderLayout.NORTH);
        add(operacoesPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void atualizarExtratoArea() {
        Conta contaSelecionada = (Conta) contaComboBox.getSelectedItem();
        if (contaSelecionada != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Tipo: ").append(contaSelecionada.getClass().getSimpleName())
                    .append("\nSaldo: R$").append(String.format("%.2f", contaSelecionada.getSaldo()))
                    .append("\n\nExtrato:\n");

            contaSelecionada.getExtrato().forEach(op -> sb.append(op).append("\n"));
            extratoArea.setText(sb.toString());
        }
    }

    private void criarNovaConta() {
        String[] tipos = {"CORRENTE", "POUPANCA", "INVESTIMENTO"};
        String tipoConta = (String) JOptionPane.showInputDialog(
                this, "Selecione o tipo de conta:", "Nova Conta",
                JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);

        if (tipoConta != null) {
            controller.criarContaPorTipo(cliente, tipoConta);
            atualizarComboContas();
            JOptionPane.showMessageDialog(this, "Nova conta criada com sucesso!");
        }
    }

    private void atualizarComboContas() {
        contaComboBox.removeAllItems();
        for (Conta conta : cliente.getContas()) {
            contaComboBox.addItem(conta);
        }
        atualizarExtratoArea();
    }

    private void realizarSaque() {
        double valor = Double.parseDouble(valorField.getText());
        Conta contaSelecionada = (Conta) contaComboBox.getSelectedItem();
        contaSelecionada.sacar(valor);
        atualizarExtratoArea();
    }

    private void realizarDeposito() {
        double valor = Double.parseDouble(valorField.getText());
        Conta contaSelecionada = (Conta) contaComboBox.getSelectedItem();
        contaSelecionada.depositar(valor);
        atualizarExtratoArea();
    }

    private void logout() {
        new LoginView().setVisible(true);
        dispose();
    }
}
