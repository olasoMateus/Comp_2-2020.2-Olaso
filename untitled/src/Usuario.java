import java.util.ArrayList;
import java.util.List;

public class Usuario implements Comparable<Usuario> {

    private int id;

    private List<Usuario> amigos;

    private CalculadorIntersecao calculadorIntersecao;

    public Usuario(int id, CalculadorIntersecao calculador) {
        this.calculadorIntersecao = calculador;
        this.id = id;// instancia um calculador de interseção
        this.amigos = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public List<Usuario> getAmigos() {
        return this.amigos;
    }

    public void putAmigos(Usuario usuario){
        if(usuario.compareTo(this) == 0){
            System.out.println("Não é possível se adicionar como amigo!");
            return;
        }
        if(amigos.contains(usuario)) return;
        this.amigos.add(usuario);
        usuario.putAmigos(this);
        return;
    }

    /**
     * Retorna a quantidade de amigos em comum entre este Usuario e o
     * outro Usuario informado.
     *
     * @param outro outro Usuario da rede social
     * @return o tamanho da interseção dos conjuntos de amigos
     */
    public int obterQuantidadeDeAmigosEmComum(Usuario outro) {
        return calculadorIntersecao.obterIntersecao(amigos, outro.getAmigos()).size();
    }

    @Override
    public int compareTo(Usuario o) {
        return this.id - o.id;
    }
}
