import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album<T extends Colecionavel> {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private int  minimoFigurinhasColadasParaAutoCompletar;

    private final Repositorio<T> repositorio;
    private final int quantItensPorPacotinho;

    private List<T> colecionavelColadas;  // direct addressing
    private int quantColecionavelColadas;

    private final String tipoDoRepositorio;

    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private Map<Integer, Integer> contRepetidasByPosicao;

    public Album(Repositorio repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;
        this.tipoDoRepositorio = repositorio.getTipoDeColecionavel();


        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.colecionavelColadas = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.colecionavelColadas.add(null);
        }
        this.quantColecionavelColadas = 0;

        this.contRepetidasByPosicao = new HashMap<>();

        this.minimoFigurinhasColadasParaAutoCompletar =
                (int) (this.repositorio.getTotalColecionaveis() * PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f);
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        T[] colecionaveisDoPacotinho = (T[]) pacotinho.getColecionavel(repositorio.getTipoDeColecionavel());
        if (colecionaveisDoPacotinho.length != this.quantItensPorPacotinho) {
            return;  // melhor ainda: lançaria uma checked exception
        }

        for (T cole : colecionaveisDoPacotinho) {
            final int posicao = cole.getPosicao();
            if (possuiItemColado(posicao)) {
                // adiciona como repetida

                // jeito pior
//                Integer contRepetidas = this.contRepetidasByPosicao.get(posicao);
//                this.contRepetidasByPosicao.put(
//                        posicao, contRepetidas == null ? 1 : contRepetidas + 1);

                // jeito mais elegante: getOrDefault
                int contRepetidas = this.contRepetidasByPosicao.getOrDefault(posicao, 0);
                this.contRepetidasByPosicao.put(posicao, contRepetidas + 1);

            } else {
                // item inédito
                this.colecionavelColadas.set(posicao, cole);
                this.quantColecionavelColadas++;
            }
        }
    }

    public T getItemColado(int posicao) {

        if (this.possuiItemColado(posicao)) return this.colecionavelColadas.get(posicao);

        return null;  // ToDo IMPLEMENT ME!!!
    }

    public boolean possuiItemColado(int posicao) {
        int tamanho = repositorio.getTotalColecionaveis();
        if(posicao > tamanho || posicao <= 0) return false;

        return this.colecionavelColadas.get(posicao) != null;
    }

    public boolean possuiItemRepetido(int posicao) {

        if(posicao > repositorio.getTotalColecionaveis() || posicao <= 0) return false;


        return contRepetidasByPosicao.containsKey(posicao);


    }

    public int getTamanho() {
        return this.repositorio.getTotalColecionaveis();
    }

    public int getQuantItensColados() {
//        int contador = 0;
//        for (Figurinha fig : this.figurinhasColadas) {
//            if (fig != null) {
//                contador++;
//            }
//        }
//        return contador;

        // melhor jeito: atributo!
        return this.quantColecionavelColadas;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        if(quantColecionavelColadas == 0) return;

        if(this.minimoFigurinhasColadasParaAutoCompletar > quantColecionavelColadas) return;

        if(repositorio.getTipoDeColecionavel() == "Figurinha"){
            for(int i = 1; i < colecionavelColadas.size(); i++){
                if(this.colecionavelColadas.get(i) == null){
                    T cole = (T) new Figurinha(i, null);
                    this.colecionavelColadas.set(i, cole);
                    this.quantColecionavelColadas++;
                }
            }
        }
        else if(repositorio.getTipoDeColecionavel() == "Selo"){
            for(int i = 1; i < colecionavelColadas.size(); i++){
                if(this.colecionavelColadas.get(i) == null){
                    T cole = (T) new Selo(i, null);
                    this.colecionavelColadas.set(i, cole);
                    this.quantColecionavelColadas++;
                }
            }
        }
        return;
    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }

    public String getTipoDoRepositorio() {
        return tipoDoRepositorio;
    }

    //    public static void main(String[] args) {
//        ArrayList<Integer> meuArrayList = new ArrayList<>(200);
//
//        // inicializa as posições com nulls, para poder acessá-las diretamente
//        for (int i = 0; i < 200; i++) {
//            meuArrayList.add(null);
//        }
//
////        System.out.println(meuArrayList.get(3));
//
//        meuArrayList.add(3, 300000);  // insert com shift right
//
//        for (int numero : meuArrayList) {
//            System.out.println(numero);
//        }
//    }
}
