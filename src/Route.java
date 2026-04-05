import java.util.ArrayList;

/**
 * Uma classe que representa uma Rota, que é uma coleção de pontos (Ponto)
 * conectados sequencialmente. A classe fornece funcionalidades para calcular o
 * comprimento da rota e determinar interseções com um segmento de reta dado.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 05/04/2026
 * @inv o numéro de coordenadas recebidas deve ser um numéro par
 */
public class Route {

    private ArrayList<Ponto> pontos;


    /**
     * Inicializa uma rota a partir de uma lista de coordenadas fornecida.
     * Cada par consecutivo de valores na lista de coordenadas representa
     * as coordenadas x e y de um ponto, que será adicionado à rota.
     * Caso a quantidade de coordenadas fornecida seja ímpar, o programa será
     * encerrado com uma mensagem de erro.
     *
     * @param coordenadas Um array contendo as coordenadas dos pontos que compõem
     *                    a rota. Cada ponto é definido por dois valores consecutivos
     *                    (x e y).
     * @pre {@code coordenadas} deve ser instancia valida de {@code Double[]}.
     * @post pontos() != null
     */
    Route(Double[] coordenadas) {
        if (coordenadas.length % 2 != 0) {
            IO.println("Rota:iv");
            System.exit(0);
        }
        this.pontos = new ArrayList<>();
        for (int i = 1; i < coordenadas.length; i += 2) {
            pontos.add(new Ponto(coordenadas[i - 1], coordenadas[i]));
        }
    }


    /**
     * Calcula o comprimento total da rota representada pela lista de pontos {@code pontos}.
     * O comprimento é calculado como a soma das distâncias Euclidianas entre pontos consecutivos na lista.
     *
     * @return O comprimento total da rota.
     */
    public double comprimento() {
        double comprimento = 0;
        for (int i = 1; i < pontos.size(); i++) {
            comprimento += pontos.get(i - 1).distance_to(pontos.get(i));
        }
        return comprimento;
    }

    /**
     * Calcula os pontos de interseção entre um segmento de reta dado e os segmentos
     * formados pelos pontos sequenciais da rota.
     *
     * @param sg O segmento de reta que será testado para interseção com os segmentos
     *           formados pelos pontos da rota.
     * @return Uma lista de pontos que representam as interseções encontradas. A lista
     * retornada pode estar vazia se nenhuma interseção for encontrada.
     * @pre {@code sg} deve ser uma instância valida do tipo {@code SegmentoReta}.
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
     * Calcula os pontos de interseção entre a rota representada pelos segmentos formados pelos
     * pontos da classe atual e os segmentos que compõem o polígono fornecido.
     *
     * @param p O polígono cujos segmentos serão testados para interseção com os segmentos
     *          formados pelos pontos da rota.
     * @return Uma lista contendo os pontos de interseção encontrados entre os segmentos
     * da rota e os segmentos do polígono. A lista pode estar vazia se nenhuma interseção for encontrada.
     * @pre {@code p} deve ser uma instância valida do tipo {@code Poligono}.
     */
    public ArrayList<Ponto> intersect(Poligono p) {
        ArrayList<Ponto> pontosIntersect = new ArrayList<>();
        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta segmento_rota = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
            for (int j = 0; j < p.vertices().length; j++) {
                Ponto intersecao = segmento_rota.intersect(new SegmentoReta(p.vertices()[j], p.vertices()[(j + 1) % p.vertices().length]));
                if (intersecao != null && !pontosIntersect.contains(intersecao)) {
                    pontosIntersect.add(intersecao);
                }

            }
        }
        return pontosIntersect;
    }

    /**
     * Calcula os pontos de interseção entre um círculo fornecido e os segmentos
     * formados pelos pontos sequenciais da rota representada pela instância atual.
     *
     * @param circle O círculo para o qual os segmentos serão testados para interseção.
     * @return Uma lista contendo os pontos de interseção encontrados entre o círculo e os
     * segmentos da rota. A lista retornada pode estar vazia se nenhuma interseção for encontrada.
     * @pre {@code circle} deve ser uma instância válida do tipo {@code Circulo}.
     */
    public ArrayList<Ponto> intersect(Circulo circle) {
        ArrayList<Ponto> pontosIntersect = new ArrayList<>();
        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta segRota = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
            ArrayList<Ponto> temp = segRota.intersect(circle);
            if (temp != null) {
                pontosIntersect.addAll(temp);
            }
        }
        return pontosIntersect;
    }

    /**
     * Obtém a lista de pontos que compõem a rota.
     *
     * @return Uma lista de objetos do tipo {@code Ponto} que representa os pontos que definem a rota.
     */
    public ArrayList<Ponto> pontos() {
        return this.pontos;
    }


}