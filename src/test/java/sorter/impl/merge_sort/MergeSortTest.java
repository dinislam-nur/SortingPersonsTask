package sorter.impl.merge_sort;

import org.junit.jupiter.api.Test;
import sorter.api.Sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void sortingTest() {
        List<Integer> integers = Arrays.asList(2, 4, 6, 5, 3, 2, 8, 3);
        List<Integer> answer = Arrays.asList(2, 2, 3, 3, 4, 5, 6, 8);
        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        integerQuickSort.sort(integers);

        for (int i = 0; i < integers.size(); i++) {
            assertEquals(answer.get(i), integers.get(i));
        }
    }

    @Test
    void ascTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> answer = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        integerQuickSort.sort(integers);

        for (int i = 0; i < integers.size(); i++) {
            assertEquals(answer.get(i), integers.get(i));
        }
    }

    @Test
    void descTest() {
        List<Integer> integers = Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1);
        List<Integer> answer = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        integerQuickSort.sort(integers);

        for (int i = 0; i < integers.size(); i++) {
            assertEquals(answer.get(i), integers.get(i));
        }
    }

    @Test
    void oneValueTest() {
        List<Integer> integers = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0);
        List<Integer> answer = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0);
        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        integerQuickSort.sort(integers);

        for (int i = 0; i < integers.size(); i++) {
            assertEquals(answer.get(i), integers.get(i));
        }
    }

    @Test
    void randomGeneratedArrayTest() {
        final ThreadLocalRandom current = ThreadLocalRandom.current();
        final int size = 1000;
        List<Integer> array = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            array.add(current.nextInt(0, 50));
        }
        final List<Integer> answer = new ArrayList<>(array);
        answer.sort(Integer::compareTo);

        final Sorter<Integer> integerQuickSort = new MergeSort<>();

        integerQuickSort.sort(array);

        for (int i = 0; i < size; i++) {
            assertEquals(answer.get(i), array.get(i));
        }
    }
}