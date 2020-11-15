package sort;

public class InsertionSort {
    public void sort(int[] arr, int left, int right)
    {
        for (int i = left + 1; i <= right; ++i) { // 5
            int key = arr[i]; // 2
            int j = i - 1; // 2

            while (j >= left && arr[j] > key) { // 4
                arr[j + 1] = arr[j]; // 4
                j--; // 2
            }
            arr[j + 1] = key; // 2
            // total: 21
        }
    }
}
