import java.util.ArrayList;
import java.util.List;

public class Repositorio<T extends Colecionavel>{

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<Colecionavel> todasOsColecionaveis;

    private ColecionavelFactory fabricaDeColecionaveis;

    private String tipo;

    public Repositorio(String sufixoUrlImagens, int quantFigurinhas, T tipo) {
        this.tipo = tipo.getClass().getName();
        todasOsColecionaveis = new ArrayList<>(quantFigurinhas);
        for (int i = 1; i <= quantFigurinhas; i++) {
            fabricaDeColecionaveis = new ColecionavelFactory(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens, tipo);
            todasOsColecionaveis.add(fabricaDeColecionaveis.getInstancia());
        }

    }

    public int getTotalColecionaveis() {
        return this.todasOsColecionaveis.size();
    }

    public String getTipoDeColecionavel() {
        return this.tipo;
    }
}
