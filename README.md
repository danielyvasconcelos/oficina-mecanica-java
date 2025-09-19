# Sistema de Gestão para Oficina Mecânica

## 📋 Sobre o Projeto

Este projeto foi desenvolvido como parte da disciplina **Banco de Dados II** do curso de **Sistemas para Internet**. 

### Requisito Original da Disciplina:
> *"Você deve montar uma aplicação Java com banco de dados MongoDB para controlar uma oficina mecânica. A sua aplicação deve contemplar informações de clientes, vendas e produtos. Você deve popular as coleções com pelo menos 3 documentos cada uma."*

Porém, resolvi criar uma aplicação mais completa (ainda que básica) para praticar meus conhecimentos e demonstrar uma implementação mais robusta do sistema.

## 🎥 Demonstração da Aplicação

![Demonstração do Sistema](https://github.com/user-attachments/assets/ee8b01a3-ce41-457a-980e-9f9438dc5839)

*Demonstração completa das funcionalidades da aplicação*

**📹 [Ver vídeo completo no Imgur](https://imgur.com/a/oficina-mec-nica-dezm1bL)**

## 🚀 Funcionalidades Implementadas

### ✅ Requisitos Atendidos:
- **Java** com **MongoDB**
- **Gestão de Clientes** (CRUD completo)
- **Gestão de Produtos** (CRUD completo + controle de estoque)
- **Gestão de Vendas** (processamento com validações)
- **População automática** com 3+ documentos por coleção

### 🎯 Funcionalidades Extras:
- **Interface Web** responsiva e moderna
- **Dashboard** com estatísticas em tempo real
- **APIs REST** para integração frontend-backend
- **Validações** de dados e regras de negócio
- **Logs detalhados** para debug
- **Testes unitários** com JUnit
- **Controle de estoque** automático
- **Arquitetura em camadas** (Model, Repository, Service, Controller)

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.2.0**
- **MongoDB 8.0.13**
- **Maven** (gerenciamento de dependências)
- **JUnit 5** (testes unitários)
- **HTML5, CSS3, JavaScript** (frontend)
- **MongoDB Compass** (interface de banco)

## 📁 Estrutura do Projeto

```
src/
├── main/java/br/oficina/
│   ├── config/          # Configurações (MongoDB, Conversores)
│   ├── controller/      # Controllers REST e Web
│   ├── modelo/          # Entidades (Cliente, Produto, Venda)
│   ├── repositorio/     # Camada de acesso a dados
│   ├── servico/         # Regras de negócio
│   ├── Main.java        # Interface linha de comando
│   ├── OficinaApplication.java  # Aplicação Spring Boot
│   └── PopularBanco.java        # Script de população inicial
├── main/resources/
│   ├── static/          # Arquivos web (HTML, CSS, JS)
│   └── application.properties
└── test/java/           # Testes unitários
```

## 🚀 Como Executar

### Pré-requisitos:
- Java 21+
- MongoDB instalado e rodando
- IDE (IntelliJ IDEA recomendado)

### Passos:

1. **Clone o repositório**
```bash
git clone [seu-repositorio]
cd oficina-mecanica-java
```

2. **Execute a população do banco**
```bash
# No IntelliJ: Execute PopularBanco.java
```

3. **Inicie a aplicação web**
```bash
# No IntelliJ: Execute OficinaApplication.java
```

4. **Acesse a aplicação**
```
http://localhost:8080
```

### Alternativa - Interface de Linha de Comando:
```bash
# No IntelliJ: Execute Main.java
```

## 📊 Dados de Exemplo

O sistema é populado automaticamente com:
- **3 Clientes**: Estela Luna, Ana Carolina, Pedro Cunha
- **3 Produtos**: Óleo Motor 5W30, Filtro de Ar, Pastilha de Freio
- **3 Vendas**: Transações completas com controle de estoque

## 🧪 Testes

Execute os testes unitários:
```bash
# Via IntelliJ: Clique direito em 'test' → Run All Tests
```

**Cobertura de testes:**
- Validações de modelos
- Regras de negócio dos serviços
- Casos de erro e exceções

## 🎯 Aprendizados Aplicados

- **Arquitetura MVC** com separação de responsabilidades
- **Integração Java + MongoDB** sem ORM
- **APIs REST** para comunicação frontend-backend
- **Validações** e tratamento de erros
- **Testes unitários** com JUnit
- **Interface web** moderna e responsiva
- **Boas práticas** de desenvolvimento Java

## 👨‍💻 Desenvolvedor

**Daniely Vasconcelos**  
Curso: Sistemas para Internet  
Disciplina: Banco de Dados II

---

*Este projeto demonstra a evolução de um requisito simples para uma aplicação completa, aplicando conceitos de desenvolvimento web, arquitetura de software e boas práticas de programação.*