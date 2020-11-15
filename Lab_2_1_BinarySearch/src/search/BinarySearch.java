package search;

public class BinarySearch {
    public int search(int[] arr, int key) {
        int ind = -1, low = 0, high = arr.length - 1, comparesCalculator = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] < key) {
                comparesCalculator++;
                low = mid + 1;
            } else if (arr[mid] > key) {
                comparesCalculator += 2;
                high = mid - 1;
            } else if (arr[mid] == key) {
                comparesCalculator += 3;
                ind = mid;
                break;
            }
        }
        System.out.println("(binary search) number of compares: " + comparesCalculator);
        return ind;
    }
}
