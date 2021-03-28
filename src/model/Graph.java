package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph
 */
public class Graph {
    /**
     * Nœuds
     */
    private final List<Node> nodes;

    /**
     * Liens
     */
    private final List<Link> links;

    /**
     * Constructeur
     *
     * @param values Tableau 2D des valeurs
     */
    public Graph(double[][] values) {
        int nbNodes = values.length;

        nodes = new ArrayList<>();
        links = new ArrayList<>();

        for (int i = 0; i < nbNodes; i++) {
            nodes.add(new Node(i, values[i][0], values[i][1]));
        }

        for (int i = 0; i < nbNodes; i++) {
            for (int j = i + 1; j < nbNodes; j++) {
                Link lien = new Link(1, nodes.get(i), nodes.get(j));
                nodes.get(i).addLink(lien);
                nodes.get(j).addLink(lien);
                links.add(lien);
            }
        }
    }

    /**
     * @return Nœuds
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * @return Liens
     */
    public List<Link> getAllLinks() {
        return links;
    }

    /**
     * @return Taille
     */
    public int getSize() {
        return nodes.size();
    }
}
