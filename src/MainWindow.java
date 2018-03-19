import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

public class MainWindow extends JFrame {

    private final static Logger LOGGER = Logger.getLogger(MainWindow.class.getName());

    public MainWindow() {
        super("SVM Antivirus");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainWindow.this.setVisible(false);
                MainWindow.this.dispose();
            }
        });

        final JFileChooser fc = new JFileChooser();

        final JButton check_btn = new JButton("Check for viruses");
        check_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(MainWindow.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    //File file = fc.getSelectedFile();
                    //This is where a real application would open the file.
                    //log.append("Opening: " + file.getName() + "." + newline);
                } else {
                    //log.append("Open command cancelled by user." + newline);
                }
            }
        });

        final JButton train_btn = new JButton("Train predictor");
        train_btn.addActionListener(e -> JOptionPane.showMessageDialog(MainWindow.this,
                "is not ready yet:(", "todo", JOptionPane.WARNING_MESSAGE));


        setLayout(new FlowLayout());
        add(check_btn);
        add(train_btn);
        pack();
        setLocationRelativeTo(null);
    }
}