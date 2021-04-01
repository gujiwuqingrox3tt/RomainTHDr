package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Fourmi
 */
public class Ant {
    /**
     * Nœud courant
     */
    private Node currentNode;

    /**
     * Nœuds disponibles
     */
    private final List<Node> availableNodes;

    /**
     * Nœuds passés
     */
    private final List<Node> history;

    /**
     * Taille totale
     */
    private double totalLength;

    /**
     * Constructeur
     *
     * @param id Id
     * @param nodes Nœuds
     */
    public Ant(int id, List<Node> nodes) {
        availableNodes = new ArrayList<>();
        availableNodes.addAll(nodes);
        this.totalLength = 0;
        this.currentNode = availableNodes.get(id);
        this.history = new ArrayList<>();
        this.availableNodes.remove(this.currentNode);
    }

    /**
     * Nœud suivant
     */
    public void nextNode() {
        // Pas de nœud disponible
        if (availableNodes.size() == 0) {
            history.add(currentNode);
        }
        else {
            // Calcul des poids
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

    /**
     * Dépose les phéromones
     */
    public void deposePheromones() {
        for (int i = 0; i < history.size(); i++) {
            Link link = history.get(i).getLink(history.get((i + 1) % history.size()));
            link.setPheromones(link.getPheromones() + (Constantes.Q / this.totalLength));
        }
    }

    /**
     * Récupère la taille totale
     *
     * @return Taille totale
     */
    public double getTotalLength() {
        return totalLength;
    }

    /**
     * Récupère l'historique
     *
     * @return Historique
     */
    public List<Node> getHistory() {
        return history;
    }
}
