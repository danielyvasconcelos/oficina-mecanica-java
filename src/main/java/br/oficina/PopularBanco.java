package br.oficina;

import br.oficina.modelo.Cliente;
import br.oficina.modelo.Produto;
import br.oficina.modelo.Venda;
import br.oficina.servico.ClienteServico;
import br.oficina.servico.ProdutoServico;
import br.oficina.servico.VendaServico;

import java.time.LocalDateTime;
import java.util.Arrays;

public class PopularBanco {
    private static final ClienteServico clienteServico = new ClienteServico();
    private static final ProdutoServico produtoServico = new ProdutoServico();
    private static final VendaServico vendaServico = new VendaServico();

    public static void main(String[] args) {
        System.out.println("=== INICIANDO POPULAÇÃO DO BANCO ===");
        
        try {
            // Criar clientes
            System.out.println("\n1. CADASTRANDO CLIENTES...");
            Cliente cliente1 = clienteServico.cadastrar(new Cliente("João Silva", "(11) 99999-1111", "joao@email.com", "Rua A, 123"));
            System.out.println("✓ Cliente 1 cadastrado: " + cliente1.getNome() + " [ID: " + cliente1.getId() + "]");
            
            Cliente cliente2 = clienteServico.cadastrar(new Cliente("Maria Santos", "(11) 99999-2222", "maria@email.com", "Rua B, 456"));
            System.out.println("✓ Cliente 2 cadastrado: " + cliente2.getNome() + " [ID: " + cliente2.getId() + "]");
            
            Cliente cliente3 = clienteServico.cadastrar(new Cliente("Pedro Costa", "(11) 99999-3333", "pedro@email.com", "Rua C, 789"));
            System.out.println("✓ Cliente 3 cadastrado: " + cliente3.getNome() + " [ID: " + cliente3.getId() + "]");
            
            // Criar produtos
            System.out.println("\n2. CADASTRANDO PRODUTOS...");
            Produto produto1 = produtoServico.cadastrar(new Produto("Óleo Motor 5W30", "Óleo sintético para motor", 45.90, 50, "Lubrificantes"));
            System.out.println("✓ Produto 1 cadastrado: " + produto1.getNome() + " - R$" + produto1.getPreco() + " [ID: " + produto1.getId() + "]");
            
            Produto produto2 = produtoServico.cadastrar(new Produto("Filtro de Ar", "Filtro de ar para veículos", 25.50, 30, "Filtros"));
            System.out.println("✓ Produto 2 cadastrado: " + produto2.getNome() + " - R$" + produto2.getPreco() + " [ID: " + produto2.getId() + "]");
            
            Produto produto3 = produtoServico.cadastrar(new Produto("Pastilha de Freio", "Pastilha de freio dianteira", 89.90, 20, "Freios"));
            System.out.println("✓ Produto 3 cadastrado: " + produto3.getNome() + " - R$" + produto3.getPreco() + " [ID: " + produto3.getId() + "]");
            
            // Criar vendas
            System.out.println("\n3. PROCESSANDO VENDAS...");
            
            System.out.println("Processando venda 1: João Silva comprando 2x Óleo Motor...");
            Venda.ItemVenda item1 = new Venda.ItemVenda(produto1.getId(), produto1.getNome(), 2, produto1.getPreco());
            Venda venda1 = new Venda(cliente1.getId(), Arrays.asList(item1), 91.80, LocalDateTime.now().minusDays(5));
            Venda vendaProcessada1 = vendaServico.processar(venda1);
            System.out.println("✓ Venda 1 processada: R$" + vendaProcessada1.getValorTotal() + " [ID: " + vendaProcessada1.getId() + "]");
            
            System.out.println("Processando venda 2: Maria Santos comprando 1x Filtro de Ar...");
            Venda.ItemVenda item2 = new Venda.ItemVenda(produto2.getId(), produto2.getNome(), 1, produto2.getPreco());
            Venda venda2 = new Venda(cliente2.getId(), Arrays.asList(item2), 25.50, LocalDateTime.now().minusDays(3));
            Venda vendaProcessada2 = vendaServico.processar(venda2);
            System.out.println("✓ Venda 2 processada: R$" + vendaProcessada2.getValorTotal() + " [ID: " + vendaProcessada2.getId() + "]");
            
            System.out.println("Processando venda 3: Pedro Costa comprando 1x Pastilha de Freio...");
            Venda.ItemVenda item3 = new Venda.ItemVenda(produto3.getId(), produto3.getNome(), 1, produto3.getPreco());
            Venda venda3 = new Venda(cliente3.getId(), Arrays.asList(item3), 89.90, LocalDateTime.now().minusDays(1));
            Venda vendaProcessada3 = vendaServico.processar(venda3);
            System.out.println("✓ Venda 3 processada: R$" + vendaProcessada3.getValorTotal() + " [ID: " + vendaProcessada3.getId() + "]");
            
            System.out.println("\n=== POPULAÇÃO CONCLUÍDA COM SUCESSO! ===");
            System.out.println("✓ 3 clientes cadastrados");
            System.out.println("✓ 3 produtos cadastrados");
            System.out.println("✓ 3 vendas realizadas");
            
        } catch (Exception e) {
            System.err.println("❌ ERRO durante a população: " + e.getMessage());
            e.printStackTrace();
        }
    }
}