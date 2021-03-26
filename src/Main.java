import model.Colony;
import model.Constantes;
import model.Graph;
import model.Node;
import view.MainWindow;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(Constantes.NB_NODES);

        Colony colony = new Colony(graph);

        colony.cycleColony();
        while (!colony.hasFinished()) {
            colony.cycleColony();
        }

        List<Node> bestNodes = colony.getBestNodes();

        for (Node node : bestNodes) {
            System.out.print("(" + node.getX() + ", " + node.getY() + ") -> ");
        }
        System.out.print("(" + bestNodes.get(0).getX() + ", " + bestNodes.get(0).getY() + ")");
        System.out.println();

        new MainWindow(graph, colony);
    }
}
