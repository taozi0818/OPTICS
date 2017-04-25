import java.util.*;
import java.io.*;

public class Util {

    public Util() {
    }

    public Vector<Point> inputData(String fileName) {
        Vector<Point> points =  new Vector<Point>();
        int id = 1;

        try {

            File file = new File(fileName);
            Scanner scan = new Scanner(file);

            while (scan.hasNext()) {
                String point[] = scan.nextLine().split(",");
                points.add(new Point(id, Double.parseDouble(point[0]), Double.parseDouble(point[1])));
                id++;
            }

            return points;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return points;
    }
}
