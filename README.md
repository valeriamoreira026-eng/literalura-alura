## 🛠️ Tecnologias Utilizadas

### **Backend:**
- **Java 17** - Linguagem principal
- **Spring Boot 3.5.10** - Framework para aplicações Java
- **Spring Data JPA** - Persistência de dados
- **Maven** - Gerenciamento de dependências

### **Banco de Dados:**
- **PostgreSQL 16** - Banco de dados relacional
- **Hibernate** - ORM (Object-Relational Mapping)

### **API Externa:**
- **Gutendex API** - Fonte de dados (70k+ livros em domínio público)
- **Jackson** - Processamento de JSON

### **Ferramentas:**
- **IntelliJ IDEA** - IDE de desenvolvimento
- **Git & GitHub** - Controle de versão
- **PostgreSQL/pgAdmin** - Gerenciamento do banco

🚦 Como Executar o Projeto
Pré-requisitos:
Java 17 ou superior

PostgreSQL 16 ou superior

Maven 3.8+

Passo a Passo:
Clone o repositório:

bash
git clone https://github.com/valeriamoreira026-eng/literalura-alura.git
cd literalura-alura
Configure o banco de dados PostgreSQL:

sql
CREATE DATABASE literalura;
-- Ou use: createdb -U postgres literalura
Configure as credenciais em src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
Execute a aplicação:

bash
# Via Maven
mvn spring-boot:run

# Ou compile e execute
mvn clean install
java -jar target/literalura-0.0.1-SNAPSHOT.jar
🎮 Como Usar
Ao iniciar, o sistema apresentará este menu:

text
=== LITERALURA - CATÁLOGO DE LIVROS ===

1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um ano
5 - Listar livros por idioma
6 - Top 10 livros mais baixados
0 - Sair
Exemplos de Busca:
Dom Casmurro - Machado de Assis (português)

Emma - Jane Austen (inglês)

Romeo and Juliet - Shakespeare (inglês)

Don Quijote - Cervantes (espanhol)

Exemplo de Saída:
text
✅ LIVRO CADASTRADO COM SUCESSO!
📖 Título: Dom Casmurro
👤 Autor: Machado de Assis
🌐 Idioma: pt
⬇️  Downloads: 1,238
📅 Autor vivo entre: 1839 - 1908
🔗 API Utilizada
Gutendex Project
URL: https://gutendex.com/

Descrição: API RESTful que fornece metadados sobre livros do Project Gutenberg

Características:

+70.000 livros em domínio público

Suporte a múltiplos idiomas

Sem necessidade de chave de API

Dados completos: título, autor, idioma, downloads

Exemplo de Requisição:
bash
GET https://gutendex.com/books/?search=shakespeare
📈 Aprendizados do Desafio
Habilidades Desenvolvidas:
✅ Consumo de APIs REST com Java HTTP Client

✅ Mapeamento Objeto-Relacional com JPA/Hibernate

✅ Design de entidades com relacionamentos (OneToMany/ManyToOne)

✅ Implementação de repositórios com Spring Data JPA

✅ Criação de interfaces de linha de comando (CLI)

✅ Manipulação de JSON com Jackson

✅ Configuração de banco de dados PostgreSQL com Spring Boot

Padrões Aplicados:
MVC (Model-View-Controller)

DTO (Data Transfer Object)

Repository Pattern

Service Layer

Dependency Injection

👩‍💻 Desenvolvedora
Valéria Moreira
💻 Desenvolvedora Backend Java
📧 GitHub
🎓 Aluna do programa Alura ONE - Oracle Next Education

📄 Licença
Este projeto foi desenvolvido como parte do desafio técnico da Alura.
O código é aberto para fins educacionais.

🙌 Agradecimentos
Alura pelo excelente conteúdo e desafio

Oracle pelo programa ONE (Oracle Next Education)

Project Gutenberg por disponibilizar livros em domínio público

Gutendex pela API gratuita e bem documentada

📌 Referências
Documentação Spring Boot

Documentação PostgreSQL

API Gutendex

Project Gutenberg

