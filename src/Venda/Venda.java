package Venda;

import Cliente.Cliente;
import Vendedor.Vendedor;

import java.time.LocalDate;

public class Venda {
    private String nomeProduto;
    private double preco;
    private Cliente clienteResponsavel;
    private Vendedor vendedorResponsavel;
    //data de registro
    private LocalDate dataRegistro;


  public Venda(String nomeProduto, double preco) {
      this.nomeProduto = nomeProduto;
      this.preco = preco;
  }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setClienteResponsavel(Cliente cliente) {
        this.clienteResponsavel = cliente;
    }

    public Cliente getClienteResponsavel() {
        return clienteResponsavel;
    }

    public void setVendedorResponsavel(Vendedor vendedor) {
        this.vendedorResponsavel = vendedor;
    }

    public Vendedor getVendedorResponsavel() {
        return vendedorResponsavel;
    }
}
