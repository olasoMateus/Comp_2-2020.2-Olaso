import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<Colecionavel> todasOsColecionaveis;

    private final String tipoDeColecionavel;

    public Repositorio(String sufixoUrlImagens, int quantFigurinhas, String tipoDeColecionavel) {
        todasOsColecionaveis = new ArrayList<>(quantFigurinhas);
        this.tipoDeColecionavel = tipoDeColecionavel;
        if(tipoDeColecionavel == "selo"){
            for (int i = 1; i <= quantFigurinhas; i++) {
                Selo selo = new Selo(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
                todasOsColecionaveis.add(selo);
            }
        }
        else if(tipoDeColecionavel == "figurinha"){
            for (int i = 1; i <= quantFigurinhas; i++) {
                Figurinha fig = new Figurinha(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
                todasOsColecionaveis.add(fig);
            }
        }
        else{
            System.out.println("Tipo de colecionavel não válido!");
        }

    }

    public int getTotalColecionaveis() {
        return this.todasOsColecionaveis.size();
    }

    public String getTipoDeColecionavel() {
        return tipoDeColecionavel;
    }
}
