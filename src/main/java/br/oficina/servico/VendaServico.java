package br.oficina.servico;

import br.oficina.modelo.Produto;
import br.oficina.modelo.Venda;
import br.oficina.repositorio.VendaRepositorio;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public class VendaServico {
    private final VendaRepositorio repositorio;
    private final ProdutoServico produtoServico;
    private final ClienteServico clienteServico;
    
    public VendaServico() {
        this.repositorio = new VendaRepositorio();
        this.produtoServico = new ProdutoServico();
        this.clienteServico = new ClienteServico();
    }
    
    public Venda processar(Venda venda) {
        System.out.println("[VendaServico] Iniciando processamento da venda...");
        validarVenda(venda);
        
        // Verificar se cliente existe
        System.out.println("[VendaServico] Verificando se cliente existe: " + venda.getClienteId());
        clienteServico.buscarPorId(venda.getClienteId());
        
        // Verificar estoque e calcular total
        System.out.println("[VendaServico] Verificando estoque e calculando total...");
        double valorTotal = 0;
        for (Venda.ItemVenda item : venda.getItens()) {
            Produto produto = produtoServico.buscarPorId(item.getProdutoId());
            System.out.println("[VendaServico] Verificando produto: " + produto.getNome() + " (Qtd: " + item.getQuantidade() + ")");
            
            if (!produtoServico.verificarEstoque(item.getProdutoId(), item.getQuantidade())) {
                throw new IllegalArgumentException("Estoque insuficiente para: " + produto.getNome());
            }
            
            item.setNomeProduto(produto.getNome());
            item.setPrecoUnitario(produto.getPreco());
            valorTotal += item.getQuantidade() * item.getPrecoUnitario();
        }
        
        venda.setValorTotal(valorTotal);
        venda.setDataVenda(LocalDateTime.now());
        System.out.println("[VendaServico] Valor total calculado: R$" + valorTotal);
        
        // Salvar venda
        System.out.println("[VendaServico] Salvando venda no banco...");
        Venda vendaSalva = repositorio.salvar(venda);
        
        // Reduzir estoque
        System.out.println("[VendaServico] Reduzindo estoque dos produtos...");
        for (Venda.ItemVenda item : venda.getItens()) {
            produtoServico.reduzirEstoque(item.getProdutoId(), item.getQuantidade());
        }
        
        System.out.println("[VendaServico] Venda processada com sucesso! ID: " + vendaSalva.getId());
        return vendaSalva;
    }
    
    public Venda buscarPorId(ObjectId id) {
        Venda venda = repositorio.buscarPorId(id);
        if (venda == null) {
            throw new IllegalArgumentException("Venda não encontrada");
        }
        return venda;
    }
    
    public List<Venda> listarTodas() {
        return repositorio.buscarTodas();
    }
    
    public List<Venda> buscarPorCliente(ObjectId clienteId) {
        return repositorio.buscarPorCliente(clienteId);
    }
    
    private void validarVenda(Venda venda) {
        if (venda.getClienteId() == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }
        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("Venda deve ter pelo menos um item");
        }
        
        for (Venda.ItemVenda item : venda.getItens()) {
            if (item.getProdutoId() == null) {
                throw new IllegalArgumentException("Produto é obrigatório");
            }
            if (item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade deve ser maior que zero");
            }
        }
    }
}