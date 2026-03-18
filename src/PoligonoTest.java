import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PoligonoTest {

    @Test
    void vertices() {
        Ponto[] pontos = new Ponto[3];
        pontos[0] = new Ponto(0, 0);
        pontos[1] = new Ponto(1, 1);
        pontos[2] = new Ponto(2, 0);
        Poligono poligono = new Poligono(pontos);
        assertEquals(poligono.vertices(), pontos);
    }
}