public class ColecionavelFactory<T extends Colecionavel> {

    private int posicao;
    private String urlDaImagem;
    private String tipo;
    public ColecionavelFactory(int posicao, String urlDaImagem, T tipo){
           this.posicao = posicao;
           this.urlDaImagem = urlDaImagem;
           this.tipo = tipo.getClass().getName();
    }

    public Colecionavel getInstancia(){
        if(this.tipo.getClass().getName() == "Figurinha"){
            return new Figurinha(posicao, urlDaImagem);
        }
        if(this.tipo.getClass().getName() == "Selo"){
            return new Selo(posicao, urlDaImagem);
        }

        return null;
    }
}
