package sorter.impl.merge_sort;

import sorter.api.Sorter;

import java.util.*;

/**
 * Реализация интерфейса Sorter<T> сортировкой слияния.
 *
 * @param <T>
 */
public class MergeSort<T> implements Sorter<T> {

    /**
     * Компаратор для сравнения двух элементов массива. При инициализации присваивается
     * компаратор по умолчанию.
     */
    private Comparator<T> comparator = defaultComparator();

    /**
     * Сортировка массива в порядке по умолчанию, заданном реализацией метода
     * compareTo(T) интерфейса Comparable<T>.
     *
     * @param array - массив для сортировки.
     * @throws ClassCastException - если элементы массива не предоставляют реализацию
     *                            метода compareTo интерфеса Comparable<T>.
     */
    @Override
    public void sort(List<T> array) throws ClassCastException {
        sortingProcedure(array);
        comparator = null;
    }

    /**
     * Сортировка массива. Порядок сортировки зависит от компаратора, который
     * передается на вход.
     *
     * @param array      - массив для сортировки.
     * @param comparator - компаратор, в котором предоставлен метод
     */
    @Override
    public void sort(List<T> array, Comparator<T> comparator) {
        this.comparator = comparator;
        sort(array);
    }

    /**
     * Сортировка слиянием. Сначала массив разбивается на каждый отдельный
     * элемент. Затем происходит слияние соседних элементов в отдельные части
     * массива. В процессе слияния соседних частей, происходит сортировка элементов
     * в порядке, заданном компаратором. Слияние продолжается до тех пор, пока все
     * части массива не сольются в отсортированный исходный массив.
     *
     * @param array - исходный массив для сортировки.
     */
    private void sortingProcedure(List<T> array) {
        int size = array.size();
        int partitionSize = 1;
        int step = 2;
        int numberOfPartitions = size / 2;
        int remainder = size - 2 * numberOfPartitions;

        while (size > 1) {
            for (int i = 0; i < numberOfPartitions; i++) {
                int left = i * step;
                int median = left + partitionSize;
                int right = median + partitionSize - 1;
                if (i == numberOfPartitions - 1 && remainder == 0) {
                    right = array.size() - 1;
                }
                merge(array, left, median, right);
                if (i == numberOfPartitions - 1 && remainder == 1) {
                    merge(array, left, right + 1, array.size() - 1);
                }
            }
            step *= 2;
            partitionSize *= 2;
            size = numberOfPartitions;
            numberOfPartitions = size / 2;
            remainder = size - 2 * numberOfPartitions;
        }
    }

    /**
     * Слияние двух соседних частей массива. Слияние сопровождается сортировкой
     * элементов этих частей в порядке, заданным компаратором.
     *
     * @param array  - исходный массив.
     * @param left   - левая граница сливаемой левой части массива.
     * @param median - левая граница сливаемой правой части массива.
     * @param right  - правая граница сливаемой правой части массива.
     */
    private void merge(List<T> array, int left, int median, int right) {
//        T[] leftArray = Arrays.copyOfRange(array, left, median);
        List<T> leftArray = new ArrayList<>(array.subList(left, median));
        int leftCount = 0;
        int rightCount = median;

        for (int i = left; i < rightCount; i++) {
            if (rightCount <= right) {
                final int compare = comparator.compare(leftArray.get(leftCount), array.get(rightCount));
                if (compare < 1) {
//                    array[i] = leftArray[leftCount++];
                    array.set(i, leftArray.get(leftCount++));
                } else {
                    array.set(i, array.get(rightCount++));
//                    array[i] = array[rightCount++];
                }
            } else {
//                array[i] = leftArray[leftCount++];
                array.set(i, leftArray.get(leftCount++));
            }
        }
    }
}
