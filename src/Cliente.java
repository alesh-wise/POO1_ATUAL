import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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
        linha = br.readLine().split(" ");

        FiguraG fig = null;
        String c = linha[0];

        fig = switch (c) {
            case "P" -> new Poligono(vertices(Arrays.copyOfRange(linha, 1, linha.length)));
            case "S" -> new Quadrado(vertices(Arrays.copyOfRange(linha, 1, linha.length)));
            case "R" -> new Retangulo(vertices(Arrays.copyOfRange(linha, 1, linha.length)));
            case "T" -> new Triangulo(vertices(Arrays.copyOfRange(linha, 1, linha.length)));
            case "C" ->
                    new Circulo(new Ponto(Double.parseDouble(linha[1]), Double.parseDouble(linha[2])), Double.parseDouble(linha[3]));
            default -> fig;
        };
        pontos = rota.intersect(fig);
        printPoints(pontos);

    }

    public static Ponto[] vertices(String[] linha) {
        Ponto[] vertices = new Ponto[linha.length / 2];
        for (int i = 0; i < linha.length; i += 2) {
            vertices[i / 2] = new Ponto(Double.parseDouble(linha[i]), Double.parseDouble(linha[i + 1]));
        }
        return vertices;
    }

    public static void printPoints(ArrayList<Ponto> pontos) {
        if (pontos.isEmpty()) {
            IO.println("null");
        } else {
            for (int i = 0; i < pontos.size(); i++) {
                IO.print(pontos.get(i).toString());
                if (i == pontos.size() - 1) {
                    IO.print("\n");
                } else {
                    IO.print(" ");
                }
            }

        }

    }

}
