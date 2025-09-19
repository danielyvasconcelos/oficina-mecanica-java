// Navegação
document.addEventListener('DOMContentLoaded', function() {
    const navLinks = document.querySelectorAll('.nav-link');
    const pages = document.querySelectorAll('.page');

    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            
            // Remove classe ativa de todos os links e páginas
            navLinks.forEach(l => l.classList.remove('active'));
            pages.forEach(p => p.classList.remove('active'));
            
            // Adiciona classe ativa ao link clicado
            this.classList.add('active');
            
            // Mostra a página correspondente
            const pageId = this.getAttribute('data-page');
            document.getElementById(pageId).classList.add('active');
            
            // Carrega dados ao trocar de página
            if (pageId === 'dashboard') {
                carregarDashboard();
            } else if (pageId === 'clientes') {
                carregarClientes();
            } else if (pageId === 'produtos') {
                carregarProdutos();
            } else if (pageId === 'vendas') {
                carregarVendas();
            }
        });
    });
    
    // Carrega dashboard ao carregar a página
    carregarDashboard();
});

// Funções da API
async function carregarDashboard() {
    try {
        const response = await fetch('/api/dashboard');
        const data = await response.json();
        
        document.getElementById('total-clientes').textContent = data.totalClientes;
        document.getElementById('total-produtos').textContent = data.totalProdutos;
        document.getElementById('total-vendas').textContent = data.totalVendas;
        document.getElementById('faturamento-total').textContent = `R$ ${data.faturamentoTotal.toFixed(2)}`;
        
        console.log('Dashboard carregado:', data);
    } catch (error) {
        console.error('Erro ao carregar dashboard:', error);
    }
}

async function carregarClientes() {
    try {
        const response = await fetch('/api/clientes');
        const clientes = await response.json();
        
        const tbody = document.querySelector('#clientes tbody');
        tbody.innerHTML = '';
        
        clientes.forEach(cliente => {
            const row = tbody.insertRow();
            row.innerHTML = `
                <td>${cliente.nome}</td>
                <td>${cliente.email}</td>
                <td>${cliente.telefone}</td>
                <td>${cliente.endereco}</td>
            `;
        });
        
        console.log('Clientes carregados:', clientes);
    } catch (error) {
        console.error('Erro ao carregar clientes:', error);
    }
}

async function carregarProdutos() {
    try {
        const response = await fetch('/api/produtos');
        const produtos = await response.json();
        
        const tbody = document.querySelector('#produtos tbody');
        tbody.innerHTML = '';
        
        produtos.forEach(produto => {
            const row = tbody.insertRow();
            row.innerHTML = `
                <td>${produto.nome}</td>
                <td>${produto.categoria}</td>
                <td>R$ ${produto.preco.toFixed(2)}</td>
                <td>${produto.quantidadeEstoque}</td>
            `;
        });
        
        console.log('Produtos carregados:', produtos);
    } catch (error) {
        console.error('Erro ao carregar produtos:', error);
    }
}

async function carregarVendas() {
    try {
        const response = await fetch('/api/vendas');
        const vendas = await response.json();
        
        const tbody = document.querySelector('#vendas tbody');
        tbody.innerHTML = '';
        
        vendas.forEach(venda => {
            const row = tbody.insertRow();
            const dataVenda = new Date(venda.dataVenda).toLocaleDateString('pt-BR');
            row.innerHTML = `
                <td>${dataVenda}</td>
                <td>Cliente ID: ${venda.clienteId}</td>
                <td>${venda.itens.length} item(s)</td>
                <td>R$ ${venda.valorTotal.toFixed(2)}</td>
            `;
        });
        
        console.log('Vendas carregadas:', vendas);
    } catch (error) {
        console.error('Erro ao carregar vendas:', error);
    }
}

// Funções de envio de formulário
async function cadastrarCliente(event) {
    event.preventDefault();
    
    const formData = new FormData(event.target);
    const cliente = {
        nome: formData.get('nome'),
        email: formData.get('email'),
        telefone: formData.get('telefone'),
        endereco: formData.get('endereco')
    };
    
    try {
        const response = await fetch('/api/clientes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cliente)
        });
        
        if (response.ok) {
            alert('Cliente cadastrado com sucesso!');
            clearForm('cliente-form');
            carregarClientes();
        } else {
            alert('Erro ao cadastrar cliente');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao cadastrar cliente');
    }
}

async function cadastrarProduto(event) {
    event.preventDefault();
    
    const formData = new FormData(event.target);
    const produto = {
        nome: formData.get('nome'),
        descricao: formData.get('descricao'),
        preco: parseFloat(formData.get('preco')),
        quantidadeEstoque: parseInt(formData.get('quantidadeEstoque')),
        categoria: formData.get('categoria')
    };
    
    try {
        const response = await fetch('/api/produtos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(produto)
        });
        
        if (response.ok) {
            alert('Produto cadastrado com sucesso!');
            clearForm('produto-form');
            carregarProdutos();
        } else {
            alert('Erro ao cadastrar produto');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao cadastrar produto');
    }
}

// Função para limpar formulário
function clearForm(formId) {
    document.getElementById(formId).reset();
}