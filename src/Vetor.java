/**
 * Representa um vetor bidimensional no plano cartesiano.
 * Esta classe oferece métodos para calcular propriedades e realizar operações
 * vetoriais, tais como módulo, produto interno e cosseno entre vetores.
 * <p>
 * Ao criar um vetor, caso ambos os componentes x e y sejam 0, o programa será
 * terminado imediatamente.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 12/02/2026
 */
public class Vetor {

    private final double x, y;

    /**
     * Construtor da classe Vetor que inicializa um vetor bidimensional no plano cartesiano.
     * Caso ambos os valores de entrada sejam 0, o programa será encerrado com uma mensagem
     * indicando "Vetor:iv".
     *
     * @param x Coordenada x do vetor.
     * @param y Coordenada y do vetor.
     * @post uma instância valida do tipo Vetor, x()==x, y()==y.
     */

    Vetor(double x, double y) {
        if (Math.abs(x) < Ponto.tol && Math.abs(y) < Ponto.tol) {
            System.out.println("Vetor:iv");
            System.exit(0);
        }
        this.x = x;
        this.y = y;

    }

    /**
     * Construtor da classe Vetor que inicializa um vetor bidimensional com base num ponto.
     * O ponto fornecido determina as coordenadas x e y do vetor.
     *
     * @param that O ponto de onde as coordenadas x e y serão atribuídas ao vetor.
     * @pre that deve ser uma instância valida de Ponto
     * @post uma instância valida do tipo Vetor, x()==x,y()==y.
     */

    Vetor(Ponto that) {
        this(that.x(), that.y());
    }

    /**
     * Calcula o módulo do vetor atual no plano cartesiano.
     * O módulo é determinado pela raiz quadrada da soma dos quadrados
     * das coordenadas x e y do vetor.
     *
     * @return O valor do módulo do vetor, como um número real.
     * @post retorna um valor double >0 correspondente ao modulo do vetor.
     */

    public double modulo() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Retorna o valor da coordenada x do vetor atual no plano cartesiano.
     *
     * @return O valor da coordenada x do vetor como um número real.
     * @post retorna um valor double correspondente ao valor exato de x.
     */


    public double x() {
        return this.x;
    }

    /**
     * Retorna o valor da coordenada y do vetor atual no plano cartesiano.
     *
     * @return O valor da coordenada y do vetor como um número real.
     * @post retorna um valor double correspondente ao valor y.
     */

    public double y() {
        return this.y;
    }

    /**
     * Calcula o produto interno entre o vetor atual e o vetor fornecido como parâmetro.
     * O produto interno é definido como a soma dos produtos das componentes correspondentes
     * dos dois vetores.
     *
     * @param b O vetor com o qual o produto interno será calculado.
     * @return O valor do produto interno como um número real.
     * @pre b deve ser uma instância valida do tipo Vetor.
     * @post retorna um double correspondente ao produto interno de dois vetores.
     */

    public double p_interno(Vetor b) {
        return this.x * b.x + this.y * b.y;
    }

    /**
     * Calcula o cosseno do ângulo entre o vetor atual e o vetor fornecido como parâmetro.
     * O cálculo utiliza a fórmula do produto interno dividido pelo produto dos
     * módulos dos dois vetores.
     *
     * @param b O vetor com o qual o cosseno do ângulo será calculado.
     * @return O valor do cosseno do ângulo entre os dois vetores como um número real.
     * @pre b deve ser uma instância valida do tipo Vetor.
     * @post retorna o cosseno do angulo entre os dois vetores.
     */
    public double cossine(Vetor b) {
        return this.p_interno(b) / (this.modulo() * b.modulo());
    }

    /**
     * Verifica a interseção entre o vetor atual e o segmento de reta fornecido.
     *
     * @param v O segmento de reta com o qual será verificada a interseção.
     * @return Um objeto do tipo {@code Ponto} representando o ponto de interseção
     * entre o vetor e o segmento de reta fornecidos, ou {@code null} caso
     * não haja interseção.
     * @pre v deve ser uma instância valida do tipo SegmentoReta
     * @post retorna uma nova instância do tipo Ponto que representa a interseção ou caso não haja interseção, null.
     */
    public Ponto intersect(SegmentoReta v) {
        return v.intersect(this);
    }

    /**
     * Multiplica as coordenadas do vetor atual pelo escalar fornecido.
     *
     * @param d O valor escalar pelo qual as coordenadas do vetor serão multiplicadas.
     * @return Um novo objeto {@code Vetor} resultante da multiplicação das coordenadas
     * do vetor atual pelo escalar fornecido.
     * @post retorna uma nova instância do tipo Vetor correspondente a multiplicação escalar
     */
    public Vetor mult(double d) {
        return new Vetor(this.x * d, this.y * d);
    }

    /**
     * Adiciona o vetor fornecido ao vetor atual, compondo um novo vetor
     * resultante da soma das respetivas coordenadas x e y.
     *
     * @param v O vetor a ser adicionado ao vetor atual.
     * @return Um novo objeto {@code Vetor} representando a soma do vetor atual e do vetor v
     * @pre v deve ser uma instância valida do tipo Vetor
     * @post retorna uma nova instância do tipo Vetor correspondente a soma entre os vetores
     */
    public Vetor add(Vetor v) {
        return new Vetor(this.x + v.x, this.y + v.y);
    }

    /**
     * Subtrai o vetor fornecido do vetor atual.
     *
     * @param v O vetor a ser subtraído do vetor atual.
     * @return Um novo objeto {@code Vetor} representando o resultado da subtração
     * das coordenadas do vetor atual e do vetor fornecido.
     * @pre v deve ser uma instância valida do tipo Vetor
     * @post retorna uma nova instância do tipo Vetor correspondente a subtração entre vetores
     */
    public Vetor sub(Vetor v) {
        return new Vetor(this.x - v.x, this.y - v.y);
    }

    /**
     * Retorna uma representação em formato de string das coordenadas do vetor bidimensional.
     * A representação segue o formato "[x, y]", onde x e y são valores formatados
     * com duas casas decimais de precisão.
     *
     * @return Uma string que representa o vetor no formato "[x, y]".
     * @post retorna uma string com o formato"[x,y]", usando precisão de duas casas
     */
    public String toString() {
        return String.format(java.util.Locale.US, "[%.2f,%.2f]", x, y);
    }


}

