package br.oficina.servico;

import br.oficina.modelo.Produto;
import br.oficina.repositorio.ProdutoRepositorio;
import org.bson.types.ObjectId;

import java.util.List;

public class ProdutoServico {
    private final ProdutoRepositorio repositorio;
    
    public ProdutoServico() {
        this.repositorio = new ProdutoRepositorio();
    }
    
    public Produto cadastrar(Produto produto) {
        System.out.println("[ProdutoServico] Validando produto: " + produto.getNome());
        validarProduto(produto);
        System.out.println("[ProdutoServico] Salvando produto no banco...");
        Produto produtoSalvo = repositorio.salvar(produto);
        System.out.println("[ProdutoServico] Produto salvo com ID: " + produtoSalvo.getId());
        return produtoSalvo;
    }
    
    public Produto buscarPorId(ObjectId id) {
        Produto produto = repositorio.buscarPorId(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        return produto;
    }
    
    public List<Produto> listarTodos() {
        return repositorio.buscarTodos();
    }
    
    public List<Produto> buscarPorCategoria(String categoria) {
        return repositorio.buscarPorCategoria(categoria);
    }
    
    public Produto atualizar(Produto produto) {
        validarProduto(produto);
        return repositorio.atualizar(produto);
    }
    
    public void excluir(ObjectId id) {
        if (!repositorio.deletar(id)) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
    }
    
    public boolean verificarEstoque(ObjectId id, int quantidade) {
        Produto produto = buscarPorId(id);
        return produto.getQuantidadeEstoque() >= quantidade;
    }
    
    public void reduzirEstoque(ObjectId id, int quantidade) {
        Produto produto = buscarPorId(id);
        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }
        
        int novoEstoque = produto.getQuantidadeEstoque() - quantidade;
        repositorio.atualizarEstoque(id, novoEstoque);
    }
    
    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        if (produto.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa");
        }
        if (produto.getCategoria() == null || produto.getCategoria().trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria é obrigatória");
        }
    }
}