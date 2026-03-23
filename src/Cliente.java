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
        switch (c) {
            case "P" -> {
                Poligono sla = new Poligono(vertices(Arrays.copyOfRange(linha, 1, linha.length)));
                pontos = rota.intersect(sla);
                printPoints(pontos);

            }
            case "S" -> {
                Quadrado sla = new Quadrado(vertices(Arrays.copyOfRange(linha, 1, linha.length)));
                pontos = rota.intersect(sla);
                printPoints(pontos);

            }
            case "R" -> {
                Retangulo sla = new Retangulo(vertices(Arrays.copyOfRange(linha, 1, linha.length)));
                pontos = rota.intersect(sla);
                printPoints(pontos);

            }
            case "T" -> {
                Triangulo sla = new Triangulo(vertices(Arrays.copyOfRange(linha, 1, linha.length)));
                pontos = rota.intersect(sla);
                printPoints(pontos);

            }
            case "C" -> {
                Circulo sla = new Circulo(new Ponto(Double.parseDouble(linha[1]), Double.parseDouble(linha[2])), Double.parseDouble(linha[3]));
                pontos = rota.intersect(sla);
                printPoints(pontos);

            }
        }

    }


    /**
     * Converte um array de strings representando coordenadas em um array de objetos {@code Ponto}.
     * Cada par consecutivo de strings é convertido em um ponto bidimensional no formato (x, y).
     *
     * @param linha Um array de strings onde cada par de elementos consecutivos representa
     *              as coordenadas x e y de um ponto.
     *              Deve conter um número par de elementos.
     * @return Um array de objetos {@code Ponto}, onde cada elemento representa um ponto
     * bidimensional criado a partir dos pares de coordenadas fornecidos como entrada.
     * @pre uma instância valida do tipo String[]
     * @post retorna uma nova instância valida do tipo Ponto[]
     */
    public static Ponto[] vertices(String[] linha) {
        Ponto[] vertices = new Ponto[linha.length / 2];
        for (int i = 0; i < linha.length; i += 2) {
            vertices[i / 2] = new Ponto(Double.parseDouble(linha[i]), Double.parseDouble(linha[i + 1]));
        }
        return vertices;
    }

    /**
     * Imprime a lista de pontos no formato especificado.
     * Cada ponto é representado como uma string no formato "(x,y)", e os pontos
     * são separados por um espaço.
     * Caso a lista esteja vazia, será impresso "null".
     *
     * @param pontos A lista de objetos {@code Ponto} a ser impressa.
     *               Cada ponto é representado através de seu formato em string "(x,y)".
     *               Se a lista estiver vazia, uma mensagem será exibida indicando
     *               que não há pontos disponíveis.
     * @pre uma instância valida de ArrayList<Ponto>
     */
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
