public class Sort {
    public static void mergeSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int middle = (leftIndex + rightIndex) / 2;
            mergeSort(arr, leftIndex, middle);
            mergeSort(arr, middle + 1, rightIndex);
            merge(arr, leftIndex, rightIndex, middle);
        }
    }

    public static void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int partitionIndex = partition(arr, leftIndex, rightIndex);
            quickSort(arr, leftIndex, partitionIndex-1);
            quickSort(arr, partitionIndex+1, rightIndex);
        }
    }

    private static int partition(int[] arr, int leftIndex, int rightIndex) {
        int pivot = arr[rightIndex];
        int partitionIndex = leftIndex;
        for (int i = leftIndex; i < rightIndex; i++) {
            if (arr[i] < pivot) {
                int temporary = arr[i];
                arr[i] = arr[partitionIndex];
                arr[partitionIndex] = temporary;
                partitionIndex++;
            }
        }
        int temporary = arr[rightIndex];
        arr[rightIndex] = arr[partitionIndex];
        arr[partitionIndex] = temporary;
        return partitionIndex;
    }

    private static void merge(int[] arr, int leftIndex, int rightIndex, int middle){
        int i = leftIndex;
        int j = middle + 1;
        int k = leftIndex;
        int[] temp = new int[rightIndex+1];

        while (i <= middle && j <= rightIndex) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                k++;
                i++;
            } else {
                temp[k] = arr[j];
                k++;
                j++;
            }
        }

        while (i <= middle) {
            temp[k] = arr[i];
            k++;
            i++;
        }

        while (j <= rightIndex) {
            temp[k] = arr[j];
            k++;
            j++;
        }

        for (int p = leftIndex; p <=rightIndex; p++) {
            arr[p] = temp[p];
        }
    }
}
