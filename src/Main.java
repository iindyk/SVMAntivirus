import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainWindow wnd = new MainWindow();
            wnd.setVisible(true);
        });
    }
}

