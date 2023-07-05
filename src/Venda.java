public class Venda {
    private String nomeProduto;
    private double preco;

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

    public void setVendedorResponsavel(Vendedor vendedor) {
    }

    public void setClienteResponsavel(Cliente cliente) {
    }
}
