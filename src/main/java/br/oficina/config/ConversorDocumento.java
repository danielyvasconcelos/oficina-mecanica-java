package br.oficina.config;

import br.oficina.modelo.Cliente;
import br.oficina.modelo.Produto;
import br.oficina.modelo.Venda;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConversorDocumento {
    
    public static Document clienteParaDocumento(Cliente cliente) {
        Document doc = new Document();
        if (cliente.getId() != null) {
            doc.append("_id", cliente.getId());
        }
        doc.append("nome", cliente.getNome())
           .append("telefone", cliente.getTelefone())
           .append("email", cliente.getEmail())
           .append("endereco", cliente.getEndereco());
        return doc;
    }
    
    public static Cliente documentoParaCliente(Document doc) {
        Cliente cliente = new Cliente();
        cliente.setId(doc.getObjectId("_id"));
        cliente.setNome(doc.getString("nome"));
        cliente.setTelefone(doc.getString("telefone"));
        cliente.setEmail(doc.getString("email"));
        cliente.setEndereco(doc.getString("endereco"));
        return cliente;
    }
    
    public static Document produtoParaDocumento(Produto produto) {
        Document doc = new Document();
        if (produto.getId() != null) {
            doc.append("_id", produto.getId());
        }
        doc.append("nome", produto.getNome())
           .append("descricao", produto.getDescricao())
           .append("preco", produto.getPreco())
           .append("quantidadeEstoque", produto.getQuantidadeEstoque())
           .append("categoria", produto.getCategoria());
        return doc;
    }
    
    public static Produto documentoParaProduto(Document doc) {
        Produto produto = new Produto();
        produto.setId(doc.getObjectId("_id"));
        produto.setNome(doc.getString("nome"));
        produto.setDescricao(doc.getString("descricao"));
        produto.setPreco(doc.getDouble("preco"));
        produto.setQuantidadeEstoque(doc.getInteger("quantidadeEstoque"));
        produto.setCategoria(doc.getString("categoria"));
        return produto;
    }
    
    public static Document vendaParaDocumento(Venda venda) {
        Document doc = new Document();
        if (venda.getId() != null) {
            doc.append("_id", venda.getId());
        }
        
        List<Document> itensDoc = new ArrayList<>();
        for (Venda.ItemVenda item : venda.getItens()) {
            Document itemDoc = new Document()
                .append("produtoId", item.getProdutoId())
                .append("nomeProduto", item.getNomeProduto())
                .append("quantidade", item.getQuantidade())
                .append("precoUnitario", item.getPrecoUnitario());
            itensDoc.add(itemDoc);
        }
        
        Date dataVenda = Date.from(venda.getDataVenda().atZone(ZoneId.systemDefault()).toInstant());
        
        doc.append("clienteId", venda.getClienteId())
           .append("itens", itensDoc)
           .append("valorTotal", venda.getValorTotal())
           .append("dataVenda", dataVenda);
        return doc;
    }
    
    public static Venda documentoParaVenda(Document doc) {
        Venda venda = new Venda();
        venda.setId(doc.getObjectId("_id"));
        venda.setClienteId(doc.getObjectId("clienteId"));
        venda.setValorTotal(doc.getDouble("valorTotal"));
        
        Date dataVenda = doc.getDate("dataVenda");
        venda.setDataVenda(dataVenda.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        
        List<Document> itensDoc = doc.getList("itens", Document.class);
        List<Venda.ItemVenda> itens = new ArrayList<>();
        for (Document itemDoc : itensDoc) {
            Venda.ItemVenda item = new Venda.ItemVenda();
            item.setProdutoId(itemDoc.getObjectId("produtoId"));
            item.setNomeProduto(itemDoc.getString("nomeProduto"));
            item.setQuantidade(itemDoc.getInteger("quantidade"));
            item.setPrecoUnitario(itemDoc.getDouble("precoUnitario"));
            itens.add(item);
        }
        venda.setItens(itens);
        
        return venda;
    }
}