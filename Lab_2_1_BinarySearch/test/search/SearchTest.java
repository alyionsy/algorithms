package search;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class SearchTest {
    private final int[] input;
    private final int[] help;

    public SearchTest(int[] input, int[] help) {
        this.input = input;
        this.help = help;
    }
    @Parameterized.Parameters
    public static int[][][] dataProvider() {
        return new int[][][] {
                { { 1, 4, 5, 6, 7, 8, 10, 19 }, { 1, 0 } },
                { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 6, 5 } },
                { { 2, 6, 6, 7, 8, 8, 9, 10 }, { 7, 3 } },
                { { -100, -10, -8, -5, -1, 4, 6, 68, 325 }, { 100, -1 } },
                { { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 10, -1 } },
                { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 10, 9 } }
        };
    }

    @Test
    public void binarySearchTest() {
        BinarySearch binarySearch = new BinarySearch();
        int key = help[0];
        int expected = help[1];
        Assert.assertEquals(binarySearch.search(input, key), expected);
    }

    @Test
    public void interpolationSearchTest() {
        InterpolationSearch interpolationSearch = new InterpolationSearch();
        int key = help[0];
        int expected = help[1];
        Assert.assertEquals(interpolationSearch.search(input, key), expected);
    }
}
