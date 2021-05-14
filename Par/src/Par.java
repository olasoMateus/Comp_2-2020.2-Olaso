import java.util.*;

public class Par {



    public static void encontrarPar(List<Integer> lista, int k){
        int numeroReferencia = 0;
        HashSet<Integer> referencia = new HashSet<Integer>();

        for(int num : lista){
            referencia.add(num);
        }

        for(int num:lista){
            numeroReferencia = k - num;
            if(referencia.contains(numeroReferencia)){
                if(num < k) System.out.printf("%d+%d = %d\n",num, numeroReferencia,k);
                else System.out.printf("%d%d = %d\n",num, numeroReferencia,k);
            }
        }

        return;

    }

    public static void main(String[] args) {

        List<Integer> lista = new ArrayList<Integer>();
        lista.add(1);
        lista.add(5);
        lista.add(-10);
        lista.add(56);
        lista.add(44);
        lista.add(12);
        lista.add(18);
        lista.add(16);
        lista.add(-22);

        Par.encontrarPar(lista, 34);

        return;
    }

}
