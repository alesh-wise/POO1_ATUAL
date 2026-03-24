import java.util.ArrayList;

/**
 * A classe AutoPilot fornece métodos para calcular a velocidade de um objeto
 * e o tempo necessário para percorrer uma dada trajetória considerando influências
 * externas como a velocidade do vento. A trajetória é definida por um vetor que
 * conecta dois pontos.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 10/03/2026
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
     */
    public AutoPilot(Ponto a, Ponto b) {
        pontos.add(a);
        pontos.add(b);
        vetores.add(new Vetor(b.x() - a.x(), b.y() - a.y()));
    }


    public AutoPilot(Route rota) {
        ArrayList<Ponto> pontosRota = rota.pontos();
        pontos.add(pontosRota.get(0));
        for (int i = 1; i < pontosRota.size(); i++) {
            vetores.add(new Vetor(pontosRota.get(i).x() - pontosRota.get(i - 1).x(), pontosRota.get(i).y() - pontosRota.get(i - 1).y()));
            pontos.add(pontosRota.get(i));
        }
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
     * Calcula o tempo necessário para um objeto percorrer uma trajetória
     * considerando a influência da velocidade do vento e sua velocidade linear.
     *
     * @param linearSpeed Um {@code double} representando a velocidade linear do objeto.
     * @return Um {@code double} representando o tempo necessário para completar a trajetória.
     * @pre windSpeed deve ser uma instância valida do tipo Vetor e linear >0
     */
    public double time(double linearSpeed) {
        return r.modulo() / linearSpeed;
    }

    private double time(Vetor v, double linearSpeed) {
        return v.modulo() / linearSpeed;
    }

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


    public ArrayList<Vetor> velocidades(Vetor windspeed, double totalTime, double linearSpeed) {
        double timeSeg;
        ArrayList<Vetor> velocidades = new ArrayList<>();
        for (int i = 0; i < vetores.size(); i++) {
            timeSeg = time(vetores.get(i), linearSpeed);
            velocidades.add(speed(windspeed, timeSeg));
            if (totalTime < timeSeg || Math.abs(totalTime - timeSeg) < Ponto.tol) {
                break;
            }
            totalTime -= timeSeg;

        }
        return velocidades;
    }
}
