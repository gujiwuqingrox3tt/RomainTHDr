package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    private Node[] nodes;
    private Link[] links;

    public Graph(int nbNodes) {
        // Link[][] liens = new Link[nb][nb];
        nodes = new Node[nbNodes];
        links = new Link[(nbNodes * (nbNodes-1))/2];
        int cptLinks = 0;

        Random rand = new Random();

        for (int i = 0; i < nbNodes; i++) {
            nodes[i] = new Node(i, rand.nextDouble(), rand.nextDouble());
        }

        for (int i = 0; i < nbNodes; i++) {
            for (int j = i + 1; j < nbNodes; j++) {
                Link lien = new Link(1, nodes[i], nodes[j]);
                nodes[i].addLink(lien);
                nodes[j].addLink(lien);

                links[cptLinks] = lien;
                cptLinks++;
            }
        }
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Link[] getAllLinks() {
        return links;
    }

    public int getSize() {
        return nodes.length;
    }
}
