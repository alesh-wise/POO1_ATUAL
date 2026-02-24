import java.util.Scanner;

/**
 * A classe Cliente é responsável por fornecer um ponto de entrada para a execução
 * do programa. Nesta classe, são criados objetos de tipos definidos no domínio do
 * problema, como Ponto, Vetor e SegmentoReta. Com base nas entradas do utilizador,
 * realiza-se o cálculo de interseção entre um vetor e um segmento de reta. A interação
 * é feita através da leitura de dados do terminal.
 *
 * @author Alexandre Guerreiro, 88489
 * @version 12/02/2026
 */
public class Cliente {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        //Get start and finish points
        Ponto start = new Ponto(sc.nextDouble(), sc.nextDouble());
        Ponto finish = new Ponto(sc.nextDouble(), sc.nextDouble());
        Vetor r = new Vetor(finish).sub(new Vetor(start));
        //Get wind speed and direction
        Vetor w = new Vetor(sc.nextDouble(), sc.nextDouble());
        //Get the required time to reach the finish point
        double t = sc.nextDouble();
        sc.close();
        //Compute the required speed to reach the finish point in time t
        Vetor s = r.sub(w).mult(1 / t);
        IO.println(s);
    }
}
