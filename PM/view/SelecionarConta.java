package view;

import javax.swing.*;

import java.awt.*;

public class SelecionarConta extends JDialog {
    private String selectedType = null;

    public SelecionarConta(Frame parent) {
        super(parent, "Selecionar Tipo de Conta", true);
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridLayout(5, 1, 10, 10));
        setSize(300, 250);

        add(new JLabel("Selecione o tipo de conta:", SwingConstants.CENTER));

        JButton contaCorrenteBtn = new JButton("Conta Corrente");
        JButton poupancaBtn = new JButton("Poupança");
        JButton rendaFixaBtn = new JButton("Renda Fixa");
        JButton investimentoBtn = new JButton("Investimento");

        contaCorrenteBtn.addActionListener(e -> {
            selectedType = "CORRENTE";
            dispose();
        });

        poupancaBtn.addActionListener(e -> {
            selectedType = "POUPANCA";
            dispose();
        });

        rendaFixaBtn.addActionListener(e -> {
            selectedType = "RENDA_FIXA";
            dispose();
        });

        investimentoBtn.addActionListener(e -> {
            selectedType = "INVESTIMENTO";
            dispose();
        });

        add(contaCorrenteBtn);
        add(poupancaBtn);
        add(rendaFixaBtn);
        add(investimentoBtn);

        setLocationRelativeTo(getParent());
    }

    public String getSelectedType() {
        return selectedType;
    }
}