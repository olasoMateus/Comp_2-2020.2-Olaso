import java.util.Objects;

/**
 * Representa uma fração de forma explícita, guardando numerador e denominador inteiros.
 * Frações equivalentes (matematicamente) devem ser consideradas equals().
 */
public class Fracao {


    private int numerador, denominador;

    private boolean sinal = true;

    /**
     * Cria uma fração, baseada em seu numerador e denominador.
     * O sinal da fração será inferido a partir do sinal
     * do numerador e do denominador.
     *
     * @param numerador o numerador
     * @param denominador o denominador
     */
    public Fracao(int numerador, int denominador) {
        boolean identificadorDeSinal = true;

        if (denominador == 0) {
            throw new ArithmeticException("Denominador não pode ser zero!!");
        }

        if(numerador < 0) {
            this.numerador = -numerador;
            identificadorDeSinal = !identificadorDeSinal;
        }
        else this.numerador = numerador;


        if(denominador < 0){
            this.denominador = -denominador;
            identificadorDeSinal = !identificadorDeSinal;
        }
        else this.denominador = denominador;

        if(!identificadorDeSinal) this.sinal = false;
        if(this.numerador == 0) this.sinal = true;

        // ToDo: IMPLEMENT ME!!!!
    }

    /**
     * Retorna o sinal da fração.
     *
     * @return true, se for positiva ou nula (zero);
     *         false, se for negativa.
     */
    public boolean getSinal() {
        return this.sinal;  // ToDo: IMPLEMENT ME!!!!
    }

    /**
     * Retorna o (valor absoluto do) numerador desta fração, ou seja, sempre não-negativo
     * @return o numerador
     */
    public int getNumerador() {
        return this.numerador;  // ToDo: IMPLEMENT ME!!!!
    }

    /**
     * Retorna o (valor absoluto do) denominador desta fração, ou seja, sempre positivo
     * @return o numerador
     */
    public int getDenominador() {
        return this.denominador;  // ToDo: IMPLEMENT ME!!!!
    }

    /**
     * Retorna o valor numérico da fração (com o sinal correto).
     *
     * @return um float, com o valor na melhor precisão possível
     *         Ex.: -1/3 vai retornar 0.33333333
     */
    public float getValorNumerico() {
        float resultado = ((float)this.numerador/(float)this.denominador);
        if(this.sinal){
            return resultado;
        }
        return -resultado;
        // ToDo: IMPLEMENT ME!!!!
    }

    /**
     * Retorna uma fração equivalente à esta fração,
     * e que não pode mais ser simplificada (irredutível).
     *
     * @return um outro objeto Fracao, se esta fração for redutível;
     *         esta própria fração (this), se ela já for irredutível
     */
    public Fracao getFracaoGeratriz() {
        int numeradorTemp = this.numerador,
                denominadorTemp = this.denominador,
                mdc = AritmeticaUtils.mdc(numeradorTemp, denominadorTemp);
        if (mdc == 1) return this;
        while(true){
            if(mdc == 1){
                return new Fracao(numeradorTemp, denominadorTemp);
            }
            else{
                numeradorTemp /= mdc;
                denominadorTemp /= mdc;
                mdc = AritmeticaUtils.mdc(numeradorTemp, denominadorTemp);
            }
        }
        // ToDo: IMPLEMENT ME!!!!
    }

    @Override
    public String toString() {
        if(this.denominador == 1 || this.numerador == 0){
            return (sinal? "":"-") + this.numerador;
        }
        return (sinal? "":"-") + this.numerador + "/" + this.denominador;  // ToDo: IMPLEMENT ME!!!!
    }

    @Override
    public boolean equals(Object o) {
        Fracao fracao = (Fracao) o;
        fracao = fracao.getFracaoGeratriz();
        Fracao fracaoReferencia = this.getFracaoGeratriz();
        if(fracaoReferencia.getNumerador() == fracao.getNumerador() &&
                fracaoReferencia.getDenominador() == fracao.getDenominador() &&
                fracaoReferencia.getSinal() == fracao.getSinal()) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerador, denominador, sinal);
    }
}
