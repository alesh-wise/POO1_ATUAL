public class Circulo extends FiguraG {
    private Ponto centro;
    private double raio;

    Circulo(Ponto centro, double raio) {
        this.centro = centro;
        this.raio = raio;
    }

    Ponto getCentro() {
        return centro;
    }

    double getRaio() {
        return raio;
    }
}
