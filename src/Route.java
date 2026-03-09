import java.util.ArrayList;

/**
 * Uma classe que representa uma Rota, que é uma coleção de pontos (Ponto)
 * conectados sequencialmente. A classe fornece funcionalidades para calcular o
 * comprimento da rota e determinar interseções com um segmento de reta dado.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 12/02/2026
 */
public class Route {

    private ArrayList<Ponto> pontos;

    Route(ArrayList<Ponto> pontos) {
        this.pontos = pontos;
    }

    public void intersect(SegmentoReta sg) {

        boolean exit = false;
        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta temp = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
            Ponto intersect = temp.intersect(sg);
            if (intersect != null) {
                IO.print(intersect);
                exit = true;
                if (i == pontos.size() - 1) {
                    IO.print("\n");
                } else {
                    IO.print(" ");
                }
            }
        }
        if (!exit) {
            IO.println("null");
        }

    }


    public double comprimento() {
        double comprimento = 0;
        for (int i = 1; i < pontos.size(); i++) {
            comprimento += pontos.get(i - 1).distance_to(pontos.get(i));
        }
        return comprimento;
    }

}
