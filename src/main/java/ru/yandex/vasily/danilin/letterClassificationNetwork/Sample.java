package ru.yandex.vasily.danilin.letterClassificationNetwork;

import javafx.geometry.Point2D;

import java.util.List;

/**
 * Created by Vasily Danilin on 06.12.2017.
 */
public class Sample {
    private double[] points;
    private int weight;
    private int height;
    private static double compression = 1;
    private int classNumber;

    public Sample(List<Point2D> points, int weight, int height) {

        this.weight = weight;
        this.height = height;
        setPoints(points);
    }

    public Sample(double[] points, int weight, int height) {
        this.weight = weight;
        this.height = height;
        this.points = points;
    }

    public Sample(double[] points, int weight, int height, int classNumber) {
        this.points = points;
        this.weight = weight;
        this.height = height;
        this.classNumber = classNumber;
    }

    public Sample(List<Point2D> points, int weight, int height, int classNumber) {
        this.weight = weight;
        this.height = height;
        this.classNumber = classNumber;
        setPoints(points);
    }

    public static void setCompression(double compression) {
        Sample.compression = compression;
    }

    public double[] getPoints() {
        return points;
    }

    public void setPoints(List<Point2D> points) {
        int compressionWeight = (int) (weight / compression);
        int compressionHeight = (int) (height / compression);
        double[] result = new double[compressionHeight * compressionWeight];
        for (Point2D point : points)
            result[(int) ((point.getX() / compression) * compressionWeight + (int) (point.getY() / compression))] = 1;
        this.points = result;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
