package model;

public class ContaInvestimento extends Conta {
    private static final double IMPOSTO = 0.225;

    public ContaInvestimento(Cliente cliente) {
        super(cliente);
    }

    @Override
    public boolean sacar(double valor) {
        double impostoRendimento = saldo * IMPOSTO;
        if (valor <= (saldo - impostoRendimento)) {
            saldo -= (valor + impostoRendimento);
            extrato.add("Saque: -R$" + valor + " (Imposto: R$" + impostoRendimento + ")");
            return true;
        }
        return false;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        extrato.add("Depósito: +R$" + valor);
    }

    @Override
    public void aplicarRendimento() {
        double taxa = Math.random() * (1.50 - (-0.60)) + (-0.60);
        double rendimento = saldo * (taxa / 100);
        double taxaAdministracao = rendimento > 0 ? rendimento * 0.01 : 0;
        saldo += rendimento - taxaAdministracao;
        extrato.add(String.format("Rendimento mensal: %+.2f (Taxa adm: R$%.2f)", rendimento, taxaAdministracao));
    }
}