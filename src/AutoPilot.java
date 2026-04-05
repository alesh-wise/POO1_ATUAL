import java.util.ArrayList;

/**
 * A classe AutoPilot fornece métodos para calcular a velocidade de um objeto
 * e o tempo necessário para percorrer uma dada trajetória considerando influências
 * externas como a velocidade do vento. A trajetória é definida por um vetor que
 * conecta dois pontos.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 05/04/2026
 * @inv o vetor r não pode ser nulo.
 */
public class AutoPilot {

    private ArrayList<Vetor> vetores;
    private ArrayList<Ponto> pontos;
    private Vetor r;

    /**
     * Constrói uma instância de AutoPilot definindo a trajetória como um vetor que conecta
     * dois pontos especificados.
     *
     * @param a O ponto inicial da trajetória.
     * @param b O ponto final da trajetória.
     * @pre a e b devem ser instâncias validas do tipo Ponto
     * @post this.r passa a ser igual ao vetor b-a
     * @post pontos contem o ponto a e b
     * @post vetores contem o vetor r
     */
    public AutoPilot(Ponto a, Ponto b) {
        pontos = new ArrayList<>();
        vetores = new ArrayList<>();
        pontos.add(a);
        pontos.add(b);
        r = new Vetor(b.x() - a.x(), b.y() - a.y());
        vetores.add(r);
    }

    /**
     * Constrói uma instância de AutoPilot definindo a trajetória com base em uma rota fornecida.
     * A trajetória é representada como uma série de vetores que conectam pontos consecutivos da rota.
     *
     * @param rota A rota consiste em uma coleção de pontos que definem a trajetória.
     * @pre {@code rota} deve ser uma instância valida de {@code Route}.
     * @post this.r passa a ser igual ao vetor de {@code vetores}.
     * @post pontos contem de {@code rota}.
     * @post vetores contem os vetores formados por cada segmento da rota
     */
    public AutoPilot(Route rota) {
        pontos = new ArrayList<>();
        vetores = new ArrayList<>();
        ArrayList<Ponto> pontosRota = rota.pontos();
        pontos.add(pontosRota.get(0));
        for (int i = 1; i < pontosRota.size(); i++) {
            vetores.add(new Vetor(pontosRota.get(i).x() - pontosRota.get(i - 1).x(), pontosRota.get(i).y() - pontosRota.get(i - 1).y()));
            pontos.add(pontosRota.get(i));
        }
        r = vetores.get(0);
    }

    /**
     * Calcula o vetor velocidade resultante de um objeto considerando a influência
     * da velocidade do vento e o intervalo de tempo.
     *
     * @param windSpeed Um {@code Vetor} representando a velocidade do vento que atua contra o movimento do objeto.
     * @param time      A duração do tempo sobre o qual a velocidade do objeto é calculada, como um double.
     * @return Um {@code Vetor} representando o vetor velocidade do objeto após considerar
     * a velocidade do vento e o intervalo de tempo especificado.
     * @pre windSpeed deve ser uma instância valida do tipo Vetor e time >0
     */
    public Vetor speed(Vetor windSpeed, double time) {
        return r.mult(1 / time).sub(windSpeed);
    }


    /**
     * Calcula o tempo total necessário para percorrer todos os vetores no objeto atual,
     * assumindo uma velocidade linear constante.
     *
     * @param linearSpeed A velocidade linear constante utilizada para atravessar a trajetória, expressa como um double.
     * @return O tempo total necessário para percorrer todos os vetores, expresso como um double.
     */
    public double time(double linearSpeed) {
        double time = 0;
        for (Vetor v : this.vetores) {
            time += time(v, linearSpeed);
        }
        return time;
    }

