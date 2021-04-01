package model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    /**
     * Liens
     */
    private final List<Link> links;

    /**
     * Position X
     */
    private final double x;

    /**
     * Position Y
     */
    private final double y;

    /**
     * Id
     */
    private final int id;

    /**
     * Constructeur
     *
     * @param id Id
     * @param x Position X
     * @param y Position Y
     */
    public Node(int id, double x, double y) {
        this.x = x;
        this.y = y;
        this.id = id;
        links = new ArrayList<>();
    }

    /**
     * Ajoute un lien
     *
     * @param link Lien
     */
    public void addLink(Link link) {
        links.add(link);
    }

    /**
     * Retourne le lien reliant ce nœud et un autre
     *
     * @param neighbour Nœud à chercher
     *
     * @return Lien
     */
    public Link getLink(Node neighbour){
        for (Link link : links)
            if (link.getInput() == neighbour || link.getOutput() == neighbour)
                return link;
        return null;
    }

    /**
     * @return Position X
     */
    public double getX() {
        return x;
    }

    /**
     * @return Position Y
     */
    public double getY() {
        return y;
    }

    /**
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * @return Représentation en String
     */
    @Override
    public String toString() {
        double nx = (double) ((int) (getX() * 100)) / 100;
        double ny = (double) ((int) (getY() * 100)) / 100;

        return id + ":(" + nx + ", " + ny + ")";
    }
}
