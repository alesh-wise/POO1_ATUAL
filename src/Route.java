import java.util.ArrayList;

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
                IO.print(intersect.toString());
                exit = true;
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
