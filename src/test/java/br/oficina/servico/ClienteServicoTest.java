package br.oficina.servico;

import br.oficina.modelo.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteServicoTest {

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        // Arrange
        ClienteServico servico = new ClienteServico();
        Cliente cliente = new Cliente(null, "(11) 99999-1111", "teste123@email.com", "Rua A, 123");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servico.cadastrar(cliente);
        });
        
        assertEquals("Nome é obrigatório", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        // Arrange
        ClienteServico servico = new ClienteServico();
        Cliente cliente = new Cliente("", "(11) 99999-1111", "teste456@email.com", "Rua A, 123");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servico.cadastrar(cliente);
        });
        
        assertEquals("Nome é obrigatório", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoEmailForInvalido() {
        // Arrange
        ClienteServico servico = new ClienteServico();
        Cliente cliente = new Cliente("João Silva", "(11) 99999-1111", "email-invalido", "Rua A, 123");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servico.cadastrar(cliente);
        });
        
        assertEquals("Email inválido", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoTelefoneForNulo() {
        // Arrange
        ClienteServico servico = new ClienteServico();
        Cliente cliente = new Cliente("João Silva", null, "joao789@email.com", "Rua A, 123");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servico.cadastrar(cliente);
        });
        
        assertEquals("Telefone é obrigatório", exception.getMessage());
    }
}