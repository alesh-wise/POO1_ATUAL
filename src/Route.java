import java.util.ArrayList;

public class Route {
    private ArrayList<SegmentoReta> segmentos;


    Route(ArrayList<SegmentoReta> segmentos) {
        this.segmentos = segmentos;
    }

    public void intersect(SegmentoReta sg) {
        ArrayList<Ponto> points = new ArrayList<Ponto>();

        for (SegmentoReta temp : segmentos) {
            Ponto intersect = temp.intersect(sg.getV1());
            if (intersect != null) {
                points.add(intersect);
            }
        }
    }

    public double comprimento() {
        double comprimento = 0;
        for (SegmentoReta temp : segmentos) {
            comprimento += temp.comprimento();
        }
        return comprimento;
    }

}
