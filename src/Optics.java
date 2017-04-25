import java.util.*;

public class Optics {
    public Optics() {

    }

    private class MyComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (a.getReachableDistance() > b.getReachableDistance()) {
                return 1;
            } else if (a.getReachableDistance() == b.getReachableDistance()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    /**
     * @params R 半径
     * @params MinPts 最小点数
     */
    public Vector<Point> run(Vector<Point> points, double R, int MinPts) {
        Vector<Point> result = new Vector<>();
        loopCal(points, R, MinPts);
        result = order(points);
        return result;
    }

    public Vector<Point> order(Vector<Point> points) {
        Vector<Point> orderSeed = new Vector<>();
        Vector<Point> resultSeed = new Vector<>();
        int i = 0;

        while (i < points.size()) {
            Point a = points.get(i);
            i++;
            if (a.getProcessed() != true) {
                resultSeed.add(a);
                a.setProcessed();

                double aCoreDistance = a.getCoreDistance();

                // 取出该点的直接密度可达点
                for (Iterator iterator = a.getDirectReachable().iterator(); iterator.hasNext();) {
                    Point directReachable = (Point) iterator.next();
                    double distance = getDistance(a, directReachable);
                    double reachableDistance = Math.max(distance, aCoreDistance);

                    if (directReachable.getProcessed() != true) { // 如果该点未被处理过
                        directReachable.setReachableDistance(reachableDistance); // 设置可达距离
                        orderSeed.add(directReachable); // 存入有序队列
                    }

                    updateOrder(orderSeed);
                }

                while (orderSeed.size() > 0) {

                    Point b = orderSeed.get(0);
                    orderSeed.remove(0);
                    b.setProcessed();
                    resultSeed.add(b);

                    for (Iterator iterator = b.getDirectReachable().iterator(); iterator.hasNext();) {
                        Point c = (Point) iterator.next();
                        double reachableDistance = Math.max(b.getCoreDistance(), getDistance(b, c));
                        if (c.getProcessed() != true) {
                            if (orderSeed.indexOf(c) != -1) { // 该点在序列中
                                if (c.getReachableDistance() > reachableDistance) {
                                    c.setReachableDistance(reachableDistance); // 小于现有的可达距离,则更新
                                }
                            } else {
                                c.setReachableDistance(reachableDistance);
                                orderSeed.add(c);
                            }
                        }
                        updateOrder(orderSeed);
                    }
                }
            }
        }

//        while (i < points.size()) {
//            Point a = points.get(i);
//            i++;
//
//            System.out.println(i);
//            if (a.getProcessed() != true) {
//
//                Point copyA = new Point(a);
//                System.out.println("2:" + a.getId());
//
//                resultSeed.add(copyA);
//                a.setProcessed(); // 标记已经处理
//
//                for (Iterator iterator = a.getDirectReachable().iterator(); iterator.hasNext(); ) {
//                    Point directReachable = (Point) iterator.next();
//                    System.out.println("3:" + directReachable.getId());
//                    directReachable.setProcessed();
//
//                    double reachableDistance = Math.max(getDistance(a, directReachable), a.getCoreDistance());
//
//                    if (orderSeed.indexOf(directReachable) != -1) { // 存在于有序队列中
//                        if (reachableDistance < directReachable.getReachableDistance()) {
//                            directReachable.setReachableDistance(reachableDistance);
//                            updateOrder(orderSeed);
//                        }
//                    } else {
//                        directReachable.setReachableDistance(reachableDistance);
//                        orderSeed.add(directReachable); // 如果不在有序队列中,直接添加进去
//                        updateOrder(orderSeed);
//                    }
//                    System.out.println("3End");
//
//                }
//
//                while (orderSeed.size() > 0) { // 如果有序队列不为空, 则取第一个点
//                    Point b = orderSeed.get(0);
//                    b.setProcessed(); // 标记已经处理
//                    resultSeed.add(b);
//                    orderSeed.remove(0);
//
//                    for (Iterator iterator = b.getDirectReachable().iterator(); iterator.hasNext(); ) {
//                        Point c = (Point) iterator.next(); //
//
//                        double reachableDistance = Math.max(getDistance(b, c), b.getCoreDistance());
//                        if (orderSeed.indexOf(c) != -1) {
//                            if (reachableDistance < c.getReachableDistance()) {
//                                c.setReachableDistance(reachableDistance);
//                            }
//                        } else {
//                            c.setReachableDistance(reachableDistance);
//                            orderSeed.add(c);
//                        }
//                    }
//
//                    updateOrder(orderSeed);
//                    b.setProcessed();
//                }
//            }
//        }

        return resultSeed;
    }

    public void updateOrder(Vector<Point> orderSeed) {
        Collections.sort(orderSeed, new MyComparator());
    }

    public Vector<Point> loopCal(Vector<Point> points, double R, int MinPts) {
        for (Iterator iterator = points.iterator(); iterator.hasNext(); ) {
            Point point = (Point) iterator.next();

            for (Iterator iterator2 = points.iterator(); iterator2.hasNext(); ) {
                Point point2 = (Point) iterator2.next();
                double distance = getDistance(point, point2);

                if (point.getId() == point2.getId()) {
                    continue;
                }

                if (distance <= R) {
                    point.putDirectReachable(point2);
                    point.putDistanceValue(distance);
                }
            }
        }

        for (Iterator iterator = points.iterator(); iterator.hasNext(); ) {
            Point point = (Point) iterator.next();

            Collections.sort(point.directDistance);
            if (point.getDirectDistance().size() >= MinPts) {
                double coreDistance = point.getDirectDistance().get(MinPts - 1);
                point.setCoreDistance(coreDistance);
                point.setCore();
            }
        }

        return points;
    }

    public Hashtable<String, Vector<Point>> extract(Vector<Point> points) {
        Hashtable<String, Vector<Point>> result = new Hashtable<>();

        int id = 1;

        return result;
    }

    public double getDistance(Point a, Point b) {
        double distance = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
        return distance;
    }

}
