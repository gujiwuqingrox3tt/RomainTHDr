package view;

import model.Colony;
import model.Graph;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    public MainWindow(Graph graph, Colony colony) {
        setSize(WIDTH, HEIGHT);
        setTitle("TP2 INFO505");
        setLayout(new BorderLayout());

        SimulationCanvas canvas = new SimulationCanvas(graph, colony.getBestNodes());
        add(canvas, BorderLayout.CENTER);

        /*Panel panelDown = new Panel(new GridLayout(3, 2));

        panelDown.add(new Label("Nb cycles"), 0, 0);
        Label nbCyclesLabel = new Label("0");
        panelDown.add(nbCyclesLabel, 0, 1);

        TextField textField = new TextField("1");
        panelDown.add(textField, 1, 0);

        Button button = new Button("Avance");
        button.addActionListener(e -> {
            int nb;

            try {
                nb = Integer.parseInt(textField.getText());
            }
            catch (NumberFormatException ex) {
                return;
            }

            nbCyclesLabel.setText(String.valueOf(Integer.parseInt(nbCyclesLabel.getText()) + nb));

            for (int i = 0; i < nb; i++) {
                colony.cycleColony();
            }

            canvas.repaint();
        });
        panelDown.add(button, 1, 1);

        add(panelDown, BorderLayout.EAST);*/

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
