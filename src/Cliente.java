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
        ArrayList<Ponto> pontos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] linha = br.readLine().split(" ");
        for (int i = 0; i < linha.length; i += 2) {
            pontos.add(new Ponto(Double.parseDouble(linha[i]), Double.parseDouble(linha[i + 1])));
        }
        Route rota = new Route(pontos);
        IO.println(rota.comprimento());
        linha = br.readLine().split(" ");
        Ponto start = new Ponto(Double.parseDouble(linha[0]), Double.parseDouble(linha[1]));
        Ponto end = new Ponto(Double.parseDouble(linha[2]), Double.parseDouble(linha[3]));
        SegmentoReta segv = new SegmentoReta(start, end);
        rota.intersect(segv);
    }
}
