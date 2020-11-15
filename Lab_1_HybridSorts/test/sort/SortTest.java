package sort;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class SortTest {
    private final int[] input;
    private final int[] expected;

    public SortTest(int[] input, int[] expected) {
        this.input = input;
        this.expected = expected;
    }
    @Parameterized.Parameters
    public static int[][][] dataProvider() {
        return new int[][][] {
                { { 7, 4, 10, 6, 1, 8, 5, 19 }, { 1, 4, 5, 6, 7, 8, 10, 19} },
                { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } },
                { { 2, 6, 7, 7, 7, 8, 9, 0 }, {0, 2, 6, 7, 7, 7, 8, 9} },
                { { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1 } },
                { { -1, 4, -5, 6, -8, -10, 325, 68, -100 }, { -100, -10, -8, -5, -1, 4, 6, 68, 325 } },
                { { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } }
        };
    }

    @Test
    public void mergeSortTest() {
        MergeSort mergeSort = new MergeSort();
        int[] inputClone = input.clone();
        mergeSort.sort(inputClone, 0, inputClone.length - 1, 100);
        Assert.assertArrayEquals(inputClone, expected);
    }

    @Test
    public void quickSortTest() {
        QuickSort quickSort = new QuickSort();
        int[] inputClone = input.clone();
        quickSort.sort(inputClone, 0, inputClone.length - 1, 100);
        Assert.assertArrayEquals(inputClone, expected);
    }
}
