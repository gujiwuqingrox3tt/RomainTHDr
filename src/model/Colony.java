package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colony extends ArrayList<Ant> {
    private Graph graph;
    private double minLength;
    private ArrayList<Node> minPath;
    private int nb_cycle_unchanged;

    public Colony(Graph graph) {
        this.graph = graph;
        for (int i = 0; i < graph.getNodes().length; i++) {
            add(null);
        }
        nb_cycle_unchanged = 0;
        this.minLength = Double.POSITIVE_INFINITY;
    }

    public void cycleColony() {
        // On reset les fourmis
        for (int i = 0; i < size(); i++) {
            set(i, new Ant(i, new ArrayList<>(Arrays.asList(graph.getNodes()))));
        }

        // Cycles fourmis
        for (int n = 0; n < graph.getSize(); n++) {
            for (Ant ant : this) {
                ant.nextNode();
            }
        }

        // Évaporation
        for (Link link : graph.getAllLinks()) {
            link.setPheromones(link.getPheromones() * Constantes.C);
        }

        boolean cycle_changed = false;

        // On dépose les phéromones
        for (Ant ant : this) {
            ant.deposePheromones();

            // On trouve le meilleur chemin courant
            if (ant.getTotalLength() < minLength) {
                minLength = ant.getTotalLength();
                minPath = ant.getHistory();
                nb_cycle_unchanged = 0;
                cycle_changed = true;
            }
        }

        if (!cycle_changed) {
            nb_cycle_unchanged++;
        }
    }

    public boolean hasFinished() {
        return nb_cycle_unchanged >= Constantes.NB_UNCHANGED;
    }

    public List<Node> getBestNodes() {
        return minPath;
    }
}
