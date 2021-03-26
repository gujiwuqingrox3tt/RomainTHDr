package view;

import model.Colony;
import model.Graph;
import model.Link;
import model.Node;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

public class SimulationCanvas extends Canvas {
    private static final double NODE_RADIUS = 15;

    private Graph graph;
    private List<Node> nodeList;

    public SimulationCanvas(Graph graph, List<Node> nodeList) {
        this.graph = graph;
        this.nodeList = nodeList;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setFont(new Font("ComicSans", Font.BOLD, 16));

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Dessin
        for (int i = 0; i < nodeList.size(); i++) {
            this.drawLink(g2d, nodeList.get(i).getLink(nodeList.get((i+1) % nodeList.size())));
        }
        //         Link link = nodeList.get(i).getLink(nodeList.get(i+1));
        //dernier elt

        Node[] nodes = graph.getNodes();

        g2d.setStroke(new BasicStroke(2));

        for (int i = 0; i < nodes.length; i++) {
            Node node = nodes[i];

            int x = (int) (node.getX() * getWidth() - NODE_RADIUS);
            int y = (int) (node.getY() * getHeight() - NODE_RADIUS);

            g2d.setColor(Color.RED);
            g2d.fillOval(x, y, (int) (NODE_RADIUS*2), (int) (NODE_RADIUS*2));
            g2d.setColor(new Color(127, 0, 0));
            g2d.drawOval(x, y, (int) (NODE_RADIUS * 2), (int) (NODE_RADIUS * 2));

            int stringWidth = g2d.getFontMetrics().stringWidth(String.valueOf(i));
            int stringHeight = g2d.getFontMetrics().getHeight();
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(node.getId()), (int) (x + NODE_RADIUS - stringWidth / 2), (int) (y + NODE_RADIUS + stringHeight / 4));
        }
    }

    public void drawLink(Graphics2D g2d, Link link) {
        g2d.setStroke(new BasicStroke(2));
        // g2d.setColor(new Color(Math.min(255, (int) (255 * link.getPheromones() / 5)), 0, 0));
        g2d.setColor(Color.RED);

        int x1 = (int) (link.getInput().getX() * getWidth());
        int y1 = (int) (link.getInput().getY() * getHeight());
        int x2 = (int) (link.getOutput().getX() * getWidth());
        int y2 = (int) (link.getOutput().getY() * getHeight());

        g2d.drawLine(x1, y1, x2, y2);
    }
}
