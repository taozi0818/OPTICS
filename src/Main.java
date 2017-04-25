import java.util.*;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // 开始时间
        String fileName = "//Users//tyt//Desktop//data.txt"; // 文件路径

        double R = 2.0;
        int MinPts = 4;

        Util u = new Util();
        Vector<Point> points = u.inputData(fileName);
        Optics optics = new Optics();
        Vector<Point> result = optics.run(points, R, MinPts);

        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Point point = (Point) iterator.next();
            System.out.println(point.getId() + ": " + point.getCore() + ": " + point.getReachableDistance());
        }
//
//        for (Iterator iterator = points.iterator(); iterator.hasNext();) {
//            Point a = (Point) iterator.next();
//            for (Iterator iterator2 = a.getDirectReachable().iterator(); iterator2.hasNext();) {
//                Point b = (Point) iterator2.next();
//                System.out.println(a.getId() + ": " + b.getId() + ": " + optics.getDistance(a, b));
//            }
//        }

        System.out.println("运行时间为: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
