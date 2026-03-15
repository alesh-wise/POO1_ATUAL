import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A classe Cliente é responsável por receber a entrada do usuário via console,
 * processar os dados para criar uma rota (Route) composta por pontos (Ponto),
 * calcular o comprimento total da rota e determinar interseções entre a rota e
 * um segmento de reta (SegmentoReta).
 * <p>
 * A classe processa input do usuário para construir objetos e utiliza os métodos
 * de outras classes associadas como Ponto, SegmentoReta e Route para realizar
 * cálculos e exibir os resultados no console.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 09/03/2026
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
        Circulo c = new Circulo(new Ponto(0, 0), 5);
        ArrayList<Ponto> pontosIntersect = rota.intersect(c);
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
