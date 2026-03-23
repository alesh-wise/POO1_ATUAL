/**
 * Representa um ponto bidimensional no plano cartesiano.
 * A classe oferece métodos para manipulação e cálculo envolvendo coordenadas
 * do ponto, como obtenção de valores, cálculo de distância, comparação e operações matemáticas.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 10/03/2026
 */
public class Ponto {
    public static final double tol = 1e-9;
    private double x, y;

    /**
     * Construtor que inicializa um ponto com as coordenadas especificadas.
     *
     * @param x Coordenada x do ponto.
     * @param y Coordenada y do ponto.
     * @post this.x passa a ser igual x e this.y passa a ser igual ao y
     *
     */
    Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter para obter o valor da coordenada x
     *
     * @return Retorna o valor da coordenada x
     */
    double x() {
        return x;
    }

    /**
     * Getter para obter o valor da coordenada y
     *
     * @return Retorna o valor da coordenada y
     */
    double y() {
        return y;
    }

    /**
     * Retorna uma representação do ponto com o formato "(x,y)"
     *
     * @return Retorna uma string com o formato desejado
     * <p>
     */
    @Override
    public String toString() {
        return "(" + String.format("%.2f", x) + "," + String.format("%.2f", y) + ")";
    }

    /**
     * Calcula a distância Euclidiana deste ponto até outro ponto.
     *
     * @param that O ponto até o qual a distância será calculada.
     * @return A distância entre este ponto e o ponto that especificado.
     * @pre that deve ser uma instância valida de Ponto (that != null).
     */
    public double distance_to(Ponto that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

    /**
     * Determina se o objeto especificado é igual à instância atual.
     * Dois pontos são considerados iguais se sua distância Euclidiana estiver dentro
     * de uma tolerância predefinida 1E-9.
     *
     * @param o O objeto a ser comparado com a instância atual.
     * @return true se o objeto especificado for um {@code Ponto} e for
     * igual à instância atual dentro da tolerância;
     * caso contrário, false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ponto ponto = (Ponto) o;

        return this.distance_to(ponto) < tol;
    }

    /**
     * Subtrai as coordenadas do ponto especificado do ponto atual.
     *
     * @param p2 o ponto cujas coordenadas serão subtraídas.
     * @return uma nova instância de {@code Ponto} com o resultado da subtração.
     * @pre p2 deve ser uma instância valida do tipo Ponto.
     */
    public Ponto subtracao(Ponto p2) {
        return new Ponto(this.x() - p2.x(), this.y() - p2.y());
    }

    /**
     * Calcula o produto vetorial entre o ponto atual e o ponto fornecido.
     * O produto vetorial é uma operação matemática que, no caso de vetores bidimensionais,
     * resulta em um escalar interpretável como a magnitude de um vetor perpendicular ao plano.
     *
     * @param p2 O ponto com o qual o produto vetorial será calculado.
     * @return O resultado do produto vetorial entre o ponto atual e o ponto fornecido.
     * @pre p2 deve ser uma instância valida do tipo Ponto.
     */
    public double produtoVetorial(Ponto p2) {
        return this.x * p2.y() - this.y * p2.x();
    }
}
