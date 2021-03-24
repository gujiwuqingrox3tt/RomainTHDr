package view;

import model.Simulation;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setVisible(true);
    }
}
