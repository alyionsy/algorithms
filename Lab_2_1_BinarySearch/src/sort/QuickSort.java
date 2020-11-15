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

    public void sort(int[] arr, int left, int right)
    {
        if (left < right)
        {
            int partitioningIndex = partition(arr, left, right);

            sort(arr, left, partitioningIndex - 1);
            sort(arr, partitioningIndex + 1, right);
        }
    }
}
