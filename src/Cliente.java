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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] linha = br.readLine().split(" ");
        ArrayList<Ponto> pontos = vertices(linha);
        Route r = new Route(pontos);
        IO.println(r.comprimento());
        linha = br.readLine().split(" ");
        Vetor windspeed = new Vetor(new Ponto(Double.parseDouble(linha[0]), Double.parseDouble(linha[1])));
        linha = br.readLine().split(" ");
        double linearspeed = Double.parseDouble(linha[0]);
        linha = br.readLine().split(" ");
        double time = Double.parseDouble(linha[0]);
        IO.println(r.timeRoute(linearspeed));
        Ponto pos = r.posicaoFinal(linearspeed, time);
        IO.println(pos);
        ArrayList<Vetor> velocidades = r.speed(windspeed, time, linearspeed);
        for (Vetor v : velocidades) {
            IO.print(v);
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
    public static ArrayList<Ponto> vertices(String[] linha) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < linha.length; i += 2) {
            pontos.add(new Ponto(Double.parseDouble(linha[i]), Double.parseDouble(linha[i + 1])));
        }
        return pontos;
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
