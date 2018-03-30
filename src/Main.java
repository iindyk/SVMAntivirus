import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainWindow wnd = new MainWindow();
            wnd.setVisible(true);
        });
    }
}

