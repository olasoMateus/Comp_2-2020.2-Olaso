import java.util.Random;

public class Pacotinho {

    private Repositorio repositorio;
    private int[] posicoesDesejadas;

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {
        this.repositorio = repo;
        this.posicoesDesejadas = posicoesDesejadas;
    }

    /**
     * Produz um pacotinho com quantFigurinhas sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantFigurinhas a quantidade de figurinhas a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantFigurinhas) {
        this.repositorio = repo;

        Random random = new Random();

        this.posicoesDesejadas = new int[quantFigurinhas];

        for (int i = 0; i < quantFigurinhas; i++){
            this.posicoesDesejadas[i] = random.nextInt(repo.getTotalColecionaveis()) + 1;
        }
    }

    public Colecionavel[] getColecionavel(String tipoDeColecionavel) {

        int tamanho = this.posicoesDesejadas.length;
        Colecionavel colecionaveis[] = new Figurinha[tamanho];

        if(tipoDeColecionavel == "figurinha"){
            for(int i = 0; i < tamanho; i++){
                colecionaveis[i] = new Figurinha(this.posicoesDesejadas[i], null);
            }
        }
        else if(tipoDeColecionavel == "selo"){
            for(int i = 0; i < tamanho; i++){
                colecionaveis[i] = new Selo(this.posicoesDesejadas[i], null);
            }
        }
        else{
            System.out.println("Não há esse tipo no pacote");
            return null;
        }

        return colecionaveis;
    }
}
