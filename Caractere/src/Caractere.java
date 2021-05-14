import java.util.*;

public class Caractere {

    public static void encontrarCaracterMaisFrequente(String string){
        char comparador;
        int valor;
        HashMap<Character, Integer> referencia = new HashMap<Character, Integer>();

        for(int i = 0; i< string.length(); i++){
            comparador = string.charAt(i);
            if(referencia.containsKey(comparador)){
                valor = referencia.get(comparador) + 1;
                referencia.put(comparador, valor);
            }
            else{
                referencia.put(comparador, 1);
            }
        }
        valor = Collections.max(referencia.values());
        System.out.printf("Frase: %s\n", string);
        for(char i:referencia.keySet()){
            if(referencia.get(i) == valor){
                System.out.printf("Letra: %c; Repetida: %d vezes.\n", i, valor);
            }
        }
        return;
    }

    public static void main(String[] args) {

        encontrarCaracterMaisFrequente("ararar");
    }
}
