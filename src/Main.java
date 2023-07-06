import Cliente.ClienteService;
import Venda.VendaService;
import Vendedor.VendedorService;

import java.util.*;

public class Main {
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
                    VendedorService.cadastrarVendedor();
                    break;
                case 2:
                    ClienteService.cadastrarCliente();
                    break;
                case 3:
                    VendaService.cadastrarVenda();
                    break;
                case 4:
                    VendedorService.listarVendedores();
                    break;
                case 5:
                    ClienteService.listarClientes();
                    break;
                case 6:
                    VendaService.listarVendas();
                    break;
                case 7:
                    VendaService.pesquisarVendedorCPF();
                    break;
                case 8:
                    VendaService.pesquisarClienteCPF();
                    break;
                case 9:
                    VendaService.pesquisarVendedorEmail();
                    break;
                case 10:
                    VendaService.pesquisarClienteEmail();
                    break;
                case 11:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, selecione um número válido.");
            }
        } while (opcao != 11);
    }
}


