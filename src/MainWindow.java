import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.logging.*;

public class MainWindow extends JFrame {

    private JTextArea textArea = new JTextArea(20, 30);

    private final static Logger LOGGER = Logger.getLogger(MainWindow.class.getName());

    MainWindow() {
        super("SVM Antivirus");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        WindowHandler logHandler = WindowHandler.getInstance();
        logHandler.setWindow(this);
        LOGGER.addHandler(logHandler);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainWindow.this.setVisible(false);
                MainWindow.this.dispose();
            }
        });

        final JFileChooser fc = new JFileChooser();

        final JButton check_btn = new JButton("Check for viruses");
        check_btn.addActionListener(e -> {
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
        });

        final JButton train_btn = new JButton("Train predictor");
        train_btn.addActionListener(e -> JOptionPane.showMessageDialog(MainWindow.this,
                "is not ready yet:(", "todo", JOptionPane.WARNING_MESSAGE));


        setLayout(new FlowLayout());
        add(check_btn);
        add(train_btn);
        add(new JScrollPane(textArea));
        pack();
        setLocationRelativeTo(null);
        setSize(400, 400);
        LOGGER.log(Level.INFO,"Program initialized");
    }

    public void showInfo(String data) {
        textArea.append(data);
        this.validate();
    }
}

class WindowHandler extends Handler {
    private MainWindow window;

    private static WindowHandler handler = null;

    private WindowHandler() {
        LogManager manager = LogManager.getLogManager();
        String className = this.getClass().getName();
        String level = manager.getProperty(className + ".level");
        setLevel(level != null ? Level.parse(level) : Level.INFO);
        setFormatter( new SimpleFormatter());
    }

    public void setWindow(MainWindow window){
        this.window=window;
    }

    public static synchronized WindowHandler getInstance() {
        if (handler == null) {
            handler = new WindowHandler();
        }
        return handler;
    }

    public synchronized void publish(LogRecord record) {
        String message;
        if (!isLoggable(record))
            return;
        message = getFormatter().format(record);
        window.showInfo(message);
    }

    public void close() {
    }

    public void flush() {
    }
}