import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Siguinha {

    public final static float MEDIA_MINIMA_PARA_APROVACAO = 5.0f;

    private static Periodo periodoCorrente = null;

    private Map<Long, Aluno> alunos = new HashMap<>();

    String instituicaoDeEnsino;

    public static int obterAnoCorrente() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private static int obterSemestreCorrente() {
        return obterMesCorrente() <= 6 ? 1 : 2;
    }

    public static int obterMesCorrente() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static Periodo obterPeriodoCorrente() {

        if (periodoCorrente != null) {
            if (periodoCorrente.getAno() != obterAnoCorrente() ||
                    periodoCorrente.getSemestre() != obterSemestreCorrente()) {
                periodoCorrente = null;  // invalida o cache
            }
        }

        if (periodoCorrente == null) {  // verifica o memo ("cache")
            // atualiza o cache
            periodoCorrente = new Periodo(obterAnoCorrente(), obterSemestreCorrente());
        }

        return periodoCorrente;
    }

    void cadastrarAluno(long dre, String nome, int anoDeNascimento){
        Aluno aluno = new Aluno(dre, nome, anoDeNascimento);
        alunos.put(dre, aluno);
        return;
    }

    Aluno obterAluno(long dre){
        Aluno aluno = alunos.get(dre);
        return aluno == null ? null : aluno;
    }

    // apenas para escrever testes rápidos, por ora
    public static void main(String[] args) {

        HashMap<Integer, String> numerosPorExtenso = new HashMap<>();
        numerosPorExtenso.put(1, "um");
        numerosPorExtenso.put(2, "dois");
        numerosPorExtenso.put(3, "três");
        numerosPorExtenso.put(4, "quatro");

        System.out.println(numerosPorExtenso.get(2));
        System.out.println(numerosPorExtenso.get(600));

        // formar de iterar pelo mapa
        for (Integer chave : numerosPorExtenso.keySet()) {
            System.out.println(chave);
        }

        // formar de iterar pelo mapa
        for (String valor : numerosPorExtenso.values()) {
            System.out.println(valor);
        }

        for (Map.Entry<Integer, String> parChaveValor : numerosPorExtenso.entrySet()) {
            Integer chave = parChaveValor.getKey();
            String valor = parChaveValor.getValue();
            System.out.println(chave + " ---> " + valor);
        }

    }
}
