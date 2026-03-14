public class Retangulo extends Poligono {
    /**
     * Construtor para criar uma instância de um Retângulo, que é um polígono formado
     * por exatamente 4 vértices.
     *
     * @param vertices Um array de objetos do tipo {@code Ponto} que representam os
     *                 vértices do retângulo. O array deve conter exatamente 4 elementos.
     * @throws IllegalArgumentException se o número de vértices não for igual a 4.
     */
    Retangulo(Ponto[] vertices) {
        if (vertices.length != 4) {
            IO.println("Retangulo.iv");
            System.exit(0);
        }
        SegmentoReta aresta1 = new SegmentoReta(vertices[0], vertices[1]);
        SegmentoReta aresta2 = new SegmentoReta(vertices[2], vertices[3]);
        if (aresta1.comprimento() != aresta2.comprimento()) {
            IO.println("Retangulo.iv");
            System.exit(0);
        }

        SegmentoReta aresta3 = new SegmentoReta(vertices[0], vertices[3]);
        SegmentoReta aresta4 = new SegmentoReta(vertices[1], vertices[2]);

        if (aresta3.comprimento() != aresta4.comprimento()) {
            IO.println("Retangulo.iv");
            System.exit(0);
        }

        super(vertices);
    }
}
