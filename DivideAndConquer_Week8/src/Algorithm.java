import java.util.LinkedList;

public class Algorithm {

    public static MinimumPair closestHouse(Point[] px, Point[] py, int startIndex, int endIndex) {

        if ((endIndex - startIndex) <= 2) {
            return minimumDistance(px, startIndex, endIndex);
        }

        int middle = (startIndex + endIndex) / 2;

        MinimumPair leftMinimum = closestHouse(px, py, startIndex, middle);
        MinimumPair rightMinimum = closestHouse(px, py, middle+1, endIndex);

        MinimumPair minimumPair = new MinimumPair();

        if (leftMinimum.firstMinimum < rightMinimum.firstMinimum) {
            minimumPair.firstMinimum = leftMinimum.firstMinimum;
            minimumPair.secondMinimum = rightMinimum.firstMinimum;

            minimumPair.house1ForSecondMin = rightMinimum.house1ForFirstMin;
            minimumPair.house2ForSecondMin = rightMinimum.house2ForFirstMin;

            minimumPair.house1ForFirstMin = leftMinimum.house1ForFirstMin;
            minimumPair.house2ForFirstMin = leftMinimum.house2ForFirstMin;

        } else {
            minimumPair.firstMinimum = rightMinimum.firstMinimum;

            minimumPair.house1ForFirstMin = rightMinimum.house1ForFirstMin;
            minimumPair.house2ForFirstMin = rightMinimum.house2ForFirstMin;

            if (leftMinimum.firstMinimum != rightMinimum.firstMinimum) {
                minimumPair.secondMinimum = leftMinimum.firstMinimum;

                minimumPair.house1ForSecondMin = leftMinimum.house1ForFirstMin;
                minimumPair.house2ForSecondMin = leftMinimum.house2ForFirstMin;
            }
        }

        if (leftMinimum.secondMinimum < minimumPair.secondMinimum && leftMinimum.secondMinimum < rightMinimum.secondMinimum) {
            minimumPair.secondMinimum = leftMinimum.secondMinimum;

            minimumPair.house1ForSecondMin = leftMinimum.house1ForSecondMin;
            minimumPair.house2ForSecondMin = leftMinimum.house2ForSecondMin;

        } if (rightMinimum.secondMinimum < minimumPair.secondMinimum && rightMinimum.secondMinimum < leftMinimum.secondMinimum) {
            minimumPair.secondMinimum = rightMinimum.secondMinimum;

            minimumPair.house1ForSecondMin = rightMinimum.house1ForSecondMin;
            minimumPair.house2ForSecondMin = rightMinimum.house2ForSecondMin;
        }

//        double temporarySecondMin = Math.min(leftMinimum.secondMinimum, rightMinimum.secondMinimum);
//        minimumPair.secondMinimum = Math.min(temporarySecondMin, minimumPair.secondMinimum);

        LinkedList<Point> middleArea = new LinkedList<>();

        for (Point point : py) {
            if ((point.getX() >= px[startIndex].getX()) && (point.getX() <= px[endIndex].getX())) {
                middleArea.add(point);
            }
        }


        for (int i = 0; i < middleArea.size(); i++) {
            int count = 0;
            for (int j = i+1; (j < middleArea.size()) && (Math.abs(middleArea.get(j).getY() - middleArea.get(i).getY()) <= minimumPair.firstMinimum); j++) {
                double distance = distanceOfPoints(middleArea.get(i), middleArea.get(j));
                count++;
                if (distance < minimumPair.firstMinimum) {
                    minimumPair.secondMinimum = minimumPair.firstMinimum;
                    minimumPair.firstMinimum = distance;

                    minimumPair.house1ForSecondMin = minimumPair.house1ForFirstMin;
                    minimumPair.house2ForSecondMin = minimumPair.house2ForFirstMin;

                    minimumPair.house1ForFirstMin = middleArea.get(i).getId();
                    minimumPair.house2ForFirstMin = middleArea.get(j).getId();

                } else if ((distance != minimumPair.firstMinimum) && (distance < minimumPair.secondMinimum)) {
                    minimumPair.secondMinimum = distance;

                    minimumPair.house1ForSecondMin = middleArea.get(i).getId();
                    minimumPair.house2ForSecondMin = middleArea.get(j).getId();
                }
            }
//            System.out.println("Loop ran: " + count);
        }

        return minimumPair;
    }

    private static MinimumPair minimumDistance(Point[] points, int startIndex, int endIndex) {
        MinimumPair minimumPair = new MinimumPair();

        int house1ForFirstMin = 0;
        int house2ForFirstMin = 0;

        int house1ForSecondMin = 0;
        int house2ForSecondMin = 0;

        for (int i = startIndex; i <= endIndex; i++) {
            for (int j = i+1; j <= endIndex; j++) {
                double distance = distanceOfPoints(points[i], points[j]);

                if (distance < minimumPair.firstMinimum) {

                    house1ForSecondMin = house1ForFirstMin;
                    house2ForSecondMin = house2ForFirstMin;

                    house1ForFirstMin = points[i].getId();
                    house2ForFirstMin = points[j].getId();

                    minimumPair.secondMinimum = minimumPair.firstMinimum;
                    minimumPair.firstMinimum = distance;

                } else if ((distance != minimumPair.firstMinimum) && (distance < minimumPair.secondMinimum)) {

                    minimumPair.secondMinimum = distance;

                    house1ForSecondMin = points[i].getId();
                    house2ForSecondMin = points[j].getId();
                }
            }
        }
        minimumPair.house1ForSecondMin = house1ForSecondMin;
        minimumPair.house2ForSecondMin = house2ForSecondMin;

        minimumPair.house1ForFirstMin = house1ForFirstMin;
        minimumPair.house2ForFirstMin = house2ForFirstMin;

        return minimumPair;
    }

