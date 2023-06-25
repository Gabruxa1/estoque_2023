package model.bean;


public class CompraProduto {
    private Compra compra;
    private Produto produto;
    private int quantidade;
    private double valorUnitarioCompra;

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitarioCompra() {
        return valorUnitarioCompra;
    }

    public void setValorUnitarioCompra(double valorUnitarioCompra) {
        this.valorUnitarioCompra = valorUnitarioCompra;
    }
    
    public double getValorTotal(){
        return quantidade * valorUnitarioCompra;
    }
}
