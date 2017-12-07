package ru.yandex.vasily.danilin.letterClassificationNetwork;


import javafx.geometry.Point2D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasily Danilin on 05.12.2017.
 */
@SuppressWarnings("WeakerAccess")
public class PaintingPanel extends JPanel {

    private List<Point2D> points = new ArrayList<>();

    public List<Point2D> getPoints() {
        return points;
    }

    public void addPoint(Point2D point) {
        points.add(point);
    }

    public void clear() {
        points.clear();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        for (Point2D point : points)
            graphics.fillOval((int) point.getX(), (int) point.getY(), 10, 10);
    }

}
