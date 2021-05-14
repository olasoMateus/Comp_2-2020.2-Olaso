import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class UsuarioTest {

    private CalculadorIntersecaoEsperto esperto;
    private CalculadorIntersecaoIngenuo ingenuo;
    private CalculadorIntersecaoViaBuscaBinaria binario;
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Usuario usuario4;

    @Before
    public void setUp(){
        esperto = new CalculadorIntersecaoEsperto();
        ingenuo = new CalculadorIntersecaoIngenuo();
        binario = new CalculadorIntersecaoViaBuscaBinaria();
        usuario1 = new Usuario(1, esperto);
        usuario2 = new Usuario(2, ingenuo);
        usuario3 = new Usuario(3, binario);
        usuario4 = new Usuario(4, ingenuo);
        Usuario usuarioBase;
        for(int i = 5; i <= 10_000; i++){
            usuarioBase = new Usuario(i, ingenuo);
            if(i % 2 == 0){
                usuario1.putAmigos(usuarioBase);
            }
            if(i % 3 == 0){
                usuario2.putAmigos(usuarioBase);
            }
            if(i % 6 == 0){
                usuario3.putAmigos(usuarioBase);
            }
            usuario4.putAmigos(usuarioBase);
        }
    }

    @Test
    public void testarTamanhoDaIntersecaoDeAmigosDosTresCalculadores(){

        //1_666 é o número de números dívisiveis por 6 de 5 a 10_000, tendo usuario3 amigos compostos com
        // id mútiplos de 6 acima de 5 e usuario4 todos os ids de 5 à 10_000
        assertEquals("Eles devem ter o mesmo número de amigos", 1666, usuario3.obterQuantidadeDeAmigosEmComum(usuario4));
        //4_998 é o número de números dívisiveis por 2 de 5 a 100_000, tendo usuario1 amigos compostos com
        // id mútiplos de 2 acima de 5 e usuario4 todos os ids de 5 à 10_000
        assertEquals("Eles devem ter o mesmo número de amigos", 4998, usuario1.obterQuantidadeDeAmigosEmComum(usuario4));

    }

    @Test
    public void testarCalculadoresDeIntersecoes(){

        long inicio = System.currentTimeMillis();
        usuario1.obterQuantidadeDeAmigosEmComum(usuario3);
        long ending = System.currentTimeMillis();

        float time1 = (float) ((ending - inicio)/ 1000.0);

        inicio = System.currentTimeMillis();
        usuario3.obterQuantidadeDeAmigosEmComum(usuario1);
        ending = System.currentTimeMillis();

        float time2 = (float) ((ending - inicio)/ 1000.0);

        System.out.printf("Comparação entre:\nTempo com o calculador esperto: %.9f\nTempo com o calculador binário: %.9f\n\n", time1, time2);

        inicio = System.currentTimeMillis();
        usuario2.obterQuantidadeDeAmigosEmComum(usuario3);
        ending = System.currentTimeMillis();

        time1 = (float) ((ending - inicio)/ 1000.0);


        inicio = System.currentTimeMillis();
        usuario3.obterQuantidadeDeAmigosEmComum(usuario2);
        ending = System.currentTimeMillis();

        time2 = (float) ((ending - inicio)/ 1000.0);

        System.out.printf("Comparação entre:\nTempo com o calculador ingenuo: %.9f\nTempo com o calculador binário: %.9f\n\n", time1, time2);

        inicio = System.currentTimeMillis();
        usuario1.obterQuantidadeDeAmigosEmComum(usuario4);
        ending = System.currentTimeMillis();

        time1 = (float) ((ending - inicio)/ 1000.0);


        inicio = System.currentTimeMillis();
        usuario4.obterQuantidadeDeAmigosEmComum(usuario1);
        ending = System.currentTimeMillis();

        time2 = (float) ((ending - inicio)/ 1000.0);

        System.out.printf("Comparação entre:\nTempo com o calculador esperto: %.9f\nTempo com o calculador ingenuo: %.9f\n\n", time1, time2);

    }


}