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
     * @post uma instância valida de Quadrado
     * @inv pontos deve ter size =4
     *
     */
    Quadrado(Ponto[] vertices) {
        if (vertices.length != 4) {
            IO.println("Quadrado:iv");
            System.exit(0);
        }
        SegmentoReta diagonal1 = new SegmentoReta(vertices[0], vertices[2]);
        SegmentoReta diagonal2 = new SegmentoReta(vertices[1], vertices[3]);
        if (diagonal1.comprimento() != diagonal2.comprimento()) {
            IO.println("Quadrado:iv");
            System.exit(0);
        }
        SegmentoReta aresta1 = new SegmentoReta(vertices[0], vertices[1]);
        SegmentoReta aresta2 = new SegmentoReta(vertices[1], vertices[2]);
        SegmentoReta aresta3 = new SegmentoReta(vertices[2], vertices[3]);
        SegmentoReta aresta4 = new SegmentoReta(vertices[3], vertices[0]);
        if ((aresta1.comprimento() != aresta2.comprimento()) || (aresta1.comprimento() != aresta3.comprimento()) || aresta1.comprimento() != aresta4.comprimento()) {
            IO.println("Quadrado:iv");
            System.exit(0);
        }
        super(vertices);
    }
}
