import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoPilotTest {

    @Test
    public void speed() {
        AutoPilot auto = new AutoPilot(new Ponto(2, 2), new Ponto(2, 4));
        Vetor windspeed = new Vetor(1, 1);
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
        AutoPilot auto = new AutoPilot(new Ponto(2, 2), new Ponto(2, 4));
        Vetor windspeed = new Vetor(1, 1);
        double v = 0.283;
        double time_expected = 5.00;
        assertEquals(time_expected, auto.time(windspeed, v), 0.1);
        Vetor v_expected = new Vetor(-0.20, 0.20);
        assertEquals(v_expected.x(), auto.speed(windspeed, time_expected).x(), 0.1);

        assertEquals(v_expected.y(), auto.speed(windspeed, time_expected).y(), 0.1);
    }
}