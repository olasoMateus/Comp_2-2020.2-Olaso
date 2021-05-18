import java.awt.*;

public class Selo implements Colecionavel{

    private  float valorNominal;
    private String pais;
    private final int posicao;
    private final Image imagem;

    public Selo(int posicao, String urlDaImagem){
        this.posicao = posicao;
        this.imagem = obterImagem(urlDaImagem);
    }

    private Image obterImagem(String url) {
        // ToDo baixaria da Internet...
        return null;
    }

    public float getValorNominal() {
        return 0;
    }

    public String getPais() {
        return null;
    }

    @Override
    public Image getImagem() {
        return null;
    }

    @Override
    public int getPosicao() {
        return this.posicao;
    }
}
