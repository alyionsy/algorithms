import sort.MergeSort;
import sort.QuickSort;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int arrSize = 100, maxk = 150, minValue = 0, maxValue = 1000, size = 10000;
        int[][] arr = new int[arrSize][];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = randomArrayGenerator(size, minValue, maxValue);
        }

        String quickSortResult = quickSortTest(arr.clone(), maxk);
        String mergeSortResult = mergeSortTest(arr.clone(), maxk);
        System.out.println("\n--- QUICK SORT ---\n" + quickSortResult + "\n\n--- MERGE SORT ---\n" + mergeSortResult);
    }

    public static int[] randomArrayGenerator(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = min + (int)(Math.random() * max);
        }
        return arr;
    }

    public static long findMinElementInList(LinkedList<Long> list) {
        long min = Long.MAX_VALUE;
        for (long element:
             list) {
            if (min > element) {
                min = element;
            }
        }
        return min;
    }

    public static String quickSortTest(int[][] arr, int maxk) {
        int k = 0;
        QuickSort quickSort = new QuickSort();
        long startTime, endTime;
        LinkedList<Long> timeList = new LinkedList<>();

        while (k <= maxk) {
            long timeSum = 0;

            for (int[] subarr:
                    arr) {
                int[] subarrClone = subarr.clone();

                startTime = System.nanoTime();
                quickSort.sort(subarrClone, 0, subarrClone.length - 1, k);
                endTime = System.nanoTime();

                timeSum += endTime - startTime;
            }

            timeList.add(timeSum / arr.length);
            k++;
        }
        long minElement = findMinElementInList(timeList);
        return "min time = " + minElement + " for k = " + timeList.indexOf(minElement);
    }

    public static String mergeSortTest(int[][] arr, int maxk) {
        MergeSort mergeSort = new MergeSort();
        int k = 1;
        long startTime, endTime;
        LinkedList<Long> timeList = new LinkedList<>();

        while (k <= maxk) {
            long timeSum = 0;

            for (int[] subarr:
                    arr) {

                int[] subarrClone = subarr.clone();

                startTime = System.nanoTime();
                mergeSort.sort(subarrClone, 0, subarrClone.length - 1, k);
                endTime = System.nanoTime();

                timeSum += endTime - startTime;
            }

            timeList.add(timeSum / arr.length);
            k++;
        }
        long minElement = findMinElementInList(timeList);
        return "min time = " + minElement + " for k = " + timeList.indexOf(minElement);

    }
}
