import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RouteTest {

    @Test
    void comprimento() {
        Double[] coordenadas = {0.0, 1.0, 1.0, 1.0, 4.0, 4.0, 4.0, 3.0};
        Route rota = new Route(coordenadas);
        assertEquals(6.24, rota.comprimento(), 0.01);
    }

    @Test
    void intersectSegmentoReta() {

        Double[] coordenadas = {0.0, 1.0, 1.0, 1.0, 4.0, 4.0, 4.0, 3.0};
        Route rota = new Route(coordenadas);
        SegmentoReta sg = new SegmentoReta(new Ponto(2, 1), new Ponto(2, 4));
        ArrayList<Ponto> expected = new ArrayList<Ponto>();
        Ponto intersect = new Ponto(2, 2);
        expected.add(intersect);
        assertEquals(expected, rota.intersect(sg));
    }

    @Test
    void intersectPoligono() {
        Double[] coordenadas = {0.0, 1.0, 1.0, 1.0, 4.0, 4.0, 4.0, 3.0};
        Route rota = new Route(coordenadas);

        Ponto[] vertices = new Ponto[4];
        vertices[1] = new Ponto(2, 4);
        vertices[2] = new Ponto(3, 4);
        vertices[3] = new Ponto(3, 1);
        vertices[0] = new Ponto(2, 1);

        Poligono p = new Poligono(vertices);

        ArrayList<Ponto> expected = new ArrayList<Ponto>();
        Ponto inter1 = new Ponto(2, 2);
        Ponto inter2 = new Ponto(3, 3);
        expected.add(inter1);
        expected.add(inter2);

        assertEquals(expected, rota.intersect(p));
    }

    @Test
    void intersectQuadrado() {
        Double[] coordenadas = {0.0, 1.0, 1.0, 1.0, 4.0, 4.0, 4.0, 3.0};
        Route rota = new Route(coordenadas);

        Ponto[] vertices = new Ponto[4];

        vertices[0] = new Ponto(1, 2);
        vertices[1] = new Ponto(1, 4);
        vertices[2] = new Ponto(3, 4);
        vertices[3] = new Ponto(3, 2);
        Quadrado q = new Quadrado(vertices);
        ArrayList<Ponto> expected = new ArrayList<Ponto>();
        Ponto inter1 = new Ponto(2, 2);
        Ponto inter2 = new Ponto(3, 3);

        expected.add(inter2);
        expected.add(inter1);
        assertEquals(expected, rota.intersect(q));

        coordenadas = new Double[]{100.0, 100.0, 200.0, 200.0};

        Route fora = new Route(coordenadas);
        expected = new ArrayList<>();
        assertEquals(expected, fora.intersect(q));
    }

    @Test
    void intersectRetangulo() {
        Double[] coordenadas = {0.0, 1.0, 1.0, 1.0, 4.0, 4.0, 4.0, 3.0};
        Route rota = new Route(coordenadas);

        Ponto[] vertices = new Ponto[4];
        vertices[1] = new Ponto(1.5, 4.0);
        vertices[2] = new Ponto(3.5, 4.0);
        vertices[3] = new Ponto(3.5, 0.0);
        vertices[0] = new Ponto(1.5, 0.0);
        Retangulo r = new Retangulo(vertices);
        Ponto inter1 = new Ponto(1.5, 1.5);
        Ponto inter2 = new Ponto(3.5, 3.5);
        ArrayList<Ponto> expected = new ArrayList<Ponto>();

        expected.add(inter1);
        expected.add(inter2);
        assertEquals(expected, rota.intersect(r));


        coordenadas = new Double[]{100.0, 100.0, 200.0, 200.0};

        Route fora = new Route(coordenadas);
        expected = new ArrayList<>();
        assertEquals(expected, fora.intersect(r));

    }

    @Test
    void intersectTriangulo() {
        Double[] coordenadas = {0.0, 1.0, 1.0, 1.0, 4.0, 4.0, 4.0, 3.0};
        Route rota = new Route(coordenadas);


        Ponto[] vertices = new Ponto[3];
        vertices[0] = new Ponto(2, 0);
        vertices[1] = new Ponto(2, 4);
        vertices[2] = new Ponto(4, 2);

        Triangulo t = new Triangulo(vertices);
        ArrayList<Ponto> expected = new ArrayList<Ponto>();
        Ponto inter1 = new Ponto(2, 2);
        Ponto inter2 = new Ponto(3, 3);
        expected.add(inter1);
        expected.add(inter2);

        assertEquals(expected, rota.intersect(t));


        coordenadas = new Double[]{100.0, 100.0, 200.0, 200.0};

        Route fora = new Route(coordenadas);
        expected = new ArrayList<>();
        assertEquals(expected, fora.intersect(t));


    }

    @Test
    void intersectCirculo() {
        Double[] coordenadas = {0.0, 1.0, 1.0, 1.0, 4.0, 4.0, 4.0, 3.0};
        Route rota = new Route(coordenadas);


        Circulo c = new Circulo(new Ponto(2, 3), 1);
        ArrayList<Ponto> expected = new ArrayList<Ponto>();
        Ponto inter1 = new Ponto(2, 2);
        Ponto inter2 = new Ponto(3, 3);
        expected.add(inter1);
        expected.add(inter2);

        assertEquals(expected, rota.intersect(c));


        coordenadas = new Double[]{100.0, 100.0, 200.0, 200.0};

        Route fora = new Route(coordenadas);
        expected = new ArrayList<>();
        assertEquals(expected, fora.intersect(c));

    }

    @Test
    void timeRoute() {
        Double[] coordenadas = {5.0, 1.0, 5.0, 5.0, 7.0, 5.0};
        Route rota = new Route(coordenadas);
        double exp = 3.0;
        double linearspeed = 2.0;
        assertEquals(exp, rota.timeRoute(linearspeed));
        coordenadas = new Double[]{1.0, 1.0, 2.0, 1.0, 2.0, 2.0, 3.0, 2.0, 3.0, 3.0, 4.0, 3.0, 4.0, 4.0, 5.0, 4.0,};
        rota = new Route(coordenadas);
        exp = 3.5;
        assertEquals(exp, rota.timeRoute(linearspeed));
    }

    @Test
    void posicaoFinal() {
        Double[] coordenadas = {5.0, 1.0, 5.0, 5.0, 7.0, 5.0};
        Route rota = new Route(coordenadas);
        Ponto exp = new Ponto(5.50, 5.00);
        double totaltime = 2.25;
        double linearspeed = 2.0;
        assertEquals(exp, rota.posicaoFinal(totaltime, linearspeed));
        coordenadas = new Double[]{1.0, 1.0, 2.0, 1.0, 2.0, 2.0, 3.0, 2.0, 3.0, 3.0, 4.0, 3.0, 4.0, 4.0, 5.0, 4.0,};
        totaltime = 1.75;
        exp = new Ponto(3.00, 2.50);
        rota = new Route(coordenadas);
        assertEquals(exp, rota.posicaoFinal(totaltime, linearspeed));
    }

    @Test
    void speed() {
        Double[] coordenadas = {5.0, 1.0, 5.0, 5.0, 7.0, 5.0};
        Route rota = new Route(coordenadas);
        Vetor windspeed = new Vetor(1, 1);

        double linearspeed = 2.0;
        List<Vetor> velocidades = List.of(
                new Vetor(-1, 1),
                new Vetor(1, -1));

        assertEquals(velocidades, rota.speed(windspeed, linearspeed));
        coordenadas = new Double[]{1.0, 1.0, 2.0, 1.0, 2.0, 2.0, 3.0, 2.0, 3.0, 3.0, 4.0, 3.0, 4.0, 4.0, 5.0, 4.0,};

        windspeed = new Vetor(-1, 0);
        rota = new Route(coordenadas);

        velocidades = List.of(
                new Vetor(3, 0),
                new Vetor(1, 2),
                new Vetor(3, 0),
                new Vetor(1, 2),
                new Vetor(3, 0),
                new Vetor(1, 2),
                new Vetor(3, 0)
        );


        assertEquals(velocidades, rota.speed(windspeed, linearspeed));
    }
}