package verificacionenvio.main;

import javax.swing.SwingUtilities;
import verificacionenvio.ui.FormularioEnvio;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormularioEnvio::new);
    }
}
