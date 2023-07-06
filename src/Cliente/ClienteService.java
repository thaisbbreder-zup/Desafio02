package Cliente;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClienteService {
    private static List<Cliente> clientes = new ArrayList<>();

    public static List<Cliente> getClientes() {
        return clientes;
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

    //LISTAR CLIENTES
    public static void listarClientes() {
        System.out.println("\n***************** Clientes cadastrados *****************");
        for (Cliente cliente : getClientes()) {
            System.out.println("\nNome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Email: " + cliente.getEmail());
        }
    }
}
