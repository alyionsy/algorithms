package search;

public class InterpolationSearch {
    public int search(int[] arr, int key) {
        int low = 0, high = arr.length - 1, comparesCalculator = 0;

        while (low <= high && key >= arr[low] && key <= arr[high]) {
            if (low == high) {
                if (arr[low] == key) {
                    comparesCalculator++;
                    System.out.println("(interpolation search) number of compares: " + comparesCalculator);
                    return low;
                }
                else {
                    System.out.println("(interpolation search) number of compares: " + comparesCalculator);
                    return -1;
                }
            }

            double pos_d = low + (((double) (high - low) / (arr[high] - arr[low])) * (key - arr[low]));
            int pos = (int) Math.round(pos_d);

            if (arr[pos] == key) {
                comparesCalculator++;
                System.out.println("(interpolation search) number of compares: " + comparesCalculator);
                return pos;
            }

            if (arr[pos] < key) {
                comparesCalculator++;
                low = pos + 1;
            }
            else {
                high = pos - 1;
            }
        }
        System.out.println("(interpolation search) number of compares: " + comparesCalculator);
        return -1;
    }
}
