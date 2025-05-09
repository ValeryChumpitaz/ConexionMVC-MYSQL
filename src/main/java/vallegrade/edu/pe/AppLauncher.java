package vallegrade.edu.pe;

import vallegrade.edu.pe.view.TaskView;

public class AppLauncher {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TaskView().setVisible(true);
        });
    }
}
