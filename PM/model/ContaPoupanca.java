package model;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            extrato.add("Saque: -R$" + valor);
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
        double rendimento = saldo * 0.006;
        saldo += rendimento;
        extrato.add("Rendimento mensal: +R$" + rendimento);
    }
}