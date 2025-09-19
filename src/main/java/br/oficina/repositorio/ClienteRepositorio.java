package br.oficina.repositorio;

import br.oficina.config.ConexaoBanco;
import br.oficina.config.ConversorDocumento;
import br.oficina.modelo.Cliente;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {
    private final MongoCollection<Document> colecao;
    
    public ClienteRepositorio() {
        this.colecao = ConexaoBanco.obterBancoDados().getCollection("clientes");
    }
    
    public Cliente salvar(Cliente cliente) {
        Document doc = ConversorDocumento.clienteParaDocumento(cliente);
        colecao.insertOne(doc);
        cliente.setId(doc.getObjectId("_id"));
        return cliente;
    }
    
    public Cliente buscarPorId(ObjectId id) {
        Document doc = colecao.find(Filters.eq("_id", id)).first();
        return doc != null ? ConversorDocumento.documentoParaCliente(doc) : null;
    }
    
    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        for (Document doc : colecao.find()) {
            clientes.add(ConversorDocumento.documentoParaCliente(doc));
        }
        return clientes;
    }
    
    public Cliente atualizar(Cliente cliente) {
        Document doc = ConversorDocumento.clienteParaDocumento(cliente);
        colecao.replaceOne(Filters.eq("_id", cliente.getId()), doc);
        return cliente;
    }
    
    public boolean deletar(ObjectId id) {
        return colecao.deleteOne(Filters.eq("_id", id)).getDeletedCount() > 0;
    }
    
    public Cliente buscarPorEmail(String email) {
        Document doc = colecao.find(Filters.eq("email", email)).first();
        return doc != null ? ConversorDocumento.documentoParaCliente(doc) : null;
    }
}