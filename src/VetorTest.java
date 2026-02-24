import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class VetorTest {

    @Test
    public void testmodulo() {
        Vetor v = new Vetor(3, 4);
        assertEquals(5, v.modulo());
    }

    @Test
    public void testp_interno() {
        Vetor v = new Vetor(3, 4);
        Vetor w = new Vetor(2, 2);
        assertEquals(14, v.p_interno(w));
    }

    @Test
    public void testcossine() {
        Vetor v = new Vetor(3, 4);
        Vetor w = new Vetor(-4, 3);
        assertEquals(0, v.cossine(w));
    }

    @Test
    public void testintersect() {
        SegmentoReta sg = new SegmentoReta(new Ponto(0, 1), new Ponto(4, 1));
        Vetor v = new Vetor(2, 2);
        Ponto expected = new Ponto(1.0, 1.0);
        SegmentoReta segmentoReta = new SegmentoReta(new Ponto(4, 4), new Ponto(6, 6));
        Vetor vetor = new Vetor(2, 2);
        assertEquals(expected, v.intersect(sg));
        assertNull(vetor.intersect(segmentoReta));
    }

    @Test
    public void testmult() {
        Vetor v = new Vetor(2, 2);
        int escalar = 2;
        Vetor expected = new Vetor(4, 4);

        assertEquals(expected.x(), v.mult(escalar).x());
        assertEquals(expected.y(), v.mult(escalar).y());
    }

    @Test
    public void testadd() {
        Vetor v = new Vetor(2, 2);
        Vetor w = new Vetor(1, 1);
        Vetor expected = new Vetor(3, 3);
        assertEquals(expected.x(), v.add(w).x());
        assertEquals(expected.y(), v.add(w).y());

    }

    @Test
    public void testsub() {
        Vetor v = new Vetor(2, 2);
        Vetor w = new Vetor(1, 1);
        Vetor expected = new Vetor(1, 1);
        assertEquals(expected.x(), v.sub(w).x());
        assertEquals(expected.y(), v.sub(w).y());
    }

    @Test
    public void testtoString() {
        Vetor v = new Vetor(2, 2);
        assertEquals("[2.00,2.00]", v.toString());
    }
}