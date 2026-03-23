/**
 * A classe {@code Retangulo} representa um retângulo como uma forma geométrica com quatro vértices,
 * estendendo as funcionalidades da classe {@code Poligono}.
 * Esta implementação verifica se os vértices fornecidos formam um retângulo válido antes de instanciá-lo.
 *
 * @author Alexandre Guerreiro, a88489
 */
public class Retangulo extends Poligono {
    /**
     * Construtor para criar uma instância de um Retângulo, que é um polígono formado
     * por exatamente 4 vértices.
     *
     * @param vertices Um array de objetos do tipo {@code Ponto} que representam os
     *                 vértices do retângulo. O array deve conter exatamente 4 elementos.
     * @pre os pontos devem estar ordenados
     * @post uma instância valida de Retangulo
     * @inv pontos deve ter size =4
     */
    Retangulo(Ponto[] vertices) {

        super(vertices);
        if (vertices.length != 4) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }
        SegmentoReta aresta1 = new SegmentoReta(vertices[0], vertices[1]);
        SegmentoReta aresta2 = new SegmentoReta(vertices[2], vertices[3]);
        if (aresta1.comprimento() != aresta2.comprimento()) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }

        SegmentoReta aresta3 = new SegmentoReta(vertices[0], vertices[3]);
        SegmentoReta aresta4 = new SegmentoReta(vertices[1], vertices[2]);

        if (aresta3.comprimento() != aresta4.comprimento()) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }

        SegmentoReta diagonal1 = new SegmentoReta(vertices[0], vertices[2]);
        SegmentoReta diagonal2 = new SegmentoReta(vertices[1], vertices[3]);

        if (diagonal1.comprimento() != diagonal2.comprimento()) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }
    }
}
