/**
 * A classe {@code Retangulo} representa um retângulo como uma forma geométrica com quatro vértices,
 * estendendo as funcionalidades da classe {@code Poligono}.
 * Esta implementação verifica se os vértices fornecidos formam um retângulo válido antes de instanciá-lo.
 *
 * @author Alexandre Guerreiro, a88489
 * @version 05/04/2026
 * @inv vertices deve ter length == 4
 */
public class Retangulo extends Poligono {
    /**
     * Construtor para criar uma instância de um Retângulo, que é um polígono formado
     * por exatamente 4 vértices.
     *
     * @param vertices Um array de objetos do tipo {@code Ponto} que representam os
     *                 vértices do retângulo. O array deve conter exatamente 4 elementos.
     * @pre {@code vertices} deve ser uma instância valida de {@code Ponto[]} e devem estar ordenados.
     * @post this.vertices passa a ser igual ao array {@code vertices}
     */
    Retangulo(Ponto[] vertices) {

        super(vertices);
        if (vertices.length != 4) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }
        if (vertices[0].distance_to(vertices[1]) != vertices[2].distance_to(vertices[3])) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }

        if (vertices[0].distance_to(vertices[3]) != vertices[1].distance_to(vertices[2])) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }

        if (vertices[0].distance_to(vertices[2]) != vertices[1].distance_to(vertices[3])) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }
    }
}
