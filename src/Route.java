import java.util.ArrayList;

/**
 * Uma classe que representa uma Rota, que é uma coleção de pontos (Ponto)
 * conectados sequencialmente. A classe fornece funcionalidades para calcular o
 * comprimento da rota e determinar interseções com um segmento de reta dado.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 12/02/2026
 */
public class Route {

    private ArrayList<Ponto> pontos;

    /**
     * Constrói uma nova instância da classe Route com uma lista de pontos especificada.
     *
     * @param pontos Uma lista de objetos {@code Ponto} que representam os pontos sequenciais
     *               que compõem a rota.
     * @pre ArrayList pontos com size >=2
     * @post Uma instância valida de Route.
     */
    Route(ArrayList<Ponto> pontos) {
        this.pontos = pontos;
    }

    /**
     * Identifica e processa os pontos de interseção entre os segmentos da rota e um
     * dado {@code SegmentoReta}. O resultado é impresso, mostrando os pontos de
     * interseção ou indicando que não há interseções.
     *
     * @param sg O {@code SegmentoReta} com o qual os segmentos da rota serão
     *           testados para interseção.
     * @pre sg deve ser uma instância valida de SegmentoReta.
     * @post Imprime na consola os pontos separados por espaço, ou "null" caso não existam interseções.
     */
    public void intersect(SegmentoReta sg) {

        ArrayList<Ponto> pontosIntersect = new ArrayList<>();

        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta temp = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
            Ponto intersect = temp.intersect(sg);
            if (intersect != null) {
                pontosIntersect.add(intersect);

            }
        }
        printPoints(pontosIntersect);
    }

    /**
     * Calcula o comprimento total da rota representada pela lista de pontos {@code pontos}.
     * O comprimento é calculado como a soma das distâncias Euclidianas entre pontos consecutivos na lista.
     *
     * @return O comprimento total da rota.
     * @post Retorna um valor double >=0 correspondente ao comprimento da rota.
     */
    public double comprimento() {
        double comprimento = 0;
        for (int i = 1; i < pontos.size(); i++) {
            comprimento += pontos.get(i - 1).distance_to(pontos.get(i));
        }
        return comprimento;
    }


    public void intersect(Poligono p) {

        ArrayList<Ponto> pontosIntersect = new ArrayList<>();
        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta segmento_rota = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
            for (int j = 1; j < p.vertices().length; j++) {
                Ponto intersecao = segmento_rota.intersect(new SegmentoReta(p.vertices()[j - 1], p.vertices()[j]));
                if (intersecao != null) {
                    pontosIntersect.add(intersecao);
                }
            }
        }
        printPoints(pontosIntersect);
    }

    public void intersect(Circulo circle) {

        ArrayList<Ponto> pontosIntersect = new ArrayList<>();
        for (int i = 1; i < pontos.size(); i++) {
            Ponto first = pontos.get(i - 1);
            Ponto second = pontos.get(i);

            double a = first.y() - second.y();
            double b = second.x() - first.x();
            double c = (first.x() * second.y()) - (second.x() * first.y());

            double a_resolvente = Math.pow(b / a, 2) + 1;
            double b_resolvente = 2 * c * b / a - 2 * circle.getCentro().x() * b / a - 2 * circle.getCentro().y();
            double c_resolvente = Math.pow(c / a, 2) - 2 * circle.getCentro().x() * c / a + Math.pow(circle.getCentro().x(), 2) + Math.pow(circle.getCentro().y(), 2) - Math.pow(circle.getRaio(), 2);

            double discriminante = Math.pow(b_resolvente, 2) - 4 * a_resolvente * c_resolvente;
            if (discriminante > 0) {
                double x1 = ((1 - b_resolvente) + Math.sqrt(discriminante)) / (2 * a_resolvente);
                double x2 = ((1 - b_resolvente) - Math.sqrt(discriminante)) / (2 * a_resolvente);

                double y1 = c / b - a * x1 / b;
                double y2 = c / b - a * x2 / b;
                pontosIntersect.add(new Ponto(x1, y1));
                pontosIntersect.add(new Ponto(x2, y2));
            }
            if (discriminante == 0) {
                double x1 = (1 - b_resolvente) / (2 * a_resolvente);
                double y1 = c / b - a * x1 / b;
                pontosIntersect.add(new Ponto(x1, y1));
            }

        }
        printPoints(pontosIntersect);
    }

    public void printPoints(ArrayList<Ponto> pontos) {
        if (pontos.isEmpty()) {
            IO.println("null");
        } else {
            for (int i = 0; i < pontos.size(); i++) {
                IO.print(pontos.get(i).toString());
                if (i == pontos.size() - 1) {
                    IO.println("\n");
                } else {
                    IO.print(" ");
                }
            }

        }

    }

}
