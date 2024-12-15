package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

import controller.BancoController;
import model.Cliente;

public class AdminView extends JFrame {
    private BancoController controller;
    private JTextArea statsArea;

    public AdminView() {
        controller = BancoController.getInstance();
        setupUI();
        atualizarEstatisticas();
    }

    private void setupUI() {
        setTitle("XuBank - Painel Administrativo");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Painel Administrativo");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);

        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(statsArea);

        JPanel buttonPanel = new JPanel();
        JButton voltarButton = new JButton("Voltar");
        JButton atualizarButton = new JButton("Atualizar");

        voltarButton.addActionListener(e -> {
            new InicioView().setVisible(true);
            dispose();
        });

        atualizarButton.addActionListener(e -> atualizarEstatisticas());

        buttonPanel.add(atualizarButton);
        buttonPanel.add(voltarButton);

        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void atualizarEstatisticas() {
        StringBuilder stats = new StringBuilder();

        stats.append("=== Valores em Custódia por Tipo de Conta ===\n\n");
        double totalCorrente = controller.getTotalPorTipoConta("ContaCorrente");
        double totalPoupanca = controller.getTotalPorTipoConta("ContaPoupanca");
        double totalInvestimento = controller.getTotalPorTipoConta("ContaInvestimento");
        double totalRendaFixa = controller.getTotalPorTipoConta("RendaFixa");

        stats.append(String.format("Conta Corrente: R$ %.2f\n", totalCorrente));
        stats.append(String.format("Conta Poupança: R$ %.2f\n", totalPoupanca));
        stats.append(String.format("Conta Investimento: R$ %.2f\n", totalInvestimento));
        stats.append(String.format("Conta Renda Fixa: R$ %.2f\n\n", totalRendaFixa));

        double mediaTotal = (totalCorrente + totalPoupanca + totalInvestimento + totalRendaFixa)
                / controller.getTotalClientes();
        stats.append(String.format("Saldo Médio por Cliente: R$ %.2f\n\n", mediaTotal));

        Cliente maiorSaldo = controller.getClienteMaiorSaldo();
        Cliente menorSaldo = controller.getClienteMenorSaldo();

        stats.append("=== Clientes Extremos ===\n\n");
        if (maiorSaldo != null) {
            stats.append(String.format("Maior Saldo: %s (CPF: %s) - R$ %.2f\n",
                    maiorSaldo.getNome(), maiorSaldo.getCpf(), maiorSaldo.getSaldoTotal()));
        }
        if (menorSaldo != null) {
            stats.append(String.format("Menor Saldo: %s (CPF: %s) - R$ %.2f\n",
                    menorSaldo.getNome(), menorSaldo.getCpf(), menorSaldo.getSaldoTotal()));
        }

        statsArea.setText(stats.toString());
    }
}