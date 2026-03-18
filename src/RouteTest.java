import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RouteTest {

    @Test
    void comprimento() {
        ArrayList<Ponto> pontos = new ArrayList<Ponto>();
        pontos.add(new Ponto(0, 1));
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(4, 4));
        pontos.add(new Ponto(4, 3));
        Route rota = new Route(pontos);
        assertEquals(6.24, rota.comprimento(), 0.01);
    }

    @Test
    void intersect() {
        ArrayList<Ponto> pontos = new ArrayList<Ponto>();
        pontos.add(new Ponto(0, 1));
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(4, 4));
        pontos.add(new Ponto(4, 3));
        Route rota = new Route(pontos);
        SegmentoReta sg = new SegmentoReta(new Ponto(2, 1), new Ponto(2, 4));
        ArrayList<Ponto> expected = new ArrayList<Ponto>();
        Ponto intersect = new Ponto(2, 2);
        expected.add(intersect);
        assertEquals(expected, rota.intersect(sg));
    }
}