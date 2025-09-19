package br.oficina.modelo;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void deveCriarProdutoComDadosValidos() {
        // Arrange & Act
        Produto produto = new Produto("Óleo Motor", "Óleo sintético", 45.90, 10, "Lubrificantes");
        
        // Assert
        assertEquals("Óleo Motor", produto.getNome());
        assertEquals("Óleo sintético", produto.getDescricao());
        assertEquals(45.90, produto.getPreco());
        assertEquals(10, produto.getQuantidadeEstoque());
        assertEquals("Lubrificantes", produto.getCategoria());
        assertNull(produto.getId());
    }

    @Test
    void deveDefinirEObterPrecoCorretamente() {
        // Arrange
        Produto produto = new Produto();
        
        // Act
        produto.setPreco(99.99);
        
        // Assert
        assertEquals(99.99, produto.getPreco());
    }

    @Test
    void deveDefinirEObterEstoqueCorretamente() {
        // Arrange
        Produto produto = new Produto();
        
        // Act
        produto.setQuantidadeEstoque(25);
        
        // Assert
        assertEquals(25, produto.getQuantidadeEstoque());
    }
}