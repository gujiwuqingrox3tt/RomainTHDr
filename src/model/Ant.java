package model;

import java.util.ArrayList;
import java.util.Random;

public class Ant {
    private Node currentNode;
    private ArrayList<Node> availableNodes;
    private ArrayList<Node> history;
    private double totalLength;

    public Ant(int id, ArrayList<Node> nodes) {
        this.availableNodes = (ArrayList<Node>) nodes.clone();
        this.totalLength = 0;
        this.currentNode = availableNodes.get(id);
        this.history = new ArrayList<>();
        this.availableNodes.remove(this.currentNode);
    }

    public void nextNode() {
        // Si l'on a nulle part où aller
        if (availableNodes.size() == 0) {
            history.add(currentNode);
        }
        else {
            // On calcule les poids
            double[] weights = new double[availableNodes.size()];
            double total = 0;
            Link linkCour;

            for (int i = 0; i < availableNodes.size(); i++) {
                linkCour = currentNode.getLink(availableNodes.get(i));
                weights[i] = Math.pow(Constantes.Q * (linkCour.getPheromones()), Constantes.a) * Math.pow(1 / (linkCour.getLength()), Constantes.b);
                total += weights[i];
            }

            // Random
            Random rdm = new Random();
            double res = rdm.nextDouble();
            double tmp = 0;
            for (int i = 0; i < availableNodes.size(); i++) {
                tmp += weights[i];
                if (res <= (tmp / total)) {
                    Node nextNode = availableNodes.get(i);
                    history.add(currentNode);
                    totalLength += currentNode.getLink(availableNodes.get(i)).getLength();
                    currentNode = nextNode;
                    availableNodes.remove(i);
                    break;
                }
            }
        }
    }

    public void deposePheromones() {
        Link link;
        for (int i = 0; i < history.size(); i++) {
            link = history.get(i).getLink(history.get((i+1)%history.size()));
            link.setPheromones(link.getPheromones() + (Constantes.Q/this.totalLength));
        }
    }

    public double getTotalLength() {
        return totalLength;
    }

    public ArrayList<Node> getHistory() {
        return history;
    }
}
