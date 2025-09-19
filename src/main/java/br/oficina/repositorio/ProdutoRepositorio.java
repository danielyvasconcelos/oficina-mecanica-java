package br.oficina.repositorio;

import br.oficina.config.ConexaoBanco;
import br.oficina.config.ConversorDocumento;
import br.oficina.modelo.Produto;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositorio {
    private final MongoCollection<Document> colecao;
    
    public ProdutoRepositorio() {
        this.colecao = ConexaoBanco.obterBancoDados().getCollection("produtos");
    }
    
    public Produto salvar(Produto produto) {
        System.out.println("[ProdutoRepositorio] Convertendo produto para documento...");
        Document doc = ConversorDocumento.produtoParaDocumento(produto);
        System.out.println("[ProdutoRepositorio] Inserindo no MongoDB...");
        colecao.insertOne(doc);
        produto.setId(doc.getObjectId("_id"));
        System.out.println("[ProdutoRepositorio] Produto inserido com ID: " + produto.getId());
        return produto;
    }
    
    public Produto buscarPorId(ObjectId id) {
        Document doc = colecao.find(Filters.eq("_id", id)).first();
        return doc != null ? ConversorDocumento.documentoParaProduto(doc) : null;
    }
    
    public List<Produto> buscarTodos() {
        List<Produto> produtos = new ArrayList<>();
        for (Document doc : colecao.find()) {
            produtos.add(ConversorDocumento.documentoParaProduto(doc));
        }
        return produtos;
    }
    
    public Produto atualizar(Produto produto) {
        Document doc = ConversorDocumento.produtoParaDocumento(produto);
        colecao.replaceOne(Filters.eq("_id", produto.getId()), doc);
        return produto;
    }
    
    public boolean deletar(ObjectId id) {
        return colecao.deleteOne(Filters.eq("_id", id)).getDeletedCount() > 0;
    }
    
    public boolean atualizarEstoque(ObjectId id, int novaQuantidade) {
        return colecao.updateOne(
            Filters.eq("_id", id),
            Updates.set("quantidadeEstoque", novaQuantidade)
        ).getModifiedCount() > 0;
    }
    
    public List<Produto> buscarPorCategoria(String categoria) {
        List<Produto> produtos = new ArrayList<>();
        for (Document doc : colecao.find(Filters.eq("categoria", categoria))) {
            produtos.add(ConversorDocumento.documentoParaProduto(doc));
        }
        return produtos;
    }
}