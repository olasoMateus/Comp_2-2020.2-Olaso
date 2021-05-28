import java.util.*;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite {

    public static int TAMANHO_MAXIMO_TUITES = 120;
    private Map<String, Integer> hashtags = new HashMap<>();

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {  // throws UsuarioJaExisteException {
        Usuario usuario = new Usuario(nome, email);
        return usuario;
    }

    /**
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     *
     * PS.: Se o texto exceder o limite pré-definido, ou o usuário não estiver cadastrado, return null
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        if(texto.length() > TAMANHO_MAXIMO_TUITES){
            return null;
        }
        Set<String> hashtags = new HashSet<>();
        for(String result : texto.split("#+")){
            String adicionar = "#" + result.split(" +")[0];
            hashtags.add(adicionar);
            if(this.hashtags.containsKey(adicionar)){
                this.hashtags.put(adicionar, this.hashtags.get(adicionar) + 1);
            }
            else{
                this.hashtags.put(adicionar, 1);
            }
        }

//        for(int i = 0; i < texto.length();i++){
//            char c = texto.charAt(i);
//            if(c == '#'){
//               result = "";
//                result.concat("#");
//            }
        return new Tuite(usuario, texto, hashtags);
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        String result = "";
        int i = 0;
        for(String hashtag : this.hashtags.keySet()){
            int compare = this.hashtags.get(hashtag);
            if(compare > i){
                i = compare;
                result = hashtag;
            }
        }

        return result;
    }
}
