import java.time.LocalDate;
import java.util.*;

public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Vendedor> vendedores = new ArrayList<>();
    private static List<Venda> vendas = new ArrayList<>();

    public static List<Vendedor> getVendedores() {
        return vendedores;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static List<Venda> getVenda() {
        return vendas;
    }

    public static void main(String[] args) throws Exception {

        Scanner entradaDoUsuario = new Scanner(System.in);
        int opcao = -1;

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
                    + "\n7 - Pesquisar vendedor por cpf"
                    + "\n8 - Pesquisar cliente por cpf"
                    + "\n9 - Pesquisar vendedor por email"
                    + "\n10 - Pesquisar cliente por email"
                    + "\n11 - Sair");
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
                    pesquisarVendedorCPF();
                    break;
                case 8:
                    pesquisarClienteCPF();
                    break;
                case 9:
                    pesquisarVendedorEmail();
                    break;
                case 10:
                    pesquisarClienteEmail();
                    break;
                case 11:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, selecione um número válido.");
            }
        } while (opcao != 11);
    }

    //CADASTRAR VENDEDOR
    public static void cadastrarVendedor() {
        Scanner entradaDoUsuario = new Scanner(System.in);
        System.out.println("\n***************** Cadastro de Vendedor *****************");
        try {
            System.out.print("Digite o nome do vendedor: ");
            String nome = entradaDoUsuario.nextLine();
            System.out.print("Digite o CPF do vendedor: ");
            int cpf = entradaDoUsuario.nextInt();
            if (verificarCPFDuplicadoVendedor(cpf)) {
                System.out.println("\nERRO: Já existe um vendedor com esse CPF cadastrado!"); //ENTREGA MEDIA
                return; // Retorna sem adicionar o vendedor na lista
            }
            System.out.print("Digite o email do vendedor: ");
            String email = entradaDoUsuario.next();
            if (!email.contains("@")) {
                System.out.println("\nERRO: Email inválido! O email deve conter o caractere '@'."); //ENTREGA MEDIA
                return;
            }
            if (verificarEmailDuplicadoVendedor(email)) {
                System.out.println("\nERRO: Já existe um vendedor com esse email cadastrado!"); //ENTREGA MEDIA
                return;
            }
            Vendedor vendedor = new Vendedor(nome, cpf, email);
            vendedores.add(vendedor);
            System.out.println("\n*** Vendedor cadastrado com sucesso! ***");
        } catch (InputMismatchException e) { //valor fornecido não corresponde ao tipo esperado
            System.out.println("\nERRO: Valor inválido. Forneça um valor numérico.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
        String nome = entradaDoUsuario.next();

        int cpf = 0;

        try {
            System.out.print("Digite o CPF do cliente: ");
            cpf = entradaDoUsuario.nextInt();
        } catch (InputMismatchException e) { //valor fornecido não corresponde ao tipo esperado(int)
            System.out.println("\nERRO: Valor inválido. Informe um valor numérico.");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (verificarCPFDuplicadoCliente(cpf)) {
            System.out.println("\nERRO: Já existe um cliente com esse CPF cadastrado!"); //ENTREGA MEDIA
            return; // Retorna sem adicionar o cliente na lista
        }

        System.out.print("Digite o email do cliente: ");
        String email = entradaDoUsuario.next();
        if (!email.contains("@")) {
            System.out.println("\nERRO: Email inválido! O email deve conter o caractere '@'."); //ENTREGA MEDIA
            return;
        }

        if (verificarEmailDuplicadoCliente(email)) {
            System.out.println("\nERRO: Já existe um cliente com esse email cadastrado!"); //ENTREGA MEDIA
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
    public static void cadastrarVenda() throws Exception {
        Scanner entradaDoUsuario = new Scanner(System.in);
        System.out.println("\n***************** Cadastro de Venda *****************");
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = entradaDoUsuario.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = entradaDoUsuario.nextDouble();

        //cria a venda passando o produto como parâmetro
        Venda venda = new Venda(nomeProduto, preco);

        if (!vendedores.isEmpty()) {
            try {
                System.out.println("\nSelecione o vendedor responsável pela venda: ");
                for (int i = 0; i < vendedores.size(); i++) {
                    System.out.println((i + 1) + " - " + vendedores.get(i).getNome());
                }
                int vendedorResponsavel = entradaDoUsuario.nextInt();
                Vendedor vendedor = vendedores.get(vendedorResponsavel - 1);
                venda.setVendedorResponsavel(vendedor);
            } catch (InputMismatchException e) {
                System.out.println("\nERRO: Valor inválido. Forneça um valor numérico.");
                return;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("\nERRO: Opção inválida. Por favor, selecione um número válido.");
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        } else if (vendedores.isEmpty()) {
            //não cadastra se a lista de vendedor/cliente estiver vazia
            System.out.println("\nERRO: Favor cadastrar vendedor antes de adicionar uma venda.");
            return;
        }

        if (!clientes.isEmpty()) {
            try {
                System.out.println("\nSelecione o cliente que realizou a compra: ");
                for (int i = 0; i < clientes.size(); i++) {
                    System.out.println((i + 1) + " - " + clientes.get(i).getNome());
                }
                int clienteResponsavel = entradaDoUsuario.nextInt();
                Cliente cliente = clientes.get(clienteResponsavel - 1);
                venda.setClienteResponsavel(cliente);
                System.out.println("\n*** Venda cadastrada com sucesso ***");
            } catch (InputMismatchException e) {
                System.out.println("\nERRO: Valor inválido. Forneça um valor numérico.");
                return;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("\nERRO: Opção inválida. Por favor, selecione um número válido.");
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        } else if (clientes.isEmpty()) {
            System.out.println("\nERRO: Favor cadastrar cliente antes de adicionar uma venda.");
            return;
        }

        venda.setDataRegistro(LocalDate.now());
        vendas.add(venda);
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
        for (Venda venda : vendas) {
            System.out.println("Produto: " + venda.getNomeProduto());
            System.out.println("Preço: " + venda.getPreco());
            System.out.println("Cliente: " + venda.getClienteResponsavel().getNome());
            System.out.println("Vendedor: " + venda.getVendedorResponsavel().getNome());
            System.out.println("Data da venda: " + venda.getDataRegistro());
        }
    }

    //PESQUISAR VENDEDOR PELO CPF
    public static Vendedor pesquisarVendedorCPF() {
        Scanner entradaDoUsuario = new Scanner(System.in);

        try {
            System.out.print("Digite o CPF do vendedor para pesquisar suas vendas: ");
        } catch (InputMismatchException e) { //valor fornecido não corresponde ao tipo esperado
            System.out.println("\nERRO: Valor inválido. Forneça um valor numérico.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int buscaCpf = entradaDoUsuario.nextInt();
        Vendedor vendedorEncontrado = null;

        for (Vendedor vendedor : vendedores) {
            if (vendedor.getCpf() == buscaCpf) {
                vendedorEncontrado = vendedor;
                break;
            }
        }

        if (vendedorEncontrado != null) {
            System.out.println("\n***************** Vendas do Vendedor *****************");
            for (Venda venda : vendas) {
                if (venda.getVendedorResponsavel().equals(vendedorEncontrado)) {
                    System.out.println("Produto: " + venda.getNomeProduto());
                    System.out.println("Preço: " + venda.getPreco());
                    System.out.println("Cliente: " + venda.getClienteResponsavel().getNome());
                }
            }
        } else {
            System.out.println("\nNenhum vendedor encontrado com o CPF fornecido.");
        }
        return vendedorEncontrado;
    }

    public static Vendedor pesquisarVendedorEmail() {
        Scanner entradaDoUsuario = new Scanner(System.in);

        System.out.print("Digite o email do vendedor para pesquisar suas vendas: ");
        String buscaEmail = entradaDoUsuario.nextLine();

        Vendedor vendedorEncontrado = null;

        for (Vendedor vendedor : vendedores) {
            if (vendedor.getEmail().equals(buscaEmail)) {
                vendedorEncontrado = vendedor;
                break;
            }
        }

        if (vendedorEncontrado != null) {
            System.out.println("\n***************** Vendas do Vendedor *****************");
            for (Venda venda : vendas) {
                if (venda.getVendedorResponsavel().equals(vendedorEncontrado)) {
                    System.out.println("Produto: " + venda.getNomeProduto());
                    System.out.println("Preço: " + venda.getPreco());
                    System.out.println("Cliente: " + venda.getClienteResponsavel().getNome());
                }
            }
        } else {
            System.out.println("\nNenhum vendedor encontrado com o email fornecido.");
        }

        return vendedorEncontrado;
    }


    //PESQUISAR CLIENTE PELO CPF
    public static Cliente pesquisarClienteCPF() {
        Scanner entradaDoUsuario = new Scanner(System.in);
        try {
            System.out.print("Digite o CPF do cliente para pesquisar suas compras: ");
        } catch (InputMismatchException e) { //valor fornecido não corresponde ao tipo esperado
            System.out.println("\nERRO: Valor inválido. Forneça um valor numérico.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int buscaCpf = entradaDoUsuario.nextInt();
        Cliente clienteEncontrado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getCpf() == buscaCpf) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            System.out.println("Cliente: " + clienteEncontrado.getNome());
            for (Venda vendas : getVenda()) {
                if (vendas.getClienteResponsavel().getCpf() == buscaCpf) {
                    System.out.println("\n***************** Compras do Cliente *****************");
                    System.out.println("Produto: " + vendas.getNomeProduto());
                    System.out.println("Preço: " + vendas.getPreco());
                    System.out.println("Vendedor: " + vendas.getVendedorResponsavel().getNome());

                }
            }
        } else {
            System.out.println("\nNenhum cliente encontrado com o CPF fornecido.");
        }

        return clienteEncontrado;
    }

    // Pesquisar cliente por email
    public static Cliente pesquisarClienteEmail() {
        Scanner entradaDoUsuario = new Scanner(System.in);

        System.out.print("Digite o email do cliente para pesquisar suas compras: ");
        String buscaEmail = entradaDoUsuario.nextLine();

        Cliente clienteEncontrado = null;

        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(buscaEmail)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            System.out.println("\n***************** Compras do Cliente *****************");
            for (Venda venda : vendas) {
                if (venda.getClienteResponsavel().equals(clienteEncontrado)) {
                    System.out.println("Produto: " + venda.getNomeProduto());
                    System.out.println("Preço: " + venda.getPreco());
                    System.out.println("Vendedor: " + venda.getVendedorResponsavel().getNome());
                }
            }
        } else {
            System.out.println("\nNenhum cliente encontrado com o email fornecido.");
        }
        return clienteEncontrado;
    }

}


