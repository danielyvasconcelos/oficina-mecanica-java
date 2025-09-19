package br.oficina;

import br.oficina.config.ConexaoBanco;
import br.oficina.modelo.Cliente;
import br.oficina.modelo.Produto;
import br.oficina.modelo.Venda;
import br.oficina.servico.ClienteServico;
import br.oficina.servico.ProdutoServico;
import br.oficina.servico.VendaServico;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClienteServico clienteServico = new ClienteServico();
    private static final ProdutoServico produtoServico = new ProdutoServico();
    private static final VendaServico vendaServico = new VendaServico();

    public static void main(String[] args) {
        System.out.println("=== Sistema Oficina Mecânica ===");
        
        try {
            while (true) {
                mostrarMenu();
                int opcao = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcao) {
                    case 1 -> cadastrarCliente();
                    case 2 -> cadastrarProduto();
                    case 3 -> realizarVenda();
                    case 4 -> listarClientes();
                    case 5 -> listarProdutos();
                    case 0 -> {
                        System.out.println("Encerrando...");
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            ConexaoBanco.fecharConexao();
        }
    }
    
    private static void mostrarMenu() {
        System.out.println("\n1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Produto");
        System.out.println("3 - Realizar Venda");
        System.out.println("4 - Listar Clientes");
        System.out.println("5 - Listar Produtos");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }
    
    private static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        try {
            Cliente cliente = clienteServico.cadastrar(new Cliente(nome, telefone, email, endereco));
            System.out.println("Cliente cadastrado: " + cliente.getId());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void cadastrarProduto() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        
        try {
            Produto produto = produtoServico.cadastrar(new Produto(nome, descricao, preco, quantidade, categoria));
            System.out.println("Produto cadastrado: " + produto.getId());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void realizarVenda() {
        System.out.println("Funcionalidade de venda - implementar conforme necessidade");
    }
    
    private static void listarClientes() {
        clienteServico.listarTodos().forEach(c -> 
            System.out.println(c.getId() + " - " + c.getNome() + " (" + c.getEmail() + ")")
        );
    }
    
    private static void listarProdutos() {
        produtoServico.listarTodos().forEach(p -> 
            System.out.println(p.getId() + " - " + p.getNome() + " - R$" + p.getPreco() + " (Estoque: " + p.getQuantidadeEstoque() + ")")
        );
    }
}