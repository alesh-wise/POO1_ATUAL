public class Poligono {
    private Ponto[] vertices;


    /**
     * Constrói um polígono representado por uma lista de vértices.
     *
     * @param vertices Uma lista de objetos do tipo {@code Ponto} que representam os vértices do polígono.
     * @pre os pontos do ArrayList vertices devem estar ordenados para formar o poligono
     * @post uma instância valida de Poligono
     */
    Poligono(Ponto[] vertices) {
        if (vertices.length < 3) {
            IO.println("Poligono.iv");
            System.exit(0);
        }
        this.vertices = vertices;
    }

    public double perimetro() {
        double perimetro = 0;
        for (int i = 1; i < vertices.length; i++) {
            perimetro += vertices[i - 1].distance_to(vertices[i]);
        }
        return perimetro;
    }

    Ponto[] vertices() {
        return this.vertices;
    }
}
