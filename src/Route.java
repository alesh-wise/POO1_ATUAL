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
    public ArrayList<Ponto> intersect(SegmentoReta sg) {

        ArrayList<Ponto> pontosIntersect = new ArrayList<>();

        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta temp = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
            Ponto intersect = temp.intersect(sg);
            if (intersect != null) {
                pontosIntersect.add(intersect);

            }
        }
        return pontosIntersect;
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


    public ArrayList<Ponto> intersect(FiguraG fig) {
        ArrayList<Ponto> pontosIntersect = new ArrayList<>();
        if (fig instanceof Poligono p) {
            pontosIntersect = new ArrayList<>();
            for (int i = 1; i < pontos.size(); i++) {
                SegmentoReta segmento_rota = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
                for (int j = 1; j < p.vertices().length; j++) {
                    Ponto intersecao = segmento_rota.intersect(new SegmentoReta(p.vertices()[j - 1], p.vertices()[j]));
                    if (intersecao != null) {
                        pontosIntersect.add(intersecao);
                    }
                }
            }
        } else if (fig instanceof Circulo circle) {
            pontosIntersect = new ArrayList<>();
            for (int i = 1; i < pontos.size(); i++) {
                SegmentoReta segRota = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
                ArrayList<Ponto> temp = segRota.intersect(circle);
                if (temp != null) {
                    pontosIntersect.addAll(temp);
                }
            }

        }
        return pontosIntersect;
    }

}
