import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    public MainWindow() {
        super("My Window");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainWindow.this.setVisible(false);
                MainWindow.this.dispose();
            }
        });

        final JButton check_btn = new JButton("Check for viruses");
        check_btn.addActionListener(e -> JOptionPane.showMessageDialog(MainWindow.this,
                "Button Pressed", "Hey", JOptionPane.INFORMATION_MESSAGE));

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