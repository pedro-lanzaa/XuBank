package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    protected double saldo;
    protected Cliente cliente;
    protected List<String> extrato;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
    }

    public abstract boolean sacar(double valor);

    public abstract void depositar(double valor);

    public abstract void aplicarRendimento();

    public double getSaldo() {
        return saldo;
    }

    public List<String> getExtrato() {
        return extrato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        String tipo = this.getClass().getSimpleName();
        return String.format("%s - Saldo: R$%.2f", tipo, saldo);
    }
}