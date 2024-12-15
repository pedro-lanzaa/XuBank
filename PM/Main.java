
import javax.swing.SwingUtilities;
import view.InicioView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InicioView welcomeFrame = new InicioView();
            welcomeFrame.setVisible(true);
        });
    }
}