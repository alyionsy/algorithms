package sort;

public class MergeSort {
    private void merge(int[] arr, int left, int middle, int right)
    {
        int sizeLeft = middle - left + 1;
        int sizeRight = right - middle;

        int[] arrLeft = new int[sizeLeft];
        int[] arrRight = new int[sizeRight];

        for (int i = 0; i < sizeLeft; ++i) {
            arrLeft[i] = arr[left + i];
        }
        for (int j = 0; j < sizeRight; ++j) {
            arrRight[j] = arr[middle + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < sizeLeft && j < sizeRight) {
            if (arrLeft[i] <= arrRight[j]) {
                arr[k] = arrLeft[i];
                i++;
            }
            else {
                arr[k] = arrRight[j];
                j++;
            }
            k++;
        }

        while (i < sizeLeft) {
            arr[k] = arrLeft[i];
            i++;
            k++;
        }

        while (j < sizeRight) {
            arr[k] = arrRight[j];
            j++;
            k++;
        }
    }

    public void sort(int[] arr, int left, int right, int k)
    {
        if (right - left + 1 <= k) {
            InsertionSort insertionSort = new InsertionSort();
            insertionSort.sort(arr, left, right);
            return;
        }

        if (left < right) {
            int middle = (left + right) / 2;

            sort(arr, left, middle, k);
            sort(arr, middle + 1, right, k);

            merge(arr, left, middle, right);
        }
    }
}
