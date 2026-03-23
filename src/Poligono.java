import java.util.Arrays;

/**
 * A classe Poligono representa uma figura geométrica genérica composta por múltiplos lados.
 * É uma especialização de FiguraG e é definida por um conjunto de vértices que devem estar
 * ordenados de forma a formar um polígono válido.
 *
 * @author Alexandre Guerreiro, a88489
 */
public class Poligono extends FiguraG {
    private Ponto[] vertices;


    /**
     * Constrói um polígono representado por uma lista de vértices.
     *
     * @param vertices Uma lista de objetos do tipo {@code Ponto} que representam os vértices do polígono.
     * @pre os pontos do ArrayList vertices devem estar ordenados para formar o poligono
     * @post uma vertices passa a ser igual ao array vertices
     */
    Poligono(Ponto[] vertices) {
        if (vertices.length < 3) {
            IO.println("Poligono:iv");
            System.exit(0);
        }
        this.vertices = Arrays.copyOf(vertices, vertices.length);
    }

    /**
     * Retorna o array de vértices que compõem o polígono.
     *
     * @return Um array de objetos do tipo {@code Ponto} representando os vértices do polígono.
     */
    Ponto[] vertices() {
        return this.vertices;
    }
}
