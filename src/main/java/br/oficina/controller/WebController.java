package br.oficina.controller;

import br.oficina.modelo.Cliente;
import br.oficina.modelo.Produto;
import br.oficina.modelo.Venda;
import br.oficina.servico.ClienteServico;
import br.oficina.servico.ProdutoServico;
import br.oficina.servico.VendaServico;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class WebController {
    
    private final ClienteServico clienteServico = new ClienteServico();
    private final ProdutoServico produtoServico = new ProdutoServico();
    private final VendaServico vendaServico = new VendaServico();
    

    
    // APIs REST para Clientes
    @GetMapping("/api/clientes")
    @ResponseBody
    public List<Cliente> listarClientes() {
        return clienteServico.listarTodos();
    }
    
    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente clienteSalvo = clienteServico.cadastrar(cliente);
            return ResponseEntity.ok(clienteSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // APIs REST para Produtos
    @GetMapping("/api/produtos")
    @ResponseBody
    public List<Produto> listarProdutos() {
        return produtoServico.listarTodos();
    }
    
    @PostMapping("/api/produtos")
    @ResponseBody
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        try {
            Produto produtoSalvo = produtoServico.cadastrar(produto);
            return ResponseEntity.ok(produtoSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // APIs REST para Vendas
    @GetMapping("/api/vendas")
    @ResponseBody
    public List<Venda> listarVendas() {
        return vendaServico.listarTodas();
    }
    
    @PostMapping("/api/vendas")
    @ResponseBody
    public ResponseEntity<Venda> processarVenda(@RequestBody Venda venda) {
        try {
            Venda vendaProcessada = vendaServico.processar(venda);
            return ResponseEntity.ok(vendaProcessada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // API para Dashboard
    @GetMapping("/api/dashboard")
    @ResponseBody
    public Map<String, Object> obterDashboard() {
        Map<String, Object> dashboard = new HashMap<>();
        
        List<Cliente> clientes = clienteServico.listarTodos();
        List<Produto> produtos = produtoServico.listarTodos();
        List<Venda> vendas = vendaServico.listarTodas();
        
        dashboard.put("totalClientes", clientes.size());
        dashboard.put("totalProdutos", produtos.size());
        dashboard.put("totalVendas", vendas.size());
        
        double faturamentoTotal = vendas.stream()
            .mapToDouble(Venda::getValorTotal)
            .sum();
        dashboard.put("faturamentoTotal", faturamentoTotal);
        
        dashboard.put("clientes", clientes);
        dashboard.put("produtos", produtos);
        dashboard.put("vendas", vendas);
        
        return dashboard;
    }
}