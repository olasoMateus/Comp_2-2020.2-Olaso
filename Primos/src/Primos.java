public class Primos {

    public static int[] obterPrimosViaCrivo(int n){
        int[] num, primos;
        int numeroDePrimos = 0, contador = 0;

        num = new int[n];

        for(int i = 0; i < n;i++){
            num[i] = i + 1;
        }

        for(int i = 0; i < n; i++){
            if (i == 0) num[i] = 0;
            if (num[i] != 0){
                numeroDePrimos++;
                for(int cont = 2*num[i] - 1; cont < n; cont+=num[i]){
                    num[cont] = 0;
                }
            }
        }

        primos = new int[numeroDePrimos];

        for(int i = 0; i < n;i++){
            if(num[i] != 0){
                primos[contador] = num[i];
                contador++;
            }
        }

        return primos;

    }

    public static int[] obterPrimos(int n){
        int[] num, primos;
        int numeroDePrimos = 0, contador = 0, raiz = 0;

        num = new int[n];

        for(int i = 0; i < n;i++){
            num[i] = i + 1;
        }

        num[0] = 0;

        for(int i = 1; i < n;i++){
            raiz = (int) Math.sqrt(num[i]);
            numeroDePrimos++;
            for(int cont = 2; cont<=raiz;cont++){
                if(num[i]%cont == 0) {
                    numeroDePrimos--;
                    num[i] = 0;
                    break;
                }
            }
        }

        primos = new int[numeroDePrimos];

        for(int i = 0; i < n;i++){
            if(num[i] != 0){
                primos[contador] = num[i];
                contador++;
            }
        }

        return primos;
    }



    public static void main(String[] args) {

        int n;
        int[] primosCrivo, primosNormal;
        long inicio, tempoObterPrimosViaCrivo, tempoObterPrimosNormalmente;

        for(n = 10;n <= 10_000;n++){
            inicio = System.currentTimeMillis();
            primosCrivo = obterPrimosViaCrivo(n);
            tempoObterPrimosViaCrivo = System.currentTimeMillis() - inicio;
            inicio = System.currentTimeMillis();
            primosNormal = obterPrimos(n);
            tempoObterPrimosNormalmente = System.currentTimeMillis() - inicio;
            System.out.printf("\nAnalisando primos até %d:\n", n);
            System.out.printf("Tempo crivo: %.9f; Número de primos crivo: %d\nTempo normal: %.9f; Número de primos normal: %d\n",
                    tempoObterPrimosViaCrivo/1000.0, primosCrivo.length,
                    tempoObterPrimosNormalmente/1000.0, primosNormal.length);
        }




    }
}
