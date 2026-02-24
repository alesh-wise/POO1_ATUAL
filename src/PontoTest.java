import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PontoTest {

    @Test
    public void testToString() {
        Ponto a = new Ponto(2, 2);
        assertEquals("(2,00,2,00)", a.toString());
    }

    @Test
    public void testdistance_to() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(3, 4);
        assertEquals(5, a.distance_to(b));
    }

    @Test
    public void testEquals() {
        Ponto a = new Ponto(2, 2);
        Ponto b = new Ponto(3, 4);
        Ponto c = new Ponto(a.x() + Ponto.tol / 2, a.y() - Ponto.tol / 2);
        Vetor d = new Vetor(a);
        assertEquals(false, a.equals(b));
        assertEquals(true, a.equals(c));
        assertEquals(false, a.equals(d));
    }

    @Test
    public void testsubtracao() {
        Ponto a = new Ponto(2, 2);
        Ponto b = new Ponto(3, 4);
        Ponto c = new Ponto(a.x() - b.x(), a.y() - b.y());
        assertEquals(c, a.subtracao(b));
    }

    @Test
    public void testprodutoVetorial() {
        Ponto a = new Ponto(2, 2);
        Ponto b = new Ponto(3, 4);
        assertEquals(2, a.produtoVetorial(b));
    }
}