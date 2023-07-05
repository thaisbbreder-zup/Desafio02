import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Vendedor> vendedores = new ArrayList<>();
    private static List<Venda> produtos = new ArrayList<>();

    public static List<Vendedor> getVendedores() {
        return vendedores;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static List<Venda> getProdutos() {
        return produtos;
    }

    public static void main(String[] args) throws Exception {

        Scanner entradaDoUsuario = new Scanner(System.in);
        int opcao;

        System.out.println("\n***************** Bem vindo ao registro de vendas! *****************");

        do {
            System.out.println("\n------------------------------ MENU ------------------------------");
            System.out.println("\nSELECIONE A AÇÃO DESEJADA:");
            System.out.println("1 - Cadastrar vendedor"
                    + "\n2 - Cadastrar cliente"
                    + "\n3 - Cadastrar venda"
                    + "\n4 - Listar vendedores"
                    + "\n5 - Listar clientes"
                    + "\n6 - Listar vendas"
                    + "\n7 - Pesquisar vendedor"
                    + "\n8 - Pesquisar cliente"
                    + "\n9 - Sair");
            System.out.println("\n-----------------------------------------------------------------");
            System.out.print("\nOpção selecionada: ");

            opcao = entradaDoUsuario.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarVendedor();
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 3:
                    cadastrarVenda();
                    break;
                case 4:
                    listarVendedores();
                    break;
                case 5:
                    listarClientes();
                    break;
                case 6:
                    listarVendas();
                    break;
                case 7:
                    pesquisarVendedor();
                    break;
                case 8:
                    pesquisarCliente();
                    break;
                case 9:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, selecione um número válido.");
            }
        } while (opcao != 9);
    }

    //CADASTRAR VENDEDOR
    public static void cadastrarVendedor() {
        Scanner entradaDoUsuario = new Scanner(System.in);
        System.out.println("\n***************** Cadastro de Vendedor *****************");
        System.out.print("Digite o nome do vendedor: ");
        String nome = entradaDoUsuario.nextLine();
        System.out.print("Digite o CPF do vendedor: ");
        int cpf = entradaDoUsuario.nextInt();
        if (verificarCPFDuplicadoVendedor(cpf)) {
            System.out.println("\nERRO: Já existe um vendedor com esse CPF cadastrado!");
            return; // Retorna sem adicionar o vendedor na lista
        }
        System.out.print("Digite o email do vendedor: ");
        String email = entradaDoUsuario.next();
        if (!email.contains("@")) {
            System.out.println("\nERRO: Email inválido! O email deve conter o caractere '@'.");
            return;
        }
        if (verificarEmailDuplicadoVendedor(email)) {
            System.out.println("\nERRO: Já existe um vendedor com esse email cadastrado!");
            return;
        }
        Vendedor vendedor = new Vendedor(nome, cpf, email);
        vendedores.add(vendedor);
        System.out.println("\n*** Vendedor cadastrado com sucesso! ***");
    }

    private static boolean verificarCPFDuplicadoVendedor(int cpf) {
        return vendedores.stream().anyMatch(vendedor -> vendedor.getCpf() == cpf);
    }

    private static boolean verificarEmailDuplicadoVendedor(String email) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    //CADASTRAR CLIENTE
    public static void cadastrarCliente() {
        Scanner entradaDoUsuario = new Scanner(System.in);
        System.out.println("\n***************** Cadastro de Cliente *****************");
        System.out.print("Digite o nome do cliente: ");
        String nome = entradaDoUsuario.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        int cpf = entradaDoUsuario.nextInt();
        if (verificarCPFDuplicadoCliente(cpf)) {
            System.out.println("\nERRO: Já existe um cliente com esse CPF cadastrado!");
            return; // Retorna sem adicionar o cliente na lista
        }
        System.out.print("Digite o email do cliente: ");
        String email = entradaDoUsuario.next();
        if (!email.contains("@")) {
            System.out.println("\nERRO: Email inválido! O email deve conter o caractere '@'.");
            return;
        }
        if (verificarEmailDuplicadoCliente(email)) {
            System.out.println("\nERRO: Já existe um cliente com esse email cadastrado!");
            return;
        }
        Cliente cliente = new Cliente(nome, cpf, email);
        clientes.add(cliente);
        System.out.println("\n*** Cliente cadastrado com sucesso! ***");
    }

    private static boolean verificarCPFDuplicadoCliente(int cpf) {
        return clientes.stream().anyMatch(cliente -> cliente.getCpf() == cpf);
    }

    private static boolean verificarEmailDuplicadoCliente(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return true; // Email já cadastrado
            }
        }
        return false; // Email não duplicado
    }

    //CADASTRAR VENDA
    public static void cadastrarVenda() {
        Scanner entradaDoUsuario = new Scanner(System.in);
        System.out.println("\n***************** Cadastro de Venda *****************");
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = entradaDoUsuario.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = entradaDoUsuario.nextDouble();
        Venda venda = new Venda(nomeProduto, preco);

        if (!vendedores.isEmpty()) {
            System.out.println("\nSelecione o vendedor responsável pela venda: ");
            for (int i = 0; i < vendedores.size(); i++) {
                System.out.println((i + 1) + " - " + vendedores.get(i).getNome());
            }
            int vendedorResponsavel = entradaDoUsuario.nextInt();
            Vendedor vendedor = vendedores.get(vendedorResponsavel - 1);
            venda.setVendedorResponsavel(vendedor);
        } else if (vendedores.isEmpty()) {
            //não cadastra se a lista de vendedor/cliente estiver vazia
            System.out.println("\nERRO: Favor cadastrar vendedor antes de adicionar uma venda.");
        }

        if (!clientes.isEmpty()) {
            System.out.println("\nSelecione o cliente que realizou a compra: ");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println((i + 1) + " - " + clientes.get(i).getNome());
            }
            int clienteResponsavel = entradaDoUsuario.nextInt();
            Cliente cliente = clientes.get(clienteResponsavel - 1);
            venda.setClienteResponsavel(cliente);
        } else if (clientes.isEmpty()) {
            System.out.println("\nERRO: Favor cadastrar cliente antes de adicionar uma venda.");
        }

        if (!vendedores.isEmpty() && !clientes.isEmpty()) {
            produtos.add(venda);
            System.out.println("\n*** Venda cadastrada com sucesso! ***");
        }
    }

    //LISTAR VENDEDORES
    public static void listarVendedores() {
        System.out.println("\n***************** Vendedores cadastrados *****************");
        for (Vendedor vendedor : getVendedores()) {
            System.out.println("\nNome: " + vendedor.getNome());
            System.out.println("CPF: " + vendedor.getCpf());
            System.out.println("Email: " + vendedor.getEmail());
        }
    }

    //LISTAR CLIENTES
    public static void listarClientes() {
        System.out.println("\n***************** Clientes cadastrados *****************");
        for (Cliente cliente : getClientes()) {
            System.out.println("\nNome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Email: " + cliente.getEmail());
        }
    }

    //LISTAR VENDAS
    public static void listarVendas() {
        System.out.println("\n***************** Vendas cadastradas *****************");
        for (Venda produto : getProdutos()) {
            for (Cliente cliente : getClientes()) {
                for (Vendedor vendedor : getVendedores()) {
                    System.out.println("Produto: " + produto.getNomeProduto());
                    System.out.println("Preço: " + produto.getPreco());
                    System.out.println("Cliente: " + cliente.getNome());
                    System.out.println("Vendedor: " + cliente.getNome());
                }
            }
        }
    }

    //PESQUISAR VENDEDOR PELO CPF
    public static Vendedor pesquisarVendedor() {
        Scanner entradaDoUsuario = new Scanner(System.in);
        System.out.print("Digite o CPF do vendedor para pesquisar suas vendas: ");
        int buscaCpf = entradaDoUsuario.nextInt();
        System.out.println("\n***************** Pesquisa por CPF *****************");
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getCpf() == buscaCpf) {
                return vendedor;
            }
            //tenho que retornar os produtos que ele vendeu
        }
        return null;
    }

    //PESQUISAR CLIENTE PELO CPF
    public static Cliente pesquisarCliente() {
        Scanner entradaDoUsuario = new Scanner(System.in);
        System.out.print("Digite o CPF do cliente para pesquisar suas vendas: ");
        int buscaCpf = entradaDoUsuario.nextInt();
        for (Cliente cliente : clientes) {
            if (cliente.getCpf() == buscaCpf) {
                if (cliente.getCpf() == buscaCpf) {
                    return cliente;
                }
                //tenho que retornar os produtos que ele vendeu
            }
        }
        return null;
    }
}