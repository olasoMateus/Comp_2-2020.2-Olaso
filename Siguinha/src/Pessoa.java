public abstract class Pessoa {

    protected String nome;

    private int anoDeNascimento;

    public final static int TAMANHO_MAXIMO_DO_NOME = 30;

    public Pessoa(String nome, int anoDeNascimento){
        this.nome = nome;
        this.anoDeNascimento = anoDeNascimento;
    }

    public String getNome(){
        return nome;
    };

    public int getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setNome(String nome) {
        if (nome.length() > TAMANHO_MAXIMO_DO_NOME) {
            // ToDo: lançar uma exceção!!!
            return;
        }

        this.nome = nome;
    }

    public int getIdade() {
        return Siguinha.obterAnoCorrente() - anoDeNascimento;
    }
}
