import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextFileComparatorApp extends JFrame {

    private TextFile file1;
    private TextFile file2;

    private final JFrame frame;
    private final JTextArea textAreaFile1;
    private final JTextArea textAreaFile2;
    private final JTextArea textAreaResult;

    public TextFileComparatorApp() {
        frame = new JFrame("Text File Comparator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel fileChooserPanel = new JPanel();
        JButton openFile1Button = new JButton("Открыть 1 файл");
        JButton openFile2Button = new JButton("Открыть 2 файл");

        openFile1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        file1 = new TextFile(selectedFile.getAbsolutePath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    textAreaFile1.setText(file1.getContent());
                }
            }
        });

        openFile2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        file2 = new TextFile(selectedFile.getAbsolutePath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    textAreaFile2.setText(file2.getContent());
                }
            }
        });

        fileChooserPanel.add(openFile1Button);
        fileChooserPanel.add(openFile2Button);

        textAreaFile1 = new JTextArea(20, 40);
        textAreaFile1.setEditable(false);

        textAreaFile2 = new JTextArea(20, 40);
        textAreaFile2.setEditable(false);

        textAreaResult = new JTextArea(10, 20);
        textAreaResult.setEditable(false);

        JButton compareButton = new JButton("Сравнить файлы");
        compareButton.addActionListener(e -> {
            if (file1 != null && file2 != null) {
                TextFileComparator comparator = new TextFileComparator();
                int result = comparator.compare(file1, file2);
                String resultLeftAndRight = comparator.equalsLeftAndRight(file1, file2);
                if (result == 0) {
                    textAreaResult.setText("Файлы идентичны");
                } else {
                    textAreaResult.setText("Файлы не идентичны " + "\n" + resultLeftAndRight);
                }
            }
        });

        frame.add(fileChooserPanel, BorderLayout.NORTH);

        JPanel textAreasPanel = new JPanel();
        textAreasPanel.add(new JScrollPane(textAreaFile1));
        textAreasPanel.add(new JScrollPane(textAreaFile2));
        textAreasPanel.add(new JScrollPane(textAreaResult));

        frame.add(textAreasPanel, BorderLayout.CENTER);
        frame.add(compareButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextFileComparatorApp::new);
    }
}
