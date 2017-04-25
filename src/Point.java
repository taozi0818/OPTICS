import java.util.*;

public class Point {
    private double x;
    private double y;
    private int id;
    private double coreDistance = -1; // 核心距离
    private double reachableDistance = -1; // 可达距离
    private Boolean processed = false; // 标记位,是否已经被处理
    private Boolean core = false; // 核心点标记
    public Vector<Point> directReachable = new Vector<>();
    public Vector<Double> directDistance = new Vector<>();

    public Point (int id, double x, double y) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public Point (Point a) {
        this.x = a.x;
        this.y = a.y;
        this.id = a.id;
        this.reachableDistance = a.reachableDistance;
        this.coreDistance = a.coreDistance;
        this.core = a.core;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getCoreDistance() {
        return this.coreDistance;
    }

    public double getReachableDistance() {
        return this.reachableDistance;
    }

    public void setCoreDistance(double coreDistance) {
        this.coreDistance = coreDistance;
    }

    public void setReachableDistance(double reachableDistance) { // 可达距离
        this.reachableDistance = reachableDistance;
    }

    public void setProcessed() {
        this.processed = true;
    }

    public void putDirectReachable(Point a) { // 密度可达点
        this.directReachable.add(a);
    }

    public void putDistanceValue(double distance) { // 密度可达距离,用于计算核心距离
        this.directDistance.add(distance);
    }

    public Boolean getProcessed() {
        return this.processed;
    }

    public Vector<Double> getDirectDistance() {
        return this.directDistance;
    }

    public Vector<Point> getDirectReachable() { // 直接密度可达点
        return this.directReachable;
    }

    public Boolean getCore() {
        return this.core;
    }

    public void setCore() {
        this.core = true;
    }
}
