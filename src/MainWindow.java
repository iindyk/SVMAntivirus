import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.logging.Level;
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
                    Predictor predictor = Predictor.getInstance();
                    File file = fc.getSelectedFile();
                    Boolean result = predictor.predict(file);
                    LOGGER.log(Level.INFO, "result for file "+file.getAbsolutePath()+
                            " is " + result);
                    if (result) {
                        JOptionPane.showMessageDialog(MainWindow.this,
                                "It's a virus!", "Virus check", JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(MainWindow.this,
                                "File os ok!", "Virus check", JOptionPane.INFORMATION_MESSAGE);
                    }
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