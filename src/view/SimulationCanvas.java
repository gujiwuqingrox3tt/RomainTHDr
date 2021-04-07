package view;

import model.Link;
import model.Node;
import model.Simulation;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

/**
 * Canvas de la simulation
 */
public class SimulationCanvas extends Canvas {
    /**
     * Rayon d'un nœud
     */
    private static final double NODE_RADIUS = 15;

    /**
     * Simulation
     */
    private final Simulation simulation;

    /**
     * Constructeur
     *
     * @param simulation Simulation
     */
    public SimulationCanvas(Simulation simulation) {
        this.simulation = simulation;
    }

    /**
     * Affichage
     *
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        List<Node> nodeList = simulation.getColony().getBestNodes();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setFont(new Font("ComicSans", Font.BOLD, 16));

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (Link link : simulation.getGraph().getAllLinks()) {
            drawLink(g2d, link, false);
        }

        // Liens
        for (int i = 0; i < nodeList.size(); i++) {
            drawLink(g2d, nodeList.get(i).getLink(nodeList.get((i+1) % nodeList.size())), true);
        }

        List<Node> nodes = simulation.getGraph().getNodes();

        g2d.setStroke(new BasicStroke(2));

        // Nœuds
        for (Node node : nodes) {
            drawNode(g2d, node);
        }
    }

    /**
     * Dessine un nœud
     *
     * @param g2d Graphiques
     * @param node Nœud
     */
    private void drawNode(Graphics2D g2d, Node node) {
        int x = (int) (node.getX() * getWidth() - NODE_RADIUS);
        int y = (int) (node.getY() * getHeight() - NODE_RADIUS);

        g2d.setColor(Color.RED);
        g2d.fillOval(x, y, (int) (NODE_RADIUS*2), (int) (NODE_RADIUS*2));
        g2d.setColor(new Color(127, 0, 0));
        g2d.drawOval(x, y, (int) (NODE_RADIUS * 2), (int) (NODE_RADIUS * 2));

        int stringWidth = g2d.getFontMetrics().stringWidth(String.valueOf(node.getId()));
        int stringHeight = g2d.getFontMetrics().getHeight();
        g2d.setColor(Color.BLACK);
        g2d.drawString(String.valueOf(node.getId()), (int) (x + NODE_RADIUS - stringWidth / 2), (int) (y + NODE_RADIUS + stringHeight / 4));
    }

    /**
     * dessine un lien
     *
     * @param g2d graphique
     * @param link lien
     */
    private void drawLink(Graphics2D g2d, Link link, boolean finalState) {
        if (finalState) {
            g2d.setStroke(new BasicStroke(3));
            g2d.setColor(Color.RED);
        }
        else {
            g2d.setStroke(new BasicStroke(Math.min(3.f, Math.max((float) link.getPheromones(), 1.8f))));
            g2d.setColor(new Color(63, 0, 0));
        }

        int x1 = (int) (link.getInput().getX() * getWidth());
        int y1 = (int) (link.getInput().getY() * getHeight());
        int x2 = (int) (link.getOutput().getX() * getWidth());
        int y2 = (int) (link.getOutput().getY() * getHeight());

        g2d.drawLine(x1, y1, x2, y2);
    }
}
