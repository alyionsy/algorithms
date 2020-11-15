package sort;

public class QuickSort {
    private int partition(int[] arr, int left, int right)
    {
        int pivot = arr[right];
        int wall = (left - 1);
        for (int i = left; i < right; i++)
        {
            if (arr[i] < pivot)
            {
                wall++;
                int temp = arr[wall];
                arr[wall] = arr[i];
                arr[i] = temp;
            }
        }

        int temp = arr[wall + 1];
        arr[wall + 1] = arr[right];
        arr[right] = temp;

        return wall + 1;
    }

    public void sort(int[] arr, int left, int right, int k)
    {
        if (right - left + 1 <= k) {
            InsertionSort insertionSort = new InsertionSort();
            insertionSort.sort(arr, left, right);
            return;
        }

        if (left < right)
        {
            int partitioningIndex = partition(arr, left, right);

            sort(arr, left, partitioningIndex - 1, k);
            sort(arr, partitioningIndex + 1, right, k);
        }
    }
}
