package controller;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import model.ContaPoupanca;
import model.RendaFixa;

import java.util.*;

public class BancoController {
    private List<Cliente> clientes;
    private static BancoController instance;

    private BancoController() {
        clientes = new ArrayList<>();
    }

    public static BancoController getInstance() {
        if (instance == null) {
            instance = new BancoController();
        }
        return instance;
    }

    public Cliente cadastrarCliente(String nome, String cpf, String senha) {
        Cliente cliente = new Cliente(nome, cpf, senha);
        clientes.add(cliente);
        return cliente;
    }

    public void criarContaCorrente(Cliente cliente, double limite) {
        ContaCorrente conta = new ContaCorrente(cliente, limite);
        cliente.adicionarConta(conta);
    }

    public void criarContaPoupanca(Cliente cliente) {
        ContaPoupanca conta = new ContaPoupanca(cliente);
        cliente.adicionarConta(conta);
    }

    public void criarContaInvestimento(Cliente cliente) {
        ContaInvestimento conta = new ContaInvestimento(cliente);
        cliente.adicionarConta(conta);
    }

    public void criarContaRendaFixa(Cliente cliente) {
        RendaFixa conta = new RendaFixa(cliente);
        cliente.adicionarConta(conta);
    }

    public void criarContaPorTipo(Cliente cliente, String tipo) {
        switch (tipo) {
            case "CORRENTE":
                criarContaCorrente(cliente, 1000);
                break;
            case "POUPANCA":
                criarContaPoupanca(cliente);
                break;
            case "RENDA_FIXA":
                criarContaRendaFixa(cliente);
                break;
            case "INVESTIMENTO":
                criarContaInvestimento(cliente);
                break;
        }
    }

    public Cliente buscarCliente(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public boolean autenticarCliente(String cpf, String senha) {
        Cliente cliente = buscarCliente(cpf);
        return cliente != null && cliente.getSenha().equals(senha);
    }

    public Cliente getClienteMaiorSaldo() {
        return clientes.stream()
                .max(Comparator.comparingDouble(Cliente::getSaldoTotal))
                .orElse(null);
    }

    public Cliente getClienteMenorSaldo() {
        return clientes.stream()
                .min(Comparator.comparingDouble(Cliente::getSaldoTotal))
                .orElse(null);
    }

    public double getTotalPorTipoConta(String tipoConta) {
        return clientes.stream()
                .flatMap(c -> c.getContas().stream())
                .filter(c -> c.getClass().getSimpleName().equals(tipoConta))
                .mapToDouble(Conta::getSaldo)
                .sum();
    }

    public int getTotalClientes() {
        return clientes.size();
    }
}