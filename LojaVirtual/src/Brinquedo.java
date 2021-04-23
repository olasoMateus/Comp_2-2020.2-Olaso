public class Brinquedo extends Produto {

    private String marca;
    private int idadeMinimaRecomendada;

    public Brinquedo(String nome) {
        super(nome, null);// ToDo IMPLEMENT ME!!!
    }

    public String getMarca() {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    public int getIdadeMinimaRecomendada() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public String pegarClasse() {
        return "Brinquedo";
    }
}
