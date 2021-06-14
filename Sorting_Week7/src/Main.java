import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 0 to quit: ");

        while (true) {
            System.out.println("Enter the size of the array: ");

            int sizeOfArray = Integer.parseInt(scanner.nextLine());
            if (sizeOfArray == 0) break;
            System.out.println("Enter the order: 1-> for ascending, 2-> for descending, 3-> for random");
            int order = Integer.parseInt(scanner.nextLine());

            int[] inputArrayForMergeSort = new int[sizeOfArray];
            int[] inputArrayForQuickSort = new int[sizeOfArray];


            for (int i = 0; i < sizeOfArray; i++) {
                int randomNumber = random.nextInt();
                inputArrayForMergeSort[i] = randomNumber;
                inputArrayForQuickSort[i] = randomNumber;
                if (order == 1) {
                    if (i > 0 && (inputArrayForMergeSort[i-1] > inputArrayForMergeSort[i])) {
                        inputArrayForMergeSort[i] = inputArrayForMergeSort[i-1] + 50;
                        inputArrayForQuickSort[i] = inputArrayForQuickSort[i-1] + 50;
                    }
                } else if (order == 2) {
                    if (i > 0 && (inputArrayForMergeSort[i-1] < inputArrayForMergeSort[i])) {
                        inputArrayForMergeSort[i] = inputArrayForMergeSort[i-1] - 50;
                        inputArrayForQuickSort[i] = inputArrayForQuickSort[i-1] - 50;
                    }
                }
            }
//        System.out.println("----------------");
//        for (int i : inputArray){
//            System.out.println(i);
//        }

            double startTimeMerge = System.nanoTime();
            Sort.mergeSort(inputArrayForMergeSort, 0, sizeOfArray-1);
            double endTimeMerge = System.nanoTime();

            double startTimeQuick = System.nanoTime();
            Sort.quickSort(inputArrayForQuickSort, 0, sizeOfArray-1);
            double endTimeQuick = System.nanoTime();


            double timeElapsedMerge = endTimeMerge - startTimeMerge;
            double timeElapsedQuick = endTimeQuick - startTimeQuick;

            System.out.println("Sorted Array By Merge Sort--------------Sorted Array By Quick Sort");
            for (int i = 0; i < sizeOfArray; i++) {
                System.out.println(inputArrayForMergeSort[i] + "  ---------------------------- " + inputArrayForQuickSort[i]);
            }
            System.out.println("");

            System.out.println("Execution time for mergesort in milliseconds: " + timeElapsedMerge / 1000000);
            System.out.println("Execution time for quicksort in milliseconds: " + timeElapsedQuick / 1000000);
        }
    }
}
