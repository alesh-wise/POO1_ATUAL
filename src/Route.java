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
        if (points.isEmpty()) {
            IO.println("null");
        }
        intersect_toString(points);
    }

    private void intersect_toString(ArrayList<Ponto> points) {
        StringBuilder sb = new StringBuilder();
        for (Ponto temp : points) {
            sb.append(temp.toString());
        }
        System.out.println(sb.toString());
    }

    public double comprimento() {
        double comprimento = 0;
        for (SegmentoReta temp : segmentos) {
            comprimento += temp.comprimento();
        }
        return comprimento;
    }

}
