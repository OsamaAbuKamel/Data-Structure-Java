import java.util.ArrayList;
import java.util.Comparator;

public class ClosestPair {
    public static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double closestPair(ArrayList<Point> points) {
        if (points.size() <= 3) {
            return bruteForce(points);
        }
        points.sort(Comparator.comparingDouble(p -> p.x));
        ArrayList<Point> left = new ArrayList<>(points.subList(0, points.size() / 2));
        ArrayList<Point> right = new ArrayList<>(points.subList(points.size() / 2, points.size()));
        double leftMin = closestPair(left);
        double rightMin = closestPair(right);
        double minDist = Math.min(leftMin, rightMin);
        ArrayList<Point> strip = new ArrayList<>();
        double medianX = points.get(points.size() / 2).x;
        for (Point point : points) {
            if (Math.abs(point.x - medianX) <= minDist) {
                strip.add(point);
            }
        }
        return Math.min(minDist, stripClosest(strip, minDist));
    }

    private static double stripClosest(ArrayList<ClosestPair.Point> strip, double minDist) {
        double minStripDist = Double.MAX_VALUE;
        // Sort strip by y-coordinates
        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && Math.abs(strip.get(j).y - strip.get(i).y) <= minDist; j++) {
                double dist = Math.sqrt(
                        Math.pow(strip.get(i).x - strip.get(j).x, 2) + Math.pow(strip.get(i).y - strip.get(j).y, 2));
                minStripDist = Math.min(minStripDist, dist);
            }
        }
        return minStripDist;
    }

    private static double bruteForce(ArrayList<ClosestPair.Point> points) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double dist = Math.sqrt(Math.pow(points.get(i).x - points.get(j).x, 2)
                        + Math.pow(points.get(i).y - points.get(j).y, 2));
                minDist = Math.min(minDist, dist);
            }
        }
        return minDist;
    }

    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 2));
        points.add(new Point(4, 4));
        points.add(new Point(1, 5));
        points.add(new Point(3, 8));
        // Add your points here (e.g., points.add(new Point(1, 2)));
        double minDist = closestPair(points);
        System.out.println("Minimum distance between closest pair: " + minDist);
    }
}
