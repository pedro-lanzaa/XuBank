package model;

public class RendaFixa extends Conta {
    private static final double TAXA_MENSAL = 20.0;
    private static final double IMPOSTO = 0.15;

    public RendaFixa(Cliente cliente) {
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
        double taxa = Math.random() * (0.85 - 0.50) + 0.50;
        double rendimento = saldo * (taxa / 100);
        saldo += rendimento - TAXA_MENSAL;
        extrato.add(String.format("Rendimento mensal: +R$%.2f (Taxa: R$%.2f)", rendimento, TAXA_MENSAL));
    }
}