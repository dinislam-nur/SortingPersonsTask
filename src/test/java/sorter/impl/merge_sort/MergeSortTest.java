package sorter.impl.merge_sort;

import org.junit.jupiter.api.Test;
import sorter.api.Sorter;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void sortingTest() {
        //Prepare
        Integer[] integers = new Integer[]{2, 4, 6, 5, 3, 2, 8, 3};
        Integer[] answer = new Integer[]{2, 2, 3, 3, 4, 5, 6, 8};
        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        //Execute
        integerQuickSort.sort(integers);

        //Assertion
        assertArrayEquals(answer, integers);
    }

    @Test
    void ascTest() {
        //Prepare
        Integer[] integers = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        //Execute
        integerQuickSort.sort(integers);

        //Assertion
        assertArrayEquals(answer, integers);
    }

    @Test
    void descTest() {
        //Prepare
        Integer[] integers = new Integer[]{8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] answer = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        //Execute
        integerQuickSort.sort(integers);

        //Assertion
        assertArrayEquals(answer, integers);
    }

    @Test
    void oneValueTest() {
        //Prepare
        Integer[] integers = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};
        Integer[] answer = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};
        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        //Execute
        integerQuickSort.sort(integers);

        //Assertion
        assertArrayEquals(answer, integers);
    }

    @Test
    void randomGeneratedArrayTest() {
        //Prepare
        final ThreadLocalRandom current = ThreadLocalRandom.current();
        Integer[] array = new Integer[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = current.nextInt(0, 50);
        }
        final Integer[] answer = Arrays.copyOf(array, array.length);
        Arrays.sort(answer);

        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        //Execution
        integerQuickSort.sort(array);

        //Assertion
        assertArrayEquals(answer, array);
    }
}