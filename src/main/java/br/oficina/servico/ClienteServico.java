package br.oficina.servico;

import br.oficina.modelo.Cliente;
import br.oficina.repositorio.ClienteRepositorio;
import org.bson.types.ObjectId;

import java.util.List;

public class ClienteServico {
    private final ClienteRepositorio repositorio;
    
    public ClienteServico() {
        this.repositorio = new ClienteRepositorio();
    }
    
    public Cliente cadastrar(Cliente cliente) {
        System.out.println("[ClienteServico] Validando cliente: " + cliente.getNome());
        validarCliente(cliente);
        
        System.out.println("[ClienteServico] Verificando email duplicado...");
        if (repositorio.buscarPorEmail(cliente.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        
        System.out.println("[ClienteServico] Salvando cliente no banco...");
        Cliente clienteSalvo = repositorio.salvar(cliente);
        System.out.println("[ClienteServico] Cliente salvo com ID: " + clienteSalvo.getId());
        return clienteSalvo;
    }
    
    public Cliente buscarPorId(ObjectId id) {
        Cliente cliente = repositorio.buscarPorId(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return cliente;
    }
    
    public List<Cliente> listarTodos() {
        return repositorio.buscarTodos();
    }
    
    public Cliente atualizar(Cliente cliente) {
        validarCliente(cliente);
        
        Cliente existente = repositorio.buscarPorEmail(cliente.getEmail());
        if (existente != null && !existente.getId().equals(cliente.getId())) {
            throw new IllegalArgumentException("Email já cadastrado para outro cliente");
        }
        
        return repositorio.atualizar(cliente);
    }
    
    public void excluir(ObjectId id) {
        if (!repositorio.deletar(id)) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }
    
    private void validarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (cliente.getEmail() == null || !cliente.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone é obrigatório");
        }
    }
}