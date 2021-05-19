import java.util.Collection;
import java.util.Random;

public class Pacotinho <T extends Colecionavel> {

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

    public T[] getColecionavel(String tipoDeColecionavel) {

        int tamanho = this.posicoesDesejadas.length;
        T colecionaveis[];

        if(tipoDeColecionavel == "Figurinha"){
            colecionaveis = (T[]) new Figurinha[tamanho];
            for(int i = 0; i < tamanho; i++){
                colecionaveis[i] = (T) new Figurinha(this.posicoesDesejadas[i], null);
            }
        }
        else if(tipoDeColecionavel == "Selo"){
            colecionaveis = (T[]) new Selo[tamanho];
            for(int i = 0; i < tamanho; i++){
                colecionaveis[i] = (T) new Selo(this.posicoesDesejadas[i], null);
            }
        }
        else{
            System.out.println("Não há esse tipo no pacote");
            return null;
        }

        return colecionaveis;
    }
}
