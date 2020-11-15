package run;

import search.BinarySearch;
import search.InterpolationSearch;
import sort.QuickSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int arrSize = 10, minValue = 0, maxValue = 1000;
        int[] arr = randomArrayGenerator(arrSize, minValue, maxValue);
        System.out.println("array: " + Arrays.toString(arr));
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr, 0, arrSize - 1);
        System.out.println("sorted array: " + Arrays.toString(arr));

        BinarySearch binarySearch = new BinarySearch();
        int index = 5;
        String binarySearchResult = "--- BINARY SEARCH ---" + "\nsearching for element: " + arr[index] +
                "\nexpected index: " + index + "\nresult index: " + binarySearch.search(arr, arr[index]);

        InterpolationSearch interpolationSearch = new InterpolationSearch();
        index = 5;
        String interpolationSearchResult = "\n\n--- INTERPOLATION SEARCH ---" + "\nsearching for element: " +
                arr[index] + "\nexpected index: " + index + "\nresult index: " + interpolationSearch.search(arr, arr[index]);;
        System.out.println(binarySearchResult + interpolationSearchResult);
    }

    public static int[] randomArrayGenerator(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = min + (int)(Math.random() * max);
        }
        return arr;
    }
}

