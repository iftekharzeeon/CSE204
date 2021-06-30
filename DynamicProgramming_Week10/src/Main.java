import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner scanner = new Scanner(inputFile);

        final int modulo = 1000000007;

        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        int numberOfDices = Integer.parseInt(firstLine.split(" ")[0]);
        int targetSum = Integer.parseInt(firstLine.split(" ")[1]);
        int[] diceFaces = new int[numberOfDices];

        for (int i = 0; i < numberOfDices; i++){
            diceFaces[i] = Integer.parseInt(secondLine.split(" ")[i]);
        }

        int[][] countTable = new int[numberOfDices][targetSum+1];

        for (int i = 1; ( i <= targetSum && i <= diceFaces[0]); i++) {
            countTable[0][i] = 1;
        }

        for (int i = 1; i < numberOfDices; i++) {
            for (int j = 1; j <= targetSum; j++) {
                for (int k = 1; (k < j && k <= diceFaces[i]); k++) {
                    countTable[i][j] = (countTable[i][j] + countTable[i-1][j-k]) % modulo;
                }
            }
        }

        System.out.println(countTable[numberOfDices-1][targetSum]);

    }
}