    public static double bruteForce(Point[] points, int startIndex, int endIndex) {
        double min = Double.MAX_VALUE;
        double secondMin = Double.MAX_VALUE;
        int first = 0;
        int second = 0;
        int point1 = 0;
        int point2 = 0;

        for (int i = startIndex; i <= endIndex; i++) {
            for (int j = i+1; j <= endIndex; j++) {
                double distance = distanceOfPoints(points[i], points[j]);
                if (distance < min) {
                    point1 = first;
                    point2 = second;

                    first = points[i].getId();
                    second = points[j].getId();
                    secondMin = min;
                    min = distance;
                } else if ((distance != min) && (distance < secondMin)) {
                    point1 = points[i].getId();
                    point2 = points[j].getId();
                    secondMin = distance;
                }
            }
        }
        System.out.println("Second Minimum from Brute Force: " + secondMin);
        System.out.println("Point 1: " + point1 + " Point 2: " + point2);
        return min;
    }


    private static double distanceOfPoints(Point p1, Point p2) {
        double dxSquared = Math.pow((p1.getX() - p2.getX()), 2);
        double dySquared = Math.pow((p1.getY() - p2.getY()), 2);

        return Math.sqrt((dxSquared + dySquared));
    }


    public static void mergeSortForPointX(Point[] points, int startIndex, int endIndex){
        if (startIndex < endIndex) {
            int middle = (startIndex + endIndex) / 2;
            mergeSortForPointX(points, startIndex, middle);
            mergeSortForPointX(points, middle+1, endIndex);
            mergeForPointX(points, startIndex, endIndex, middle);
        }
    }

    public static void mergeSortForPointY(Point[] points, int startIndex, int endIndex){
        if (startIndex < endIndex) {
            int middle = (startIndex + endIndex) / 2;
            mergeSortForPointY(points, startIndex, middle);
            mergeSortForPointY(points, middle+1, endIndex);
            mergeForPointY(points, startIndex, endIndex, middle);
        }
    }

    private static void mergeForPointX(Point[] points, int startIndex, int endIndex, int middle) {
        int i = startIndex;
        int j = middle + 1;
        int k = 0;
        Point[] temp = new Point[(endIndex - startIndex) +1];
        for (int l = 0; l < temp.length; l++) {
            temp[l] = new Point();
        }

        while (i <= middle && j <= endIndex) {
            if (points[i].getX() <= points[j].getX()) {
                temp[k].setX(points[i].getX());
                temp[k].setY(points[i].getY());
                temp[k].setId(points[i].getId());
                k++;
                i++;
            } else {
                temp[k].setX(points[j].getX());
                temp[k].setY(points[j].getY());
                temp[k].setId(points[j].getId());
                k++;
                j++;
            }
        }

        while (i <= middle) {
            temp[k].setX(points[i].getX());
            temp[k].setY(points[i].getY());
            temp[k].setId(points[i].getId());
            k++;
            i++;
        }

        while (j <= endIndex) {
            temp[k].setX(points[j].getX());
            temp[k].setY(points[j].getY());
            temp[k].setId(points[j].getId());
            k++;
            j++;
        }

        for (int p = startIndex; p <= endIndex; p++ ) {
            points[p].setX(temp[p-startIndex].getX());
            points[p].setY(temp[p-startIndex].getY());
            points[p].setId(temp[p-startIndex].getId());
        }
    }

    private static void mergeForPointY(Point[] points, int startIndex, int endIndex, int middle) {
        int i = startIndex;
        int j = middle + 1;
        int k = 0;
        Point[] temp = new Point[(endIndex - startIndex) +1];
        for (int l = 0; l < temp.length; l++) {
            temp[l] = new Point();
        }

        while (i <= middle && j <= endIndex) {
            if (points[i].getY() <= points[j].getY()) {
                temp[k].setX(points[i].getX());
                temp[k].setY(points[i].getY());
                temp[k].setId(points[i].getId());
                k++;
                i++;
            } else {
                temp[k].setX(points[j].getX());
                temp[k].setY(points[j].getY());
                temp[k].setId(points[j].getId());
                k++;
                j++;
            }
        }

        while (i <= middle) {
            temp[k].setX(points[i].getX());
            temp[k].setY(points[i].getY());
            temp[k].setId(points[i].getId());
            k++;
            i++;
        }

        while (j <= endIndex) {
            temp[k].setX(points[j].getX());
            temp[k].setY(points[j].getY());
            temp[k].setId(points[j].getId());
            k++;
            j++;
        }

        for (int p = startIndex; p <= endIndex; p++ ) {
            points[p].setX(temp[p-startIndex].getX());
            points[p].setY(temp[p-startIndex].getY());
            points[p].setId(temp[p-startIndex].getId());
        }
    }
}
