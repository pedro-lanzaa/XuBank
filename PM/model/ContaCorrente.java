package model;

public class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(Cliente cliente, double limite) {
        super(cliente);
        this.limite = limite;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= (saldo + limite)) {
            saldo -= valor;
            extrato.add("Saque: -R$" + valor);
            return true;
        }
        return false;
    }

    @Override
    public void depositar(double valor) {
        if (saldo < 0) {
            double taxa = Math.abs(saldo) * 0.03 + 10;
            saldo += (valor - taxa);
            extrato.add("Depósito: +R$" + valor + " (Taxa: R$" + taxa + ")");
        } else {
            saldo += valor;
            extrato.add("Depósito: +R$" + valor);
        }
    }

    @Override
    public void aplicarRendimento() {
        // ,sem rendimento
    }
}