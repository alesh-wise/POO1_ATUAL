/**
 * A classe AutoPilot fornece métodos para calcular a velocidade de um objeto
 * e o tempo necessário para percorrer uma dada trajetória considerando influências
 * externas como a velocidade do vento. A trajetória é definida por um vetor que
 * conecta dois pontos.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 10/03/2026
 * @inv o vetor r não pode ser nulo.
 */
public class AutoPilot {

    private final Vetor r;

    /**
     * Constrói uma instância de AutoPilot definindo a trajetória como um vetor que conecta
     * dois pontos especificados.
     *
     * @param a O ponto inicial da trajetória.
     * @param b O ponto final da trajetória.
     * @pre a e b devem ser instâncias validas do tipo Ponto
     * @post this.r passa a ser igual ao vetor b-a
     */
    public AutoPilot(Ponto a, Ponto b) {
        this.r = new Vetor(b.x() - a.x(), b.y() - a.y());
    }

    /**
     * Calcula o vetor velocidade resultante de um objeto considerando a influência
     * da velocidade do vento e o intervalo de tempo.
     *
     * @param windSpeed Um {@code Vetor} representando a velocidade do vento que atua contra o movimento do objeto.
     * @param time      A duração do tempo sobre o qual a velocidade do objeto é calculada, como um double.
     * @return Um {@code Vetor} representando o vetor velocidade do objeto após considerar
     * a velocidade do vento e o intervalo de tempo especificado.
     * @pre windSpeed deve ser uma instância valida do tipo Vetor e time >0
     */
    public Vetor speed(Vetor windSpeed, double time) {
        return r.sub(windSpeed).mult(1 / time);
    }

    /**
     * Calcula o tempo necessário para um objeto percorrer uma trajetória
     * considerando a influência da velocidade do vento e sua velocidade linear.
     *
     * @param windSpeed   Um {@code Vetor} representando a velocidade do vento que atua contra o objeto.
     * @param linearSpeed Um {@code double} representando a velocidade linear do objeto.
     * @return Um {@code double} representando o tempo necessário para completar a trajetória.
     * @pre windSpeed deve ser uma instância valida do tipo Vetor e linear >0
     */
    public double time(Vetor windSpeed, double linearSpeed) {
        return r.sub(windSpeed).modulo() / linearSpeed;
    }
}
