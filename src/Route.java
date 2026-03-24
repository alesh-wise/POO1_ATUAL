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
     * @post this.pontos passa a ser igual ao ArrayList pontos
     */
    Route(ArrayList<Ponto> pontos) {
        this.pontos = pontos;
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
     * segmentos da rota. A lista retornada pode estar vazia se nenhuma interseção
     * for encontrada.
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

    public double timeRoute(double linearSpeed) {
        double time = 0;
        for (int i = 1; i < this.pontos.size(); i++) {
            Ponto p1 = pontos.get(i - 1);
            Ponto p2 = pontos.get(i);
            AutoPilot at = new AutoPilot(p1, p2);
            time += at.time(linearSpeed);
        }
        return time;
    }

    public Ponto posicaoFinal(double linearSpeed, double totalTime) {
        Ponto f;
        double timeSeg;
        Ponto p1 = null, p2 = null;
        for (int i = 1; i < this.pontos.size(); i++) {
            p1 = pontos.get(i - 1);
            p2 = pontos.get(i);
            AutoPilot at = new AutoPilot(p1, p2);
            timeSeg = at.time(linearSpeed);
            if (totalTime < timeSeg || Math.abs(totalTime - timeSeg) < Ponto.tol) {
                break;
            }
            totalTime -= timeSeg;
        }
        double deslocamento = totalTime * linearSpeed;
        Vetor direcao = new Vetor(new Ponto(p2.x() - p1.x(), p2.y() - p1.y()));
        double m = direcao.modulo();
        Vetor u = new Vetor(direcao.x() / m, direcao.y() / m);
        f = new Ponto(p1.x() + u.x() * deslocamento, p1.y() + u.y() * deslocamento);
        return f;
    }

    public ArrayList<Vetor> speed(Vetor windspeed, double totalTime, double linearSpeed) {
        double timeSeg;
        Ponto p1 = null, p2 = null;
        ArrayList<Vetor> velocidades = new ArrayList<>();
        for (int i = 1; i < this.pontos.size(); i++) {
            p1 = pontos.get(i - 1);
            p2 = pontos.get(i);
            AutoPilot at = new AutoPilot(p1, p2);
            timeSeg = at.time(linearSpeed);
            velocidades.add(at.speed(windspeed, timeSeg));
            if (totalTime < timeSeg || Math.abs(totalTime - timeSeg) < Ponto.tol) {
                break;
            }
            totalTime -= timeSeg;

        }
        return velocidades;
    }
}
