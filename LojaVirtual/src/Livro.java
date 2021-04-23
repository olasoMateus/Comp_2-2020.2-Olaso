public class Livro extends Produto {

    private String editora;
    private int numeroDePaginas;
    private String trechoEmDestaque;
    private String autor;
    private int anoDePublicaçao;

    public Livro(String nome, String editora) {
        super(nome, null);
        this.editora = editora;
        // ToDo IMPLEMENT ME!!!!
    }

    public int getNumeroDePaginas() {
        return this.numeroDePaginas;  // ToDo IMPLEMENT ME!!!!
    }

    public String getTrechoEmDestaque() {
        return this.trechoEmDestaque;  // ToDo IMPLEMENT ME!!!!
    }

    public String getAutor() {
        return this.autor;  // ToDo IMPLEMENT ME!!!!
    }

    public int getAnoDePublicacao() {
        return this.anoDePublicaçao;  // ToDo IMPLEMENT ME!!!!
    }

    @Override
    public String pegarClasse() {
        return "Livro";
    }
}
