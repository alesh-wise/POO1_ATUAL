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
        double det = vertices[0].x() * (vertices[1].y() - vertices[2].y()) +
                vertices[1].x() * (vertices[2].y() - vertices[0].y()) +
                vertices[2].x() * (vertices[0].y() - vertices[1].y());
        if (Math.abs(det) < Ponto.tol) {
            IO.println("Triangulo.iv");
            System.exit(0);
        }
        super(vertices);
    }

}
