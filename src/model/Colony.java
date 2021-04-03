package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Colonie, liste de fourmis
 */
public class Colony extends ArrayList<Ant> {
    /**
     * Graph
     */
    private final Graph graph;

    /**
     * Taille minimale
     */
    private double minLength;

    /**
     * Chemin minimal
     */
    private List<Node> minPath;

    /**
     * Nombre de cycles inchangés
     */
    private int nbCycleUnchanged;

    /**
     * Nombre d'itérations
     */
    private int nbEpoch;

    private final Simulation simulation;

    /**
     * Constructeur
     *
     * @param graph Graph
     */
    public Colony(Simulation simulation, Graph graph) {
        this.simulation = simulation;
        this.graph = graph;
        for (int i = 0; i < graph.getNodes().size(); i++)
            add(null);
        nbCycleUnchanged = 0;
        nbEpoch = 0;
        this.minLength = Double.POSITIVE_INFINITY;
    }

    public void cycleColony() {
        // Fourmis reset
        for (int i = 0; i < size(); i++)
            set(i, new Ant(simulation, i, graph.getNodes()));

        // Cycles des fourmis
        for (int n = 0; n < graph.getSize(); n++)
            for (Ant ant : this)
                ant.nextNode();

        // Évaporation
        for (Link link : graph.getAllLinks())
            link.setPheromones(link.getPheromones() * Constantes.C);

        // Dépôt des phéromones
        for (Ant ant : this) {
            ant.deposePheromones();

            // Meilleur chemin courant
            if (ant.getTotalLength() < minLength) {
                minLength = ant.getTotalLength();
                minPath = ant.getHistory();
            }
        }

        nbEpoch++;
    }

    /**
     * Récupère le nombre d'itérations
     *
     * @return Nombre d'itérations
     */
    public int getNbEpoch() {
        return nbEpoch;
    }

    /**
     * @return Fini ou non
     */
    public boolean hasFinished() {
        int cpt = 0;
        for (Link link : graph.getAllLinks()) {
            if (link.getPheromones() < Constantes.ALMOST_ZERO) {
                cpt++;
            }
        }

        if (nbEpoch >= 100_000) {
            System.out.println("Trop d'itérations");
            return true;
        }

        if (graph.getAllLinks().size() - graph.getNodes().size() - cpt == 0) {
            nbCycleUnchanged ++;
            return (nbCycleUnchanged >= Constantes.NB_UNCHANGED);
        }
        else {
            nbCycleUnchanged = 0;
            return false;
        }
    }

    /**
     * @return Meilleur chemin
     */
    public List<Node> getBestNodes() {
        return minPath;
    }
}
