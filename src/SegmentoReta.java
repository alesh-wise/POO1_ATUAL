import java.util.ArrayList;

/**
 * A classe SegmentoReta representa um segmento de reta num plano bidimensional.
 * Ela pode ser definida através de dois pontos ou por um ponto inicial e um vetor.
 * Além disso, permite realizar operações como calcular o comprimento do segmento,
 * verificar se um ponto está contido nele, ou determinar pontos de interseção com vetores.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 12/02/2026
 * @inv as coordenadas dos pontos devem ser maiores que 0
 */
public class SegmentoReta extends FiguraG {

    private Ponto p1, p2;

    /**
     * Constrói um SegmentoReta usando um ponto inicial e um vetor.
     * O segundo ponto do segmento é calculado adicionando as componentes do vetor
     * às componentes do ponto inicial.
     *
     * @param p1 O ponto inicial do segmento.
     * @param v1 O vetor que define a direção e magnitude do ponto inicial
     *           até o ponto final do segmento.
     * @pre p1 deve ser uma instância valida do tipo Ponto
     * @pre v1 deve ser uma instância valido do tipo Vetor
     * @post p1 passa a ser igual ao Ponto p1
     * @post v1 passa a ser igual a p1
     * @post p2 passa a ser igual a p1 + v1
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
     * @pre p1 e p2 devem ser instância validas do tipo Ponto e diferentes
     * @post p1 passa a ser igual ao Ponto p1
     * @post v1 passa a ser igual a p1
     * @post p2 passa a ser igual a p1 + v1
     */
    SegmentoReta(Ponto p1, Ponto p2) {
        this.p1 = p1;
        this.p2 = p2;
        check();
    }

    /**
     * Verifica se dois pontos de um segmento de reta são iguais.
     * Caso os pontos {@code p1} e {@code p2} sejam idênticos (considerando a tolerância
     * de igualdade definida para a classe {@code Ponto}), o programa será encerrado com
     * uma mensagem de erro indicando a inconsistência.
     * <p>
     * Este método é utilizado para garantir que o segmento de reta seja válido, uma vez
     * que um segmento de reta formado por dois pontos idênticos não é definido.
     *
     * @pre p1 e p2 devem estar inicializados
     * @post Caso os pontos sejam iguais, a execução do programa é interrompida
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
        return new Vetor(p2.x() - p1.x(), p2.y() - p1.y()).modulo();
    }

    /**
     * Verifica se um ponto está dentro de um segmento de reta.
     * O método utiliza cálculos vetoriais para determinar se o ponto fornecido
     * está colinear ao segmento e se encontra dentro de seus limites.
     *
     * @param p O ponto a ser verificado.
     * @return true se o ponto estiver dentro do segmento de reta, false caso contrário.
     * @pre p deve ser uma instância valida do tipo Ponto
     */
    public boolean noSegmento(Ponto p) {
        Ponto r = p2.subtracao(p1);
        Ponto q = p.subtracao(p1);


        if (Math.abs(r.produtoVetorial(q)) > Ponto.tol) return false;

        Vetor vq = new Vetor(q);
        Vetor vr = new Vetor(r);

        return vq.p_interno(vr) >= Ponto.tol && vq.p_interno(vr) <= vr.p_interno(vr);
    }


    /**
     * Calcula o ponto de interseção entre o segmento de reta atual e outro segmento de reta fornecido.
     * <p>
     * Se os segmentos se intersectam em um único ponto, esse ponto é retornado.
     * Se os segmentos se sobrepõem (são colineares e possuem interseção), um dos pontos de sobreposição é retornado.
     * Se não houver interseção ou os segmentos forem paralelos e não se sobrepuserem, retorna {@code null}.
     *
     * @param segv O outro segmento de reta a ser usado no cálculo da interseção.
     *             Deve ser uma instância válida de {@code SegmentoReta}.
     * @return O {@code Ponto} correspondente ao ponto de interseção, se existir.
     * Retorna {@code null} se não houver interseção ou se os segmentos forem paralelos sem sobreposição.
     * @pre segv deve ser uma instância válida de SegmentoReta.
     */
    public Ponto intersect(SegmentoReta segv) {
        Ponto r = p2.subtracao(p1);
        Ponto s = segv.p2.subtracao(segv.p1);
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

    /**
     * Calcula o ponto de interseção entre o segmento de reta atual e um vetor fornecido.
     * O método transforma o vetor em um segmento de reta a partir do ponto de origem (0,0)
     * e calcula o ponto de interseção através de sobreposição ou colinearidade, se existente.
     *
     * @param v O vetor usado para calcular a interseção com o segmento de reta atual.
     * @return O {@code Ponto} representando o ponto de interseção, se existir.
     * Retorna {@code null} caso não exista interseção.
     * @pre v deve ser uma instância valida de vetor
     */
    public Ponto intersect(Vetor v) {
        SegmentoReta segv = new SegmentoReta(new Ponto(0, 0), v);
        return this.intersect(segv);
    }

    public ArrayList<Ponto> intersect(Circulo circle) {
        ArrayList<Ponto> intersecoes = new ArrayList<>();

        Vetor d = new Vetor(p2.x() - p1.x(), p2.y() - p1.y());

        Vetor f = new Vetor(p1.subtracao(circle.getCentro()));

        double a = d.p_interno(d);
        double b = 2 * f.p_interno(d);
        double c = f.p_interno(f) - Math.pow(circle.getRaio(), 2);

        double discriminante = (b * b) - 4 * a * c;
        if (a == 0) {
            return null;
        }
        if (discriminante >= 0) {
            discriminante = Math.sqrt(discriminante);
            Ponto first = null, second = null;
            double t1 = (-b + discriminante) / (2 * a);
            if (t1 >= 0 && t1 <= 1) {
                double interx = p1.x() + t1 * d.x();
                double intery = p1.y() + t1 * d.y();
                first = new Ponto(interx, intery);
            }

            double t2 = (-b - discriminante) / (2 * a);
            if (t2 >= 0 && t2 <= 1 && discriminante > 0) {
                double interx = p1.x() + t2 * d.x();
                double intery = p1.y() + t2 * d.y();
                second = new Ponto(interx, intery);
            }
            if (first != null && second != null) {
                if (first.distance_to(p1) < second.distance_to(p1)) {
                    intersecoes.add(first);
                    intersecoes.add(second);
                } else {
                    intersecoes.add(second);
                    intersecoes.add(first);
                }
            }
        }
        return intersecoes;
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
}



