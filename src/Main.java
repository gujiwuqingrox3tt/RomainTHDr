import model.Ant;
import model.Colony;
import model.Graph;
import model.Node;
import model.Simulation;
import view.MainWindow;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final int n = 5;

        Simulation simulation = new Simulation(n);

        /*
        Colony colony = simulation.getColony();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < n; j++) {
                Ant ant = colony.get(j);
                System.out.println(ant.getId() + " " + ant.getCurrentNode().getX() + " " + ant.getCurrentNode().getY());
                ant.nextNode();
            }
            System.out.println();
        }
        */

        new MainWindow(simulation);
    }

    private static final double C = 0.7;

    public void evaporation(Graph graph) {

    }
}
