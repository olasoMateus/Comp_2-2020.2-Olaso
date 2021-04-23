public class Recibo {

    private float valorTotalDaCompra;
    private int quantidadeDeItensComprados;
    private Usuario usuario;
    private Produto produtoComprado;



    public Recibo(Usuario usuario, int quantidadeDeItensComprados, float valorTotalDaCompra, Produto produtoComprado){
        this.usuario = usuario;
        this.quantidadeDeItensComprados = quantidadeDeItensComprados;
        this.valorTotalDaCompra = valorTotalDaCompra;
        this.produtoComprado = produtoComprado;
    }

    public float getValorTotalDaCompra() {
        return this.valorTotalDaCompra;  // ToDo IMPLEMENT ME!!!
    }

    public Usuario getUsuario() {
        return this.usuario;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public String toString() {
        return "Recibo no valor de R$" +
                (int)this.valorTotalDaCompra +",00 para " + usuario.getNome() +
                " referente à compra de " + this.quantidadeDeItensComprados +
                " unidades de " + produtoComprado.pegarClasse() +
                ": " + produtoComprado.getNome();
    }
}
