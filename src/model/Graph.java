package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    private Node[] nodes;

    public Graph(int nb) {
        // Link[][] liens = new Link[nb][nb];
        nodes = new Node[nb];

        Random rand = new Random();

        for (int i = 0; i < nb; i++) {
            int x = Math.abs(rand.nextInt()) % 100 - 50;
            int y = Math.abs(rand.nextInt()) % 100 - 50;

            nodes[i] = new Node(x, y);
        }

        for (int i = 0; i < nb; i++) {
            for (int j = i + 1; j < nb; j++) {
                //on fait pas GIGA-CHIER
                Link lien = new Link(1, nodes[i], nodes[j]);
                nodes[i].addLink(lien);
                nodes[j].addLink(lien);
            }
        }
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Link[] getAllLinks() {
        return null;
    }
}
