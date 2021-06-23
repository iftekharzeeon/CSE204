import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPlants = scanner.nextInt();
        int numberOfFriends = scanner.nextInt();

        Integer[] plantsPrice = new Integer[numberOfPlants];
        int[] friendsPurchased = new int[numberOfFriends];
        int minimumCost = 0;
        for (int i = 0; i < numberOfPlants; i++) {
            plantsPrice[i] = scanner.nextInt();
        }

        Arrays.sort(plantsPrice, Collections.reverseOrder());

        int i = 0;
        int j = 0;
        while (i != numberOfPlants) {
            if (j == numberOfFriends) {
                j= 0;
            }
            minimumCost += plantsPrice[i] * (friendsPurchased[j] + 1);
            i++;
            friendsPurchased[j]++;
            j++;
        }
        System.out.println(minimumCost);
    }
}
