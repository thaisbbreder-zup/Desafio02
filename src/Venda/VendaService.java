package Venda;

import Cliente.Cliente;
import Cliente.ClienteService;
import Vendedor.VendedorService;
import Vendedor.Vendedor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VendaService {

    private static List<Venda> vendas = new ArrayList<>();

    public static List<Venda> getVenda() {
        return vendas;
    }

    //CADASTRAR VENDA
    public static void cadastrarVenda() throws Exception {
        Scanner entradaDoUsuario = new Scanner(System.in);
        System.out.println("\n***************** Cadastro de Venda *****************");
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = entradaDoUsuario.nextLine();
        double preco;
        try {
            System.out.print("Digite o preço do produto: ");
            preco = entradaDoUsuario.nextDouble();
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
        Venda venda = new Venda(nomeProduto, preco);

        if (!VendedorService.getVendedores().isEmpty()) {
            try {
                System.out.println("\nSelecione o vendedor responsável pela venda: ");
                for (int i = 0; i < VendedorService.getVendedores().size(); i++) {
                    System.out.println((i + 1) + " - " + VendedorService.getVendedores().get(i).getNome());
                }
                int vendedorResponsavel = entradaDoUsuario.nextInt();
                Vendedor vendedor = VendedorService.getVendedores().get(vendedorResponsavel - 1);
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
        } else if (VendedorService.getVendedores().isEmpty()) {
            //não cadastra se a lista de vendedor/cliente estiver vazia
            System.out.println("\nERRO: Favor cadastrar vendedor antes de adicionar uma venda.");
            return;
        }

        if (!ClienteService.getClientes().isEmpty()) {
            try {
                System.out.println("\nSelecione o cliente que realizou a compra: ");
                for (int i = 0; i < ClienteService.getClientes().size(); i++) {
                    System.out.println((i + 1) + " - " + ClienteService.getClientes().get(i).getNome());
                }
                int clienteResponsavel = entradaDoUsuario.nextInt();
                Cliente cliente = ClienteService.getClientes().get(clienteResponsavel - 1);
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
        } else if (ClienteService.getClientes().isEmpty()) {
            System.out.println("\nERRO: Favor cadastrar cliente antes de adicionar uma venda.");
            return;
        }
        venda.setDataRegistro(LocalDate.now());
        vendas.add(venda);
    }

    //LISTAR VENDAS
    public static void listarVendas() {
        System.out.println("\n***************** Vendas cadastradas *****************");
        for (Venda venda : vendas) {
            System.out.println("\nProduto: " + venda.getNomeProduto());
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

        for (Vendedor vendedor : VendedorService.getVendedores()) {
            if (vendedor.getCpf() == buscaCpf) {
                vendedorEncontrado = vendedor;
                break;
            }
        }

        if (vendedorEncontrado != null) {
            System.out.println("\n***************** Vendas do Vendedor.Vendedor *****************");
            for (Venda venda : vendas) {
                if (venda.getVendedorResponsavel().equals(vendedorEncontrado)) {
                    System.out.println("Produto: " + venda.getNomeProduto());
                    System.out.println("Preço: " + venda.getPreco());
                    System.out.println("Cliente " + venda.getClienteResponsavel().getNome());
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

        for (Vendedor vendedor : VendedorService.getVendedores()) {
            if (vendedor.getEmail().equals(buscaEmail)) {
                vendedorEncontrado = vendedor;
                break;
            }
        }

        if (vendedorEncontrado != null) {
            System.out.println("\n***************** Vendas do Vendedor.Vendedor *****************");
            for (Venda venda : vendas) {
                if (venda.getVendedorResponsavel().equals(vendedorEncontrado)) {
                    System.out.println("Produto: " + venda.getNomeProduto());
                    System.out.println("Preço: " + venda.getPreco());
                    System.out.println("Cliente " + venda.getClienteResponsavel().getNome());
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

        for (Cliente cliente : ClienteService.getClientes()) {
            if (cliente.getCpf() == buscaCpf) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            System.out.println("Cliente " + clienteEncontrado.getNome());
            for (Venda vendas : getVenda()) {
                if (vendas.getClienteResponsavel().getCpf() == buscaCpf) {
                    System.out.println("\n***************** Compras do Cliente.Cliente *****************");
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

        for (Cliente cliente : ClienteService.getClientes()) {
            if (cliente.getEmail().equals(buscaEmail)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado != null) {
            System.out.println("\n***************** Compras do Cliente.Cliente *****************");
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
