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
     * @pre ArrayList pontos não vazio
     * @post Uma instância valida de Route
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
     * @pre sg valido
     *
     */
    public void intersect(SegmentoReta sg) {

        boolean exit = false;
        ArrayList<Ponto> pontosIntersect = new ArrayList<>();

        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta temp = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
            Ponto intersect = temp.intersect(sg);
            if (intersect != null) {
                pontosIntersect.add(intersect);
                exit = true;
            }
        }
        if (!exit) {
            IO.println("null");
        } else {
            for (int i = 0; i < pontosIntersect.size(); i++) {
                IO.print(pontosIntersect.get(i).toString());
                if (i < pontosIntersect.size() - 1) {
                    IO.print(" ");
                }
            }
            IO.print("\n");
        }

    }


    public double comprimento() {
        double comprimento = 0;
        for (int i = 1; i < pontos.size(); i++) {
            comprimento += pontos.get(i - 1).distance_to(pontos.get(i));
        }
        return comprimento;
    }

}
