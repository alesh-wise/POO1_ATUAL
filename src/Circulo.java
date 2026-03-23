/**
 * Representa um círculo em um plano bidimensional.
 * <p>
 * Esta classe modela um círculo definido pelos pontos do plano bidimensional
 * que estão equidistantes de um ponto central, denominado "centro".
 * O raio do círculo define a distância entre o centro e qualquer ponto da sua circunferência.
 * A classe herda de FiguraG, podendo usufruir de conceitos gerais de figuras geométricas.
 *
 * @author Alexandre Guerreiro, a88489
 */
public class Circulo extends FiguraG {
    private Ponto centro;
    private double raio;

    /**
     * Construtor que inicializa um círculo com um ponto central e um raio especificados.
     *
     * @param centro O ponto que representa o centro do círculo.
     * @param raio   O valor do raio do círculo, representando a distância entre o centro
     *               e qualquer ponto da circunferência.
     * @pre centro deve ser uma instância valida de Ponto
     * @post raio passa a ser igual ao double raio e centro igual ao ponto centro
     */
    Circulo(Ponto centro, double raio) {
        if (raio <= 0) {
            IO.println("Circulo:iv");
            System.exit(0);
        }
        this.centro = centro;
        this.raio = raio;
    }

    /**
     * Retorna o ponto central do círculo.
     *
     * @return O ponto que representa o centro do círculo.
     */
    Ponto getCentro() {
        return centro;
    }

    /**
     * Retorna o valor do raio do círculo.
     *
     * @return O valor do raio do círculo, que representa a distância entre o centro
     * e qualquer ponto da sua circunferência.
     */
    double getRaio() {
        return raio;
    }
}
