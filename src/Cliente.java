import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A classe Cliente é responsável por fornecer um ponto de entrada para a execução
 * do programa. Nesta classe, são criados objetos de tipos definidos no domínio do
 * problema, como Ponto, Vetor e SegmentoReta. Com base nas entradas do utilizador,
 * realiza-se o cálculo de interseção entre um vetor e um segmento de reta. A interação
 * é feita através da leitura de dados do terminal.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 12/02/2026
 */
public class Cliente {
    public static void main() throws IOException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String[] pontos = entrada.readLine().split(" ");
        ArrayList<SegmentoReta> segmentos = new ArrayList<>();

        for (int i = 2; i < pontos.length; i += 2) {
            Ponto start = new Ponto(Double.parseDouble(pontos[i - 2]), Double.parseDouble(pontos[i - 1]));
            Ponto end = new Ponto(Double.parseDouble(pontos[i]), Double.parseDouble(pontos[i + 1]));
            segmentos.add(new SegmentoReta(start, end));
        }
        Route rota = new Route(segmentos);
        IO.println(String.format("%.2f", rota.comprimento()));
        pontos = entrada.readLine().split(" ");
        Ponto start = new Ponto(Double.parseDouble(pontos[0]), Double.parseDouble(pontos[1]));
        Ponto end = new Ponto(Double.parseDouble(pontos[2]), Double.parseDouble(pontos[3]));
        SegmentoReta extremo = new SegmentoReta(start, end);
        rota.intersect(extremo);
    }
}
