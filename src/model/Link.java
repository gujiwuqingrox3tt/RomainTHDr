package model;

public class Link {
    private double length;
    private double pheromones;
    private Node input, output;

    public Link(double pheromones, Node input, Node output) {
        this.length = Math.sqrt(Math.pow(input.getX() - output.getX(), 2) + Math.pow(input.getY() - output.getY(), 2));
        this.pheromones = pheromones;
        this.input = input;
        this.output = output;
    }

    public double getLength() {
        return length;
    }

    public double getPheromones() {
        return pheromones;
    }

    public Node getInput() {
        return input;
    }

    public Node getOutput() {
        return output;
    }

    public void setPheromones(double pheromones) {
        this.pheromones = pheromones;
    }
}
