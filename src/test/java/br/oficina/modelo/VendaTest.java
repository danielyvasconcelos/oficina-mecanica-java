package br.oficina.modelo;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    @Test
    void deveCriarVendaComDadosValidos() {
        // Arrange
        ObjectId clienteId = new ObjectId();
        ObjectId produtoId = new ObjectId();
        Venda.ItemVenda item = new Venda.ItemVenda(produtoId, "Produto Teste", 2, 10.0);
        LocalDateTime dataVenda = LocalDateTime.now();
        
        // Act
        Venda venda = new Venda(clienteId, Arrays.asList(item), 20.0, dataVenda);
        
        // Assert
        assertEquals(clienteId, venda.getClienteId());
        assertEquals(1, venda.getItens().size());
        assertEquals(20.0, venda.getValorTotal());
        assertEquals(dataVenda, venda.getDataVenda());
        assertNull(venda.getId());
    }

    @Test
    void deveCriarItemVendaCorretamente() {
        // Arrange
        ObjectId produtoId = new ObjectId();
        
        // Act
        Venda.ItemVenda item = new Venda.ItemVenda(produtoId, "Óleo Motor", 3, 45.90);
        
        // Assert
        assertEquals(produtoId, item.getProdutoId());
        assertEquals("Óleo Motor", item.getNomeProduto());
        assertEquals(3, item.getQuantidade());
        assertEquals(45.90, item.getPrecoUnitario());
    }

    @Test
    void deveDefinirEObterValorTotalCorretamente() {
        // Arrange
        Venda venda = new Venda();
        
        // Act
        venda.setValorTotal(150.75);
        
        // Assert
        assertEquals(150.75, venda.getValorTotal());
    }

    @Test
    void deveDefinirEObterDataVendaCorretamente() {
        // Arrange
        Venda venda = new Venda();
        LocalDateTime data = LocalDateTime.now();
        
        // Act
        venda.setDataVenda(data);
        
        // Assert
        assertEquals(data, venda.getDataVenda());
    }
}