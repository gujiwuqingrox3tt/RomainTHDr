package model;

import java.util.ArrayList;
import java.util.Random;

public class Ant {
    private static final double q = 1;
    private static final double a = 1;
    private static final double b = 1;

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

    public boolean nextNode() {
        /*ArrayList<Link> goodLinks = new ArrayList<>();

        for (Link link : currentNode.getLinks()) {
            //si le lien est valide on l'ajoute

            if (link.getInput() == currentNode) {
                if (availableNodes.contains(link.getOutput())) {
                    currentNode = link.getOutput();
                    goodLinks.add(link);
                }
            }
            else if (link.getOutput() == currentNode) {
                if (availableNodes.contains(link.getInput())) {
                    currentNode = link.getInput();
                    goodLinks.add(link);
                }
            }
            else {
                System.err.println("ptdr t ki?");
            }
        }*/

        //si on as nul part ou aller
        if (availableNodes.size() == 0) {
            return false;
        }

        //on calcule les poids
        double[] weights = new double[availableNodes.size()];
        double total = 0;
        Link linkCour;

        for (int i = 0; i < availableNodes.size(); i++) {
            linkCour = currentNode.getLink(availableNodes.get(i));
            weights[i] = Math.pow(q * (linkCour.getPheromones()), a) * Math.pow(1 / (linkCour.getLength()), b);
            total += weights[i];
        }
        //random
        Random rdm = new Random();
        double res = rdm.nextDouble();
        double tmp = 0;
        for (int i = 0; i < availableNodes.size(); i++) {
            tmp += weights[i];
            if (res <= (tmp/total)) {
                history.add(currentNode);
                currentNode = availableNodes.get(i);
                availableNodes.remove(i);
                break;
            }
        }

        return true;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public double getTotalLength() {
        return totalLength;
    }
}
