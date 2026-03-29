import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoPilotTest {

    @Test
    void speed() {
        AutoPilot auto = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        Vetor windspeed = new Vetor(0.2, 0.2);
        double time = 5;
        Vetor expected = new Vetor(-0.20, 0.20);
        assertEquals(expected.x(), auto.speed(windspeed, time).x(), Ponto.tol);
        assertEquals(expected.y(), auto.speed(windspeed, time).y(), Ponto.tol);

        AutoPilot auto2 = new AutoPilot(new Ponto(2, 4), new Ponto(2, 2));
        Vetor expected2 = new Vetor(-0.20, -0.60);
        assertEquals(expected2.x(), auto2.speed(windspeed, time).x(), Ponto.tol);
        assertEquals(expected2.y(), auto2.speed(windspeed, time).y(), Ponto.tol);
    }

    public @Test
    void time() {
        AutoPilot auto = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        Vetor windspeed = new Vetor(0.2, 0.2);
        double v = 0.4;
        double time_expected = 5.00;
        assertEquals(time_expected, auto.time(v), 0.1);
        Vetor v_expected = new Vetor(-0.20, 0.20);
        assertEquals(v_expected.x(), auto.speed(windspeed, time_expected).x(), 0.1);

        assertEquals(v_expected.y(), auto.speed(windspeed, time_expected).y(), 0.1);
    }

    @Test
    void posicaoFinal() {
        Double[] coordenadas = {5.0, 1.0, 5.0, 5.0, 7.0, 5.0};
        Route rota = new Route(coordenadas);
        Ponto exp = new Ponto(5.50, 5.00);
        AutoPilot at = new AutoPilot(rota);
        double totaltime = 2.25;
        double linearspeed = 2.0;
        assertEquals(exp, at.posicaoFinal(totaltime, linearspeed));
        coordenadas = new Double[]{1.0, 1.0, 2.0, 1.0, 2.0, 2.0, 3.0, 2.0, 3.0, 3.0, 4.0, 3.0, 4.0, 4.0, 5.0, 4.0,};
        totaltime = 1.75;
        exp = new Ponto(3.00, 2.50);
        rota = new Route(coordenadas);
        at = new AutoPilot(rota);
        assertEquals(exp, at.posicaoFinal(totaltime, linearspeed));
    }

    @Test
    void velocidades() {
        Double[] coordenadas = {5.0, 1.0, 5.0, 5.0, 7.0, 5.0};
        Route rota = new Route(coordenadas);
        Vetor windspeed = new Vetor(1, 1);

        double linearspeed = 2.0;
        List<Vetor> velocidades = List.of(
                new Vetor(-1, 1),
                new Vetor(1, -1));

        AutoPilot at = new AutoPilot(rota);
        assertEquals(velocidades, at.velocidades(windspeed, linearspeed));
        coordenadas = new Double[]{1.0, 1.0, 2.0, 1.0, 2.0, 2.0, 3.0, 2.0, 3.0, 3.0, 4.0, 3.0, 4.0, 4.0, 5.0, 4.0,};

        windspeed = new Vetor(-1, 0);
        rota = new Route(coordenadas);
        at = new AutoPilot(rota);

        velocidades = List.of(
                new Vetor(3, 0),
                new Vetor(1, 2),
                new Vetor(3, 0),
                new Vetor(1, 2),
                new Vetor(3, 0),
                new Vetor(1, 2),
                new Vetor(3, 0)
        );


        assertEquals(velocidades, at.velocidades(windspeed, linearspeed));
    }

}