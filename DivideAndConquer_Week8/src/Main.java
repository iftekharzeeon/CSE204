import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner scanner = new Scanner(inputFile);
        int numberOfHouses = Integer.parseInt(scanner.nextLine());

        Point[] pointsOfHousesSortedXWise = new Point[numberOfHouses];
        Point[] pointsOfHousesSortedYWise = new Point[numberOfHouses];

        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            int xAxisValue = Integer.parseInt(line.split(" ")[0]);
            int yAxisValue = Integer.parseInt(line.split(" ")[1]);

            pointsOfHousesSortedYWise[i] = new Point(xAxisValue, yAxisValue, i);
            pointsOfHousesSortedXWise[i] = new Point(xAxisValue, yAxisValue, i);
            i++;
        }
        Algorithm.mergeSortForPointY(pointsOfHousesSortedYWise, 0, numberOfHouses-1);
        Algorithm.mergeSortForPointX(pointsOfHousesSortedXWise, 0, numberOfHouses-1);

//        System.out.println("Minimum with brute Force: " + Algorithm.bruteForce(pointsOfHousesSortedXWise, 0, numberOfHouses-1));

        MinimumPair minimumPair = Algorithm.closestHouse(pointsOfHousesSortedXWise, pointsOfHousesSortedYWise, 0, numberOfHouses-1);

        if (minimumPair.house1ForSecondMin < minimumPair.house2ForSecondMin) {
            System.out.println(minimumPair.house1ForSecondMin + " " + minimumPair.house2ForSecondMin);
        } else {
            System.out.println(minimumPair.house2ForSecondMin + " " + minimumPair.house1ForSecondMin);
        }
        System.out.printf("%.4f", minimumPair.secondMinimum);

    }
}
