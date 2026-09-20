package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPairSolver {
    private long comparisons;
    private int recursionDepth;
    private int maxRecursionDepth;
    public Point[] findClosestPair(Point[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException(
                    "At least two points are required"
            );
        }
        comparisons = 0;
        recursionDepth = 0;
        maxRecursionDepth = 0;
        Point[] pointsByX = points.clone();
        Arrays.sort(pointsByX, Comparator.comparingDouble(Point::getX));
        return closestPair(pointsByX);
    }

    private Point[] closestPair(Point[] points) {
        int n = points.length;
        if (n <= 3) {
            return bruteForce(points);
        }
        int middle = n / 2;
        Point[] left = Arrays.copyOfRange(points, 0, middle);
        Point[] right = Arrays.copyOfRange(points, middle, n);
        Point[] leftPair = closestPair(left);
        Point[] rightPair = closestPair(right);
        double leftDistance = leftPair[0].distance(leftPair[1]);
        double rightDistance = rightPair[0].distance(rightPair[1]);
        double minDistance;
        if (leftDistance < rightDistance) {
            minDistance = leftDistance;
        } else {
            minDistance = rightDistance;
        }
        double middleX = points[middle].getX();
        List<Point> strip = new ArrayList<>();
        for (Point point : points) {
            if (Math.abs(point.getX() - middleX) < minDistance) {
                strip.add(point);
            }
        }
        strip.sort(Comparator.comparingDouble(Point::getY));
        Point[] stripPair = checkStrip(strip, minDistance);
        if (stripPair != null) {
            return stripPair;
        }
        if (leftDistance < rightDistance) {
            return leftPair;
        }
        return rightPair;
    }

    private Point[] checkStrip(List<Point> strip, double minDistance) {
        double bestDistance = minDistance;
        Point[] bestPair = null;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size(); j++) {
                double yDifference =
                        strip.get(j).getY() - strip.get(i).getY();
                if (yDifference >= bestDistance) {
                    break;
                }
                comparisons++;
                double distance =
                        strip.get(i).distance(strip.get(j));
                if (distance < bestDistance) {
                    bestDistance = distance;
                    bestPair = new Point[]{
                            strip.get(i),
                            strip.get(j)
                    };
                }
            }
        }
        return bestPair;
    }
    private Point[] bruteForce(Point[] points) {
        double minDistance = Double.MAX_VALUE;
        Point first = null;
        Point second = null;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                comparisons++;
                double distance =
                        points[i].distance(points[j]);
                if (distance < minDistance) {
                    minDistance = distance;

                    first = points[i];
                    second = points[j];
                }
            }
        }
        return new Point[]{first, second};
    }
    public long getComparisons() {
        return comparisons;
    }
}