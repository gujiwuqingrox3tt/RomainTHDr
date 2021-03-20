import model.Ant;
import model.Graph;
import model.Node;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        Ant ant = new Ant(1, new ArrayList<>(Arrays.asList(graph.getNodes())));

        int i = 0;

        boolean ok;
        do {
            System.out.println(ant.getCurrentNode().getX() + " " + ant.getCurrentNode().getY());
            ok = ant.nextNode();
            i++;
            if (i == 100) {
                System.err.println("STOP");
                break;
            }
        } while (ok);
    }

    private static final double C = 0.7;

    public void evaporation(Graph graph) {

    }
}
