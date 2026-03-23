/**
 * A classe Quadrado representa um tipo específico de polígono com quatro lados iguais e ângulos retos.
 * Ela herda de Poligono e contém validações para garantir que os vértices fornecidos formam um quadrado válido.
 *
 * @author Alexandre Guerreiro, a88489
 */
public class Quadrado extends Poligono {


    /**
     * Construtor para a classe Quadrado, que representa um polígono quadrado no plano cartesiano.
     * Inicializa o quadrado com base numa lista de vértices fornecida.
     *
     * @param vertices Uma lista de objetos do tipo {@code Ponto} que representam os vértices do quadrado.
     * @pre os pontos devem estar ordenados
     * @post vertices passa a ser igual ao array vertices
     * @inv pontos deve ter size =4
     *
     */
    Quadrado(Ponto[] vertices) {

        super(vertices);
        if (vertices.length != 4) {
            IO.println("Quadrado:iv");
            System.exit(0);
        }

        if (vertices[0].distance_to(vertices[2]) != vertices[1].distance_to(vertices[3])) {
            IO.println("Quadrado:iv");
            System.exit(0);
        }
        if ((vertices[0].distance_to(vertices[1]) != vertices[0].distance_to(vertices[3])) || (vertices[1].distance_to(vertices[2]) != vertices[2].distance_to(vertices[3]))) {
            IO.println("Quadrado:iv");
            System.exit(0);
        }
    }
}
