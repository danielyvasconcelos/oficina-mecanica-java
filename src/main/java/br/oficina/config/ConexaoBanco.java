package br.oficina.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexaoBanco {
    private static MongoClient cliente;
    private static MongoDatabase bancoDados;
    
    private static final String URL_CONEXAO = "mongodb://localhost:27017";
    private static final String NOME_BANCO = "oficina_mecanica";
    
    public static MongoDatabase obterBancoDados() {
        if (bancoDados == null) {
            conectar();
        }
        return bancoDados;
    }
    
    private static void conectar() {
        try {
            cliente = MongoClients.create(URL_CONEXAO);
            bancoDados = cliente.getDatabase(NOME_BANCO);
            System.out.println("Conectado ao MongoDB com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar com MongoDB: " + e.getMessage());
        }
    }
    
    public static void fecharConexao() {
        if (cliente != null) {
            cliente.close();
            System.out.println("Conexão com MongoDB fechada.");
        }
    }
}