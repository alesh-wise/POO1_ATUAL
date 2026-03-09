/**
 * A classe SegmentoReta representa um segmento de reta em um plano bidimensional.
 * Ela pode ser definida através de dois pontos ou por um ponto inicial e um vetor.
 * Além disso, permite realizar operações como calcular o comprimento do segmento,
 * verificar se um ponto está contido nele, ou determinar pontos de interseção com vetores.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 12/02/2026
 * @inv as coordenadas dos pontos devem ser maiores que 0
 */
public class SegmentoReta {

    private Ponto p1, p2;
    private Vetor v1;

    /**
     * Constrói um SegmentoReta usando um ponto inicial e um vetor.
     * O segundo ponto do segmento é calculado adicionando as componentes do vetor
     * às componentes do ponto inicial.
     *
     * @param p1 O ponto inicial do segmento.
     * @param v1 O vetor que define a direção e magnitude do ponto inicial
     *           até o ponto final do segmento.
     */
    SegmentoReta(Ponto p1, Vetor v1) {
        this(p1, new Ponto(p1.x() + v1.x(), p1.y() + v1.y()));

    }

    /**
     * Constrói um segmento de reta utilizando dois pontos.
     * O segmento é definido pelas coordenadas dos pontos fornecidos,
     * o ponto inicial {@code p1} e o ponto final {@code p2}.
     *
     * @param p1 O ponto inicial do segmento de reta.
     * @param p2 O ponto final do segmento de reta.
     */
    SegmentoReta(Ponto p1, Ponto p2) {
        this.p1 = p1;
        this.p2 = p2;
        check();
        this.v1 = new Vetor(p2.x() - p1.x(), p2.y() - p1.y());
    }

    /**
     * Verifica se dois pontos de um segmento de reta são iguais.
     * Caso os pontos {@code p1} e {@code p2} sejam idênticos (considerando a tolerância
     * de igualdade definida para a classe {@code Ponto}), o programa será encerrado com
     * uma mensagem de erro indicando a inconsistência.
     * <p>
     * Este método é utilizado para garantir que o segmento de reta seja válido, uma vez
     * que um segmento de reta formado por dois pontos idênticos não é definido.
     */
    private void check() {
        if (p1.equals(p2)) {
            System.out.println("SegmentoReta:iv");
            System.exit(0);
        }
    }

    /**
     * Calcula o comprimento do segmento de reta representado pela instância.
     * O comprimento do segmento é determinado pelo módulo do vetor associado
     * ao segmento de reta.
     *
     * @return O comprimento do segmento de reta.
     */
    public double comprimento() {
        return this.v1.modulo();
    }

    /**
     * Verifica se um ponto está dentro de um segmento de reta.
     * O método utiliza cálculos vetoriais para determinar se o ponto fornecido
     * está colinear ao segmento e se encontra dentro de seus limites.
     *
     * @param p O ponto a ser verificado.
     * @return true se o ponto estiver dentro do segmento de reta, false caso contrário.
     */
    private boolean noSegmento(Ponto p) {
        Ponto r = p2.subtracao(p1);
        Ponto q = p.subtracao(p1);


        if (Math.abs(r.produtoVetorial(q)) > Ponto.tol) return false; //opcional porque o metodo intersect já verifica

        Vetor vq = new Vetor(q);
        Vetor vr = new Vetor(r);

        return vq.p_interno(vr) >= Ponto.tol && vq.p_interno(vr) <= vr.p_interno(vr);
    }


    /**
     * Calcula o ponto de interseção, se existir, entre este segmento e um vetor.
     * O cálculo envolve verificação de colinearidade, sobreposição ou interseção,
     * dentro dos limites tanto do segmento quanto do vetor.
     *
     * @param v O vetor com o qual será calculada a interseção com o segmento atual.
     * @return O {@code Ponto} representando o ponto de interseção se ele existir, caso contrario retorna null.
     * @see <a href="https://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect"> StackOverflow</a>
     */
    public Ponto intersect(SegmentoReta segv) {


        Ponto r = p2.subtracao(p1);
        Ponto s = segv.p2;
        Ponto qp = segv.p1.subtracao(p1);

        double numerador = qp.produtoVetorial(s);
        double rxs = r.produtoVetorial(s);

        if (Math.abs(rxs) < Ponto.tol && Math.abs(numerador) < Ponto.tol) {
            if (segv.noSegmento(p1)) {
                return p1;
            }
            if (segv.noSegmento(p2)) {
                return p2;
            }
            if (this.noSegmento(segv.p1)) {
                return segv.p1;
            }
            if (this.noSegmento(segv.p2)) {
                return segv.p2;
            }
            return null;
        }

        if (Math.abs(rxs) < Ponto.tol) {
            return null;
        }
        double t = numerador / rxs;
        double u = qp.produtoVetorial(r) / rxs;

        if (u >= 0 && u <= 1 && t >= 0 && t <= 1) {
            double interseta_x = p1.x() + t * r.x();
            double interseta_y = p1.y() + t * r.y();
            return new Ponto(interseta_x, interseta_y);
        }

        return null;
    }

    public Ponto intersect(Vetor v) {
        SegmentoReta segv = new SegmentoReta(new Ponto(0, 0), v);
        return this.intersect(segv);
    }


    /**
     * Retorna uma representação em formato de string do segmento de reta.
     * A representação segue o padrão "sr(p1; p2)", onde os pontos p1 e p2
     * são organizados pela proximidade em relação à origem (0,0). O ponto
     * mais próximo da origem será exibido primeiro.
     *
     * @return Uma string representando o segmento de reta, organizada pelos
     * pontos p1 e p2 em ordem de distância crescente em relação à origem.
     */
    public String toString() {
        if (p1.distance_to(new Ponto(0, 0)) < p2.distance_to(new Ponto(0, 0))) {
            return "sr(" + p1.toString() + "; " + p2.toString() + ")";
        } else {
            return "sr(" + p2.toString() + "; " + p1.toString() + ")";
        }
    }

    public Ponto getP1() {
        return p1;
    }

    public Ponto getP2() {
        return p2;
    }

    public Vetor getV1() {
        return v1;
    }
}



