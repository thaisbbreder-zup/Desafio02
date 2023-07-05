public class Venda {
    private String nomeProduto;
    private double preco;
    private Cliente clienteResponsavel;
    private Vendedor vendedorResponsavel;

    public Venda(String nomeProduto, double preco) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
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