    /**
     * Calcula o tempo necessário para percorrer um determinado vetor com uma velocidade linear constante especificada.
     *
     * @param v           O vetor que representa o trajeto a ser percorrido. Deve ser uma instância válida de {@code Vetor}.
     * @param linearSpeed A velocidade linear constante com a qual o trajeto será percorrido, fornecida como um número do tipo double.
     * @return O tempo necessário para percorrer o vetor informado, expresso como um número do tipo double.
     * @pre {@code linearSpeed > 0} e {@code v} deve ser uma instância válida de {@code Vetor}.
     */
    private double time(Vetor v, double linearSpeed) {
        if (v == null) {
            throw new IllegalArgumentException("O vetor 'v' não pode ser nulo.");
        }
        if (linearSpeed <= 0) {
            throw new IllegalArgumentException("A velocidade linear 'linearSpeed' deve ser maior que zero.");
        }
        return v.modulo() / linearSpeed;
    }


    /**
     * Calcula o vetor velocidade resultante de um objeto, dada a sua velocidade inicial,
     * a velocidade do vento que atua contra ele e o intervalo de tempo.
     *
     * @param v         Um {@code Vetor} representando a velocidade inicial do objeto.
     * @param windSpeed Um {@code Vetor} representando a velocidade do vento que atua contra o objeto.
     * @param time      Um valor do tipo double que define o intervalo de tempo em que o cálculo é feito.
     * @return Um {@code Vetor} representando a velocidade resultante do objeto após considerar
     * os efeitos do vento e o intervalo de tempo especificado.
     * @pre {@code v} e {@code windSpeed} devem ser instâncias válidas de {@code Vetor}. {@code time} deve ser maior que 0.
     */
    private Vetor speed(Vetor v, Vetor windSpeed, double time) {
        return v.mult(1 / time).sub(windSpeed);

    }

    /**
     * Calcula a posição final de um objeto em movimento ao longo de uma trajetória composta por vetores,
     * dado uma velocidade linear constante e um tempo total de movimentação.
     *
     * @param linearSpeed A velocidade linear constante utilizada para percorrer a trajetória, expressa como um double.
     * @param totalTime   O tempo total disponível para o movimento ao longo da trajetória, expresso como um double.
     * @return Um {@code Ponto} representando a posição final do objeto após percorrer os vetores durante o tempo especificado.
     * @pre {@code linearSpeed >0} e {@code totalTime >0}
     **/
    public Ponto posicaoFinal(double linearSpeed, double totalTime) {
        Ponto f;
        double timeSeg;
        Vetor temp = null;
        Ponto p1 = null;
        for (int i = 0; i < vetores.size(); i++) {
            temp = vetores.get(i);
            timeSeg = time(temp, linearSpeed);
            p1 = pontos.get(i);
            if (totalTime < timeSeg || Math.abs(totalTime - timeSeg) < Ponto.tol) {
                break;
            }

            totalTime -= timeSeg;
        }
        double deslocamento = totalTime * linearSpeed;
        double m = temp.modulo();
        Vetor u = new Vetor(temp.x() / m, temp.y() / m);
        f = new Ponto(p1.x() + u.x() * deslocamento, p1.y() + u.y() * deslocamento);
        return f;
    }

    /**
     * Calcula os vetores de velocidade resultantes para cada segmento de uma trajetória,
     * considerando a velocidade do vento e uma velocidade linear constante.
     *
     * @param windspeed   Um {@code Vetor} representando a velocidade do vento que atua na trajetória.
     *                    Deve ser uma instância válida de {@code Vetor}.
     * @param linearSpeed A velocidade linear constante usada para calcular as velocidades, fornecida como um {@code double}.
     *                    Deve ser maior que 0 (zero).
     * @return Uma {@code ArrayList} de objetos {@code Vetor}, onde cada vetor representa a velocidade resultante de um segmento da trajetória.
     * @pre {@code windSpeed} deve ser uma instância válida de {@code Vetor} e {@code linearSpeed > 0}.
     */
    public ArrayList<Vetor> velocidades(Vetor windspeed, double linearSpeed) {
        double timeSeg;
        ArrayList<Vetor> velocidades = new ArrayList<>();
        for (int i = 0; i < vetores.size(); i++) {
            timeSeg = time(vetores.get(i), linearSpeed);
            velocidades.add(speed(vetores.get(i), windspeed, timeSeg));
        }
        return velocidades;
    }
}
