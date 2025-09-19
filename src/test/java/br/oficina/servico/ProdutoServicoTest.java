package br.oficina.servico;

import br.oficina.modelo.Produto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoServicoTest {

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        // Arrange
        ProdutoServico servico = new ProdutoServico();
        Produto produto = new Produto(null, "Descrição", 10.0, 5, "Categoria");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servico.cadastrar(produto);
        });
        
        assertEquals("Nome é obrigatório", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForNegativo() {
        // Arrange
        ProdutoServico servico = new ProdutoServico();
        Produto produto = new Produto("Produto", "Descrição", -10.0, 5, "Categoria");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servico.cadastrar(produto);
        });
        
        assertEquals("Preço deve ser maior que zero", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoEstoqueForNegativo() {
        // Arrange
        ProdutoServico servico = new ProdutoServico();
        Produto produto = new Produto("Produto", "Descrição", 10.0, -1, "Categoria");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servico.cadastrar(produto);
        });
        
        assertEquals("Quantidade em estoque não pode ser negativa", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaForNula() {
        // Arrange
        ProdutoServico servico = new ProdutoServico();
        Produto produto = new Produto("Produto", "Descrição", 10.0, 5, null);
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servico.cadastrar(produto);
        });
        
        assertEquals("Categoria é obrigatória", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaForVazia() {
        // Arrange
        ProdutoServico servico = new ProdutoServico();
        Produto produto = new Produto("Produto", "Descrição", 10.0, 5, "");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            servico.cadastrar(produto);
        });
        
        assertEquals("Categoria é obrigatória", exception.getMessage());
    }
}