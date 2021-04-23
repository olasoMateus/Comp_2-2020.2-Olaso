public abstract class Produto {

    private String nome;
    private float preco;
    private int peso;
    private Dimensoes dimensoes;
    private String urlDaImagem;
    private String descricao;

    public Produto(String nome, String urlDaImagem) {
        this.nome = nome;
        this.urlDaImagem = urlDaImagem;
        // ToDo IMPLEMENT ME!!!
    }

    /**
     * @return uma descrição textual do produto
     */
    public String getNome(){ return this.nome; }

    public String getDescricao() {
        return this.descricao;  // ToDo IMPLEMENT ME!!!
    }

    public int getPesoEmGramas() {
        return this.peso;  // ToDo IMPLEMENT ME!!!
    }

    public Dimensoes getDimensoes() {
        return this.dimensoes;  // ToDo IMPLEMENT ME!!!
    }

    public float precoEmReais() {
        return this.preco;  // ToDo IMPLEMENT ME!!!
    }

    public void setPrecoEmReais(float preco) {
        this.preco = preco;
        // ToDo IMPLEMENT ME!!!
    }

    public String getUrlDaImagem() {
        return this.urlDaImagem;  // ToDo IMPLEMENT ME!!!
    }

    public abstract String pegarClasse();
}
