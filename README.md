# Sistema de Registro de Vendas 💼📊💰
Este é um sistema responsável por registrar as vendas de uma organização. Com ele, você poderá cadastrar vendas, clientes e vendedores, além de realizar consultas e listagens.

### 📝✅ Entrega Mínima
- __Cadastro:__ É possível cadastrar clientes, vendedores e vendas.Além disso, é possivel associar a venda a um vendedor e a um cliente.<br>
- __Restrição Cadastrado:__ O sistema não permite cadastrar vendas sem que existe clientes e/ou vendedores cadastrados previamente.<br>
- __Listagem:__ É possível listar todas as vendas, vendedores e clientes cadastradas no sistema.<br>

### 🚀🔍 Entrega Média 
Além das funcionalidades básicas, o sistema também possui as seguintes restrições adicionais:
-  __Restrição de e-mail inválido:__ O sistema não permite cadastrar clientes/vendedores com e-mails inválidos, ou seja, sem o símbolo "@".<br>
-  __Restrição de CPFs repetidos:__ O sistema não permite cadastrar clientes/vendedores com CPFs repetidos.<br>
-  __Restrição de e-mails repetidos:__ O sistema não permite cadastrar clientes/vendedores com e-mails repetidos.<br>

### 🌟🔎 Entrega Máxima 
Na versão máxima do sistema, foram adicionadas as seguintes funcionalidades:
- __Pesquisa por CPF:__ É possível pesquisar todas as compras/vendas realizadas por um cliente/vendedor específico, utilizando o seu CPF como filtro.<br>

# 💡📝 Estrutura do Código
O código do sistema está organizado em pacotes, seguindo a divisão de responsabilidades. Abaixo está uma breve descrição de cada pacote:

__1. Main:__ Classe principal do sistema, com o método main como ponto de entrada do programa.<br>

__2. Pessoa:__ Classe base para Cliente e Vendedor, representando uma pessoa genérica com atributos comuns.<br>

__3. Cliente__ <br>
- __Cliente:__ Representa um cliente no sistema, herdando da classe Pessoa. <br>
- __ClienteService:__ Fornecer serviços para o gerenciamento de clientes, como cadastrar clientes e listar clientes cadastrados.<br>
  
__4. Vendedor__ <br>
- __Vendedor:__ Representa um vendedor no sistema, herdando da classe Pessoa e possuindo atributos adicionais, como código de vendedor.<br>
- __VendedorService:__ Fornece serviços relacionados ao gerenciamento de vendedores no sistema, como cadastrar vendedores e listar vendedores cadastrados.<br>

__5. Venda__ <br>
- __Venda:__ Representa uma venda no sistema, contendo informações como nome do produto, preço, cliente responsável e vendedor responsável.<br>
- __VendaService:__ Fornece serviços para o gerenciamento de vendas, como cadastrar vendas e listar vendas cadastradas.<br>

Cada pacote possui as classes e serviços relevantes para suas respectivas funcionalidades no sistema.
 
