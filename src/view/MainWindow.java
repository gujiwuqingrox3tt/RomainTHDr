package view;

import model.Simulation;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Fenêtre principale
 */
public class MainWindow extends Frame {
    /**
     * Largeur
     */
    public static final int WIDTH = 1280;

    /**
     * Hauteur
     */
    public static final int HEIGHT = 720;

    /**
     * Constructeur
     *
     * @param simulation Simulation
     */
    public MainWindow(Simulation simulation) {
        setSize(WIDTH, HEIGHT);
        setTitle("TP2 INFO505");
        setLayout(new BorderLayout());

        SimulationCanvas canvas = new SimulationCanvas(simulation);
        add(canvas, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainWindow.this.dispose();
            }
        });

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'r') {
                    simulation.reset();
                    canvas.repaint();
                }
            }
        });

        setVisible(true);
    }
}
