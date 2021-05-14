import java.util.ArrayList;
import java.util.List;

public class CalculadorIntersecaoIngenuo implements CalculadorIntersecao {

    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {

        List<Usuario> resultado = new ArrayList<Usuario>();

        for(Usuario usuario : lista1){
            if(lista2.contains(usuario)){
                resultado.add(usuario);// para cada elemento da primeira lista, percorra a segunda lista até encontrá-lo (ou não)
            }
        }

        return resultado;
    }
}
