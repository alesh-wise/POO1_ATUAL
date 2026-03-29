import java.util.ArrayList;

/**
 * Uma classe que representa uma Rota, que é uma coleção de pontos (Ponto)
 * conectados sequencialmente. A classe fornece funcionalidades para calcular o
 * comprimento da rota e determinar interseções com um segmento de reta dado.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 12/02/2026
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
     * @pre coordenadas deve ser instancia valida de Double[]
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
     * @pre circle deve ser uma instância válida do tipo Circulo
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

    /**
     * Calcula o tempo total necessário para percorrer a rota a uma velocidade linear constante.
     * O tempo é calculado como a soma dos tempos individuais de deslocamento entre pontos
     * consecutivos na lista de pontos, utilizando a velocidade linear fornecida.
     *
     * @param linearSpeed A velocidade constante com a qual a rota será percorrida.
     *                    Deve ser um valor positivo representando a velocidade.
     * @return O tempo total necessário para percorrer toda a rota.
     * @pre linearSpeed deve ser um valor positivo (linearSpeed > 0).
     */
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

    /**
     * Calcula a posição final de um objeto que se move ao longo da rota com uma velocidade linear constante
     * após um tempo especificado. A rota é composta por uma lista de pontos sequencialmente conectados.
     *
     * @param linearSpeed A velocidade linear constante do objeto. Deve ser um valor positivo.
     * @param totalTime   O tempo total de deslocamento ao longo da rota, expresso em segundos. Deve ser >= 0.
     * @return O ponto final onde o objeto estará após percorrer a rota por {@code totalTime} segundos.
     * @pre linearSpeed > 0, totalTime >= 0.
     */

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

    /**
     * Calcula as velocidades de movimento entre pontos consecutivos na rota,
     * considerando a influência do vento, tempo total e uma velocidade linear especificada.
     * Para cada segmento da rota, calcula-se o tempo necessário para percorrê-lo,
     * ajustando a velocidade com base no vetor do vento fornecido.
     *
     * @param windspeed   Vetor que representa a velocidade e direção do vento, afetando o cálculo da velocidade.
     * @param linearSpeed Velocidade linear constante usada para percorrer a rota, desconsiderando inicialmente o vento. Esta velocidade é ajustada pelo vetor do vento para cada segmento.
     * @return Uma lista de vetores representando as velocidades calculadas
     * (incluindo o impacto do vento) para cada segmento da rota.
     * O tamanho da lista é igual à quantidade de segmentos da rota.
     * @pre windspeed deve ser uma instância válida de Vetor, e linearSpeed > 0.
     */
    public ArrayList<Vetor> speed(Vetor windspeed, double linearSpeed) {
        double timeSeg;
        Ponto p1 = null, p2 = null;
        ArrayList<Vetor> velocidades = new ArrayList<>();
        for (int i = 1; i < this.pontos.size(); i++) {
            p1 = pontos.get(i - 1);
            p2 = pontos.get(i);
            AutoPilot at = new AutoPilot(p1, p2);
            timeSeg = at.time(linearSpeed);
            velocidades.add(at.speed(windspeed, timeSeg));

        }
        return velocidades;
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