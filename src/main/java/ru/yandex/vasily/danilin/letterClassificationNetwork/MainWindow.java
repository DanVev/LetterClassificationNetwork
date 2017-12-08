package ru.yandex.vasily.danilin.letterClassificationNetwork;

import javafx.geometry.Point2D;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;
import java.util.Arrays;

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
    private JButton saveButton;
    private static MultiLayerPerceptron network = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 30 * 40, 30 * 40, 10);
    private static DataSet dataSet = new DataSet(30 * 40, 10);

    //private  static final String[] numbers = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л" ,"М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", ""};
    private static final String[] numbers = "0123456789".split("");

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
                clearFieldsandPanels();
            }
        });
        trainButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (dataSet.size() != 0)
                    network.learn(dataSet);
                dataSet.clear();
            }
        });
        predictButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DataSet testDataSet = new DataSet(30 * 40, 10);
                network.setInput(new Sample(((PaintingPanel) paintingPanel).getPoints(), 300, 400).getPoints());
                network.calculate();
                double[] networkOutput = network.getOutput();
                double sum = Arrays.stream(networkOutput).sum();
                double max = Arrays.stream(networkOutput).max().getAsDouble();
                int index = -1;
                for (int i = 0; i < networkOutput.length; i++) {
                    if ((networkOutput[i] - 0.01 < max) && (networkOutput[i] + 0.01 > max))
                        index = i;
                }
                accuracyNumberLabel.setText(String.valueOf(new DecimalFormat("#.##").format(max / sum)));
                letterLabel.setText(String.valueOf(numbers[index]));

            }

        });
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                double[] desiredOutput = new double[10];
                desiredOutput[letterComboBox.getSelectedIndex()] = 1;
                dataSet.addRow(new DataSetRow(new Sample(((PaintingPanel) paintingPanel).getPoints(), 300, 400).getPoints(), desiredOutput));
                clearFieldsandPanels();
            }
        });
    }

    private void clearFieldsandPanels() {
        ((PaintingPanel) paintingPanel).clear();
        letterLabel.setText("");
        accuracyNumberLabel.setText("");
    }

    @SuppressWarnings("unchecked")
    private void createUIComponents() {
        letterComboBox = new JComboBox(new DefaultComboBoxModel<>(numbers));
        paintingPanel = new PaintingPanel();
        paintingPanel.setSize(300, 400);
        paintingPanel.setMinimumSize(new Dimension(300, 400));
        paintingPanel.setMaximumSize(new Dimension(300, 400));
        paintingPanel.repaint();
    }

    public static void main(String[] args) {
        Sample.setCompression(10);
        JFrame frame = new JFrame("MainWindow");
        System.out.println("create windows");
        frame.setSize(380, 400);
        frame.setResizable(false);
        frame.setContentPane(new MainWindow().rootPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
