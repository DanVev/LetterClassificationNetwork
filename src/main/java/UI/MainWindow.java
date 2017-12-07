package UI;

import javafx.geometry.Point2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by Vasily Danilin on 05.12.2017.
 */
public class MainWindow {

    private JPanel paintingPanel;
    private JPanel rootPanel;
    private JButton clearButton;
    private JPanel SettingPanel;
    private JComboBox letterComboBox;
    private JButton trainButton;
    private JLabel accuracyNumberLabel;
    private JButton predictButton;
    private JLabel predictedLabel;
    private JLabel accuracyLabel;
    private JLabel letterLabel;
    //private  static final String[] letters = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л" ,"М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", ""};
    private static final char[] letters = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ".toCharArray();


    private MainWindow() {

        paintingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                ((PaintingPanel) paintingPanel).addPoint(new Point2D(e.getX(), e.getY()));
                paintingPanel.repaint();
            }
        });
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ((PaintingPanel) paintingPanel).clear();
                letterLabel.setText("");
                accuracyNumberLabel.setText("");
            }
        });
        trainButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        predictButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
    }

    private void createUIComponents() {
        paintingPanel = new PaintingPanel();
        paintingPanel.setSize(300, 400);
        paintingPanel.setMinimumSize(new Dimension(300, 400));
        paintingPanel.setMaximumSize(new Dimension(300, 400));
        paintingPanel.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setSize(380, 400);
        frame.setResizable(false);
        frame.setContentPane(new MainWindow().rootPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);




    }
}
