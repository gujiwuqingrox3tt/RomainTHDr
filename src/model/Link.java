package model;

/**
 * Lien
 */
public class Link {
    /**
     * Taille du lien
     */
    private final double length;

    /**
     * Entrée
     */
    private final Node input;

    /**
     * Sortie
     */
    private final Node output;

    /**
     * Quantité de phéromones
     */
    private double pheromones;

    /**
     * Constructeur
     *
     * @param pheromones Phéromones
     * @param input Entrée
     * @param output Sortie
     */
    public Link(double pheromones, Node input, Node output) {
        this.length = Math.sqrt(Math.pow(input.getX() - output.getX(), 2) + Math.pow(input.getY() - output.getY(), 2));
        this.pheromones = pheromones;
        this.input = input;
        this.output = output;
    }

    /**
     * @return Taille
     */
    public double getLength() {
        return length;
    }

    /**
     * @return Phéromones
     */
    public double getPheromones() {
        return pheromones;
    }

    /**
     * @return Nœud d'entrée
     */
    public Node getInput() {
        return input;
    }

    /**
     * @return Nœud de sortie
     */
    public Node getOutput() {
        return output;
    }

    /**
     * Set les phéromones
     *
     * @param pheromones Phéromones
     */
    public void setPheromones(double pheromones) {
        this.pheromones = pheromones;
    }
}
