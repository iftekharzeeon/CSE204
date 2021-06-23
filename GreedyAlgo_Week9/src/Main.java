import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
//        scanner.nextLine();
        Integer[] plantsPrice = new Integer[n];
        int[] friendsPurchased = new int[k];
        int minimumCost = 0;
        for (int i = 0; i < n; i++) {
            plantsPrice[i] = scanner.nextInt();
        }
//        scanner.nextLine();
        Arrays.sort(plantsPrice, Collections.reverseOrder());
        int i = 0;
        int j = 0;
        while (i != n) {
            if (j == k) {
                j= 0;
            }
            minimumCost += plantsPrice[i] * (friendsPurchased[j] + 1);
            i++;
            friendsPurchased[j]++;
            j++;
        }
        System.out.println("cost:" + minimumCost);
//        for (int i = 0; i < n; i++) {
//            System.out.println(plantsPrice[i]);
//        }
    }
}
