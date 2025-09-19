package br.oficina.modelo;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteComDadosValidos() {
        // Arrange & Act
        Cliente cliente = new Cliente("João Silva", "(11) 99999-1111", "joao@email.com", "Rua A, 123");
        
        // Assert
        assertEquals("João Silva", cliente.getNome());
        assertEquals("(11) 99999-1111", cliente.getTelefone());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals("Rua A, 123", cliente.getEndereco());
        assertNull(cliente.getId());
    }

    @Test
    void deveDefinirEObterIdCorretamente() {
        // Arrange
        Cliente cliente = new Cliente();
        ObjectId id = new ObjectId();
        
        // Act
        cliente.setId(id);
        
        // Assert
        assertEquals(id, cliente.getId());
    }

    @Test
    void deveDefinirEObterNomeCorretamente() {
        // Arrange
        Cliente cliente = new Cliente();
        
        // Act
        cliente.setNome("Maria Santos");
        
        // Assert
        assertEquals("Maria Santos", cliente.getNome());
    }
}