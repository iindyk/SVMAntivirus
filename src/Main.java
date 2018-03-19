import javax.swing.*;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
        //    final MainWindow wnd = new MainWindow();
        //    wnd.setVisible(true);
        //});
        Predictor pr = Predictor.getInstance();
    }
}

