import java.util.HashMap;
import java.util.Map;

public class Turma {

    private Disciplina disciplina;

    private Professor professor;

    private Periodo periodo;

    private Map<Long, Aluno> alunosIncritos = new HashMap<>();

    public Turma(Disciplina disciplina, Professor professor, Periodo periodo){
        this.disciplina = disciplina;
        this.professor = professor;
        this.periodo = periodo;
    }

    void inscreverAluno(Aluno aluno){
        alunosIncritos.put(aluno.getDre(), aluno);
        return;
    }

    void atribuirNotaFinal(long dre, float nota){
        Aluno aluno = alunosIncritos.get(dre);
        if(aluno == null){
            System.out.println("Aluno não inscrito!");
            return;
        }
        aluno.inserirItemHistorico(this.disciplina, nota, this.periodo);
        return;
    }

    float obterMediaFinal(long dre){
        Aluno aluno = alunosIncritos.get(dre);
        if(aluno == null){
            System.out.println("Aluno não inscrito!");
            return 0;
        }

        float nota = aluno.notaDeItemHistorico(this.disciplina, this.periodo);
        if(nota == 0) {
            System.out.println("Nota ainda não atribuida.");
        }
        return nota;
    }

    public String listarAlunos(){
        String resultado = "Lista de alunos:\n";
        for(Aluno aluno : alunosIncritos.values()){
            resultado += aluno.getNome() + " - " + aluno.getDre() + " - " + aluno.notaDeItemHistorico(this.disciplina, this.periodo) + "\n";
        }
        return resultado;
    }

}
