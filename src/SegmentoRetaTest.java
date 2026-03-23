import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SegmentoRetaTest {

    @Test
    public void comprimento() {
        SegmentoReta sg = new SegmentoReta(new Ponto(0, 0), new Ponto(3, 4));
        assertEquals(5, sg.comprimento());
    }

    @Test
    public void intersectVetor() {
        SegmentoReta sg = new SegmentoReta(new Ponto(0, 1), new Ponto(4, 1));
        Vetor v = new Vetor(2, 2);
        Ponto expected = new Ponto(1.0, 1.0);
        SegmentoReta segmentoReta = new SegmentoReta(new Ponto(4, 4), new Ponto(6, 6));
        Vetor vetor = new Vetor(2, 2);
        assertEquals(expected, v.intersect(sg));
        assertNull(vetor.intersect(segmentoReta));
    }

    @Test
    public void intersectSegmento() {
        SegmentoReta sg1 = new SegmentoReta(new Ponto(0, 2), new Ponto(4, 2));
        SegmentoReta sg2 = new SegmentoReta(new Ponto(2, 0), new Ponto(2, 4));
        Ponto expected = new Ponto(2, 2);
        assertEquals(expected, sg1.intersect(sg2));
    }

    @Test
    public void testToString() {
        SegmentoReta sg = new SegmentoReta(new Ponto(0, 0), new Ponto(4, 4));
        assertEquals("sr((0,00,0,00); (4,00,4,00))", sg.toString());
    }
}