package br.oficina.modelo;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.util.List;

public class Venda {
    private ObjectId id;
    private ObjectId clienteId;
    private List<ItemVenda> itens;
    private double valorTotal;
    private LocalDateTime dataVenda;
    
    public Venda() {}
    
    public Venda(ObjectId clienteId, List<ItemVenda> itens, double valorTotal, LocalDateTime dataVenda) {
        this.clienteId = clienteId;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }
    
    public ObjectId getId() {
        return id;
    }
    
    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public ObjectId getClienteId() {
        return clienteId;
    }
    
    public void setClienteId(ObjectId clienteId) {
        this.clienteId = clienteId;
    }
    
    public List<ItemVenda> getItens() {
        return itens;
    }
    
    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public LocalDateTime getDataVenda() {
        return dataVenda;
    }
    
    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
    
    public static class ItemVenda {
        private ObjectId produtoId;
        private String nomeProduto;
        private int quantidade;
        private double precoUnitario;
        
        public ItemVenda() {}
        
        public ItemVenda(ObjectId produtoId, String nomeProduto, int quantidade, double precoUnitario) {
            this.produtoId = produtoId;
            this.nomeProduto = nomeProduto;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }
        
        public ObjectId getProdutoId() {
            return produtoId;
        }
        
        public void setProdutoId(ObjectId produtoId) {
            this.produtoId = produtoId;
        }
        
        public String getNomeProduto() {
            return nomeProduto;
        }
        
        public void setNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
        }
        
        public int getQuantidade() {
            return quantidade;
        }
        
        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }
        
        public double getPrecoUnitario() {
            return precoUnitario;
        }
        
        public void setPrecoUnitario(double precoUnitario) {
            this.precoUnitario = precoUnitario;
        }
    }
}