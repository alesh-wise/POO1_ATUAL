public class Quadrado extends Poligono {


    /**
     * Construtor para a classe Quadrado, que representa um polígono quadrado no plano cartesiano.
     * Inicializa o quadrado com base numa lista de vértices fornecida.
     *
     * @param vertices Uma lista de objetos do tipo {@code Ponto} que representam os vértices do quadrado.
     * @pre os pontos devem estar ordenados
     * @inv pontos deve ter size =4
     *
     */
    Quadrado(Ponto[] vertices) {
        if (vertices.length != 4) {
            IO.println("Quadrado.iv");
            System.exit(0);
        }
        SegmentoReta diagonal1 = new SegmentoReta(vertices[0], vertices[2]);
        SegmentoReta diagonal2 = new SegmentoReta(vertices[1], vertices[3]);
        if (diagonal1.comprimento() != diagonal2.comprimento()) {
            IO.println("Quadrado.iv");
            System.exit(0);
        }
        SegmentoReta aresta1 = new SegmentoReta(vertices[0], vertices[1]);
        SegmentoReta aresta2 = new SegmentoReta(vertices[1], vertices[2]);
        SegmentoReta aresta3 = new SegmentoReta(vertices[2], vertices[3]);
        SegmentoReta aresta4 = new SegmentoReta(vertices[3], vertices[0]);
        if ((aresta1.comprimento() != aresta2.comprimento()) || (aresta1.comprimento() != aresta3.comprimento()) || aresta1.comprimento() != aresta4.comprimento()) {
            IO.println("Quadrado.iv");
            System.exit(0);
        }
        super(vertices);
    }
}
