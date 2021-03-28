import model.Colony;
import model.Constantes;
import model.Graph;
import model.Node;
import model.Simulation;
import view.MainWindow;

import java.util.List;

/**
 * Classe principale
 */
public class Main {
    /**
     * Main
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        double[][] values = {
                {0.2, 0.2},
                {0.5, 0.8},
                {0.9, 0.4},
                {0.3, 0.8}
        };

        Simulation simulation = new Simulation(Constantes.NB_NODES);
        // Simulation simulation = new Simulation(values);

        new MainWindow(simulation);
    }
}
