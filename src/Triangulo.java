public class Triangulo extends Poligono {
    /**
     * Construtor para a classe Triangulo, que representa um polígono triangulo no plano cartesiano.
     * Inicializa o triangulo com base numa lista de vértices fornecida.
     *
     * @param vertices Uma lista de objetos do tipo {@code Ponto} que representam os vértices do quadrado.
     * @inv pontos deve ter size =3
     *
     */
    Triangulo(Ponto[] vertices) {
        if (vertices.length != 3) {
            IO.println("Triangulo.iv");
            System.exit(0);
        }
        SegmentoReta aresta = new SegmentoReta(vertices[0], vertices[1]);
        if (aresta.noSegmento(vertices[2])) {
            IO.println("Triangulo.iv");
            System.exit(0);
        }
        super(vertices);
    }

}
