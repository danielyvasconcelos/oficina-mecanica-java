package br.oficina.repositorio;

import br.oficina.config.ConexaoBanco;
import br.oficina.config.ConversorDocumento;
import br.oficina.modelo.Venda;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class VendaRepositorio {
    private final MongoCollection<Document> colecao;
    
    public VendaRepositorio() {
        this.colecao = ConexaoBanco.obterBancoDados().getCollection("vendas");
    }
    
    public Venda salvar(Venda venda) {
        Document doc = ConversorDocumento.vendaParaDocumento(venda);
        colecao.insertOne(doc);
        venda.setId(doc.getObjectId("_id"));
        return venda;
    }
    
    public Venda buscarPorId(ObjectId id) {
        Document doc = colecao.find(Filters.eq("_id", id)).first();
        return doc != null ? ConversorDocumento.documentoParaVenda(doc) : null;
    }
    
    public List<Venda> buscarTodas() {
        List<Venda> vendas = new ArrayList<>();
        for (Document doc : colecao.find()) {
            vendas.add(ConversorDocumento.documentoParaVenda(doc));
        }
        return vendas;
    }
    
    public List<Venda> buscarPorCliente(ObjectId clienteId) {
        List<Venda> vendas = new ArrayList<>();
        for (Document doc : colecao.find(Filters.eq("clienteId", clienteId))) {
            vendas.add(ConversorDocumento.documentoParaVenda(doc));
        }
        return vendas;
    }
    
    public boolean deletar(ObjectId id) {
        return colecao.deleteOne(Filters.eq("_id", id)).getDeletedCount() > 0;
    }
}