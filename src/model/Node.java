package model;

import java.util.ArrayList;

public class Node {
    private ArrayList<Link> links;
    private double x, y;
    private int id;

    public Node(int id, double x, double y) {
        this.x = x;
        this.y = y;
        this.id = id;
        links = new ArrayList<>();
    }

    public void addLink(Link link) {
        links.add(link);
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public Link getLink(Node neighbour){
        for (Link link : links) {
            if (link.getInput() == neighbour || link.getOutput() == neighbour) {
                return link;
            }
        }
        return null;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
