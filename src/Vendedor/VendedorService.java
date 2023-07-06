package Vendedor;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VendedorService {
    private static List<Vendedor> vendedores = new ArrayList<>();
    public static List<Vendedor> getVendedores() {
        return vendedores;
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

    //LISTAR VENDEDORES
    public static void listarVendedores() {
        System.out.println("\n***************** Vendedores cadastrados *****************");
        for (Vendedor vendedor : getVendedores()) {
            System.out.println("\nNome: " + vendedor.getNome());
            System.out.println("CPF: " + vendedor.getCpf());
            System.out.println("Email: " + vendedor.getEmail());
        }
    }
}
