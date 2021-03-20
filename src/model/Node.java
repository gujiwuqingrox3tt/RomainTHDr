package model;

import java.util.ArrayList;

public class Node {
    private ArrayList<Link> links;
    private int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        links = new ArrayList<Link>();
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
