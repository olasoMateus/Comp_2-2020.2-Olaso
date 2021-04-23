import java.util.Objects;

public class Usuario {

    private String nome;
    private long cpf;
    private String endereco;


    public Usuario(String nome, long cpf, String endereco) {


        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;

        // ToDo IMPLEMENT ME!!!
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return cpf == usuario.cpf && Objects.equals(nome, usuario.nome) && Objects.equals(endereco, usuario.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, endereco);
    }
}
