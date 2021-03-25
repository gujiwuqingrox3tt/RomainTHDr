package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Simulation {
    private Graph graph;
    private Colony colony;

    public Simulation(int nbNodes) {
        graph = new Graph(nbNodes);
        colony = new Colony();

        for (int i = 0; i < nbNodes; i++) {
            colony.add(new Ant(i, new ArrayList<>(Arrays.asList(graph.getNodes()))));
        }
    }

    public Colony getColony() {
        return colony;
    }

    public Graph getGraph() {
        return graph;
    }
}
