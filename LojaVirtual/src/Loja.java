import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Implementa uma loja virtual para produtos de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja {

    private static final Loja instanciaUnica = new Loja();
    private ArrayList<Usuario> usuariosCadastrados = new ArrayList<>();
    private ArrayList<ItemDoEstoque> estoque = new ArrayList<>();

    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    public Loja() {
        // escrevo código normalmente para o construtor
    }

    public static Loja getInstanciaUnica() {
        return instanciaUnica;
    }

    /**
     * Inclui no estoque da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir) {
        for(int i = 0; i < estoque.size(); i++){
            ItemDoEstoque item = estoque.get(i);
            if(item.equals(produto)){
                item.adicionarQuantidade(quantidadeAIncluir);
                return;
            }
        }
        estoque.add(new ItemDoEstoque(produto.getNome(), quantidadeAIncluir));
        return;

        // ToDo IMPLEMENT ME!!!
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuariosCadastrados.add(usuario);
        // ToDo IMPLEMENT ME!!!
    }

    /**
     * Efetua a venda do produto desejado na quantidade especificada.
     *
     * @param produto o produto
     * @param quantidadeDesejada a quantidade
     *
     * @return um Recibo indicando a venda feita, se o produto existia (em quantidade suficiente)
     *         no estoque da loja; null, caso o usuário ou o produto sejam desconhecidos,
     *         ou não haja quantidade suficiente do produto desejado
     */
    public Recibo efetuarVenda(
            Produto produto, int quantidadeDesejada, Usuario usuario) {
        for(int i = 0; i < usuariosCadastrados.size(); i++){
            if(usuariosCadastrados.get(i).equals(usuario)) {
                if (estoque.size() == 0) {
                    System.out.println("Não possuimos esse produto.");
                    return null;
                }
                for (int count = 0; count < estoque.size(); count++) {
                    ItemDoEstoque item = estoque.get(i);
                    if (item.equals(produto)) {
                        if (item.getQuantidade() >= quantidadeDesejada) {
                            item.retirarQuantidade(quantidadeDesejada);
                            return new Recibo(usuario, quantidadeDesejada,
                                    produto.precoEmReais() * quantidadeDesejada, produto);  // ToDo IMPLEMENT ME!!!
                        } else {
                            System.out.println("Não temos essa quantidade de " + produto.getNome() + " no momento.");
                            return null;
                        }
                    }
                }
                System.out.println("Não possuimos esse produto.");
                return null;
            }
        }
        System.out.println("Usuário não cadastrado!");
        return null;

    }

    /**
     * @param produto o produto a ser consultado
     *
     * @return a quantidade em estoque;
     *         0 se não houver nenhuma unidade;
     *         -1 se o produto não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Produto produto) {
        for(int i = 0; i < estoque.size(); i++){
            ItemDoEstoque item = estoque.get(i);
            if(item.equals(produto)){
                return item.getQuantidade() == 0? 0: estoque.get(i).getQuantidade();
            }
        }

        return -1;  // ToDo IMPLEMENT ME!!!
    }

    private class ItemDoEstoque {


        private String nome;
        private int quantidade;

        public ItemDoEstoque(String nome, int quantidade){
            this.nome = nome;
            this.quantidade = quantidade;
        }

        public String getNome() {
            return nome;
        }

        public int getQuantidade() { return quantidade; }

        public void adicionarQuantidade(int quant){
            this.quantidade += quant;
            return;
        }

        public void retirarQuantidade(int quant){
            this.quantidade -= quant;
            return;
        }

        public boolean equals(Produto produto) {
            if(this.nome == produto.getNome()) return true;
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(nome);
        }
    }
}

