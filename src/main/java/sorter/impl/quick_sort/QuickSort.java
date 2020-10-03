package sorter.impl.quick_sort;

import sorter.api.Sorter;
import sorter.impl.AbstractSorter;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Реализация интерфейса Sorter<T> quicksort сортировкой.
 *
 * @param <T> - тип массива.
 */
public class QuickSort<T> extends AbstractSorter<T> {

    /**
     * Компаратор для сравнения двух элементов массива. При инициализации присваивается
     * компаратор по умолчанию.
     */
    private Comparator<T> comparator = defaultComparator();

    /**
     * Генератор рандомных чисел.
     */
    private static final ThreadLocalRandom CURRENT = ThreadLocalRandom.current();

    /**
     * Сортировка массива в порядке по умолчанию, заданном реализацией метода
     * compareTo(T) интерфейса Comparable<T>.
     *
     * @param array - массив для сортировки.
     * @throws ClassCastException - если элементы массива не предоставляют реализацию
     *                            метода compareTo интерфеса Comparable<T>.
     */
    @Override
    public void sort(T[] array) throws ClassCastException {
        sortingProcedure(array, 0, array.length - 1);
        comparator = null;
    }

    /**
     * Сортировка массива. Порядок сортировки зависит от компаратора, который
     * передается на вход.
     *
     * @param array      - массив для сортировки.
     * @param comparator - компаратор, в котором предоставлен метод для сравнения
     *                   двух элементов массива.
     */
    @Override
    public void sort(T[] array, Comparator<T> comparator) {
        this.comparator = comparator;
        sort(array);
    }

    /**
     * Сортировка массива array по алгоритму quicksort сортировки. Рандомно выбирается
     * ключевой элемент. Вызывается метод partition. Элементы меньше ключевого располагаются
     * слева от ключевого. Элементы больше - справа от ключевого. Процедура рекурсивно
     * повторяется для левой и правой частей массива, пока весь массив не будет отсортирован.
     *
     * @param array - массив для сортировки.
     * @param left  - индекс левого конца части массива, передающегося для сортировки.
     * @param right - индекс правого конца части массива, передающегося для сортировки.
     */
    private void sortingProcedure(T[] array, int left, int right) {

        while (left < right) {
            int random = CURRENT.nextInt(left, right + 1);
            if (random != left) {
                swapElements(array, left, random);
            }
            int[] medians = partition(array, left, right);
            int leftMedian = medians[0];
            int rightMedian = medians[1];
            if (Math.abs(leftMedian - left) < Math.abs(right - rightMedian)) {
                sortingProcedure(array, left, leftMedian);
                left = rightMedian;
            } else {
                sortingProcedure(array, rightMedian, right);
                right = leftMedian;
            }
        }
    }

    /**
     * Для определенной части массива выполняется поиск расположения ключевого элемента в
     * отсортированном массиве.
     *
     * @param array - массив для сортировки.
     * @param left  - индекс левого конца части массива.
     * @param right - индекс правого конца части массива.
     * @return - исходный массив, в котором элементы меньше ключевого располагаются слева
     * от ключевого, а элементы больше - справа от ключевого, соответственно.
     */
    private int[] partition(T[] array, int left, int right) {
        int[] boundary = new int[2];

        int cursor = left + 1;
        int cursorForEqualsElements = cursor;
        for (int i = left + 1; i <= right; i++) {
            final int compare = comparator.compare(array[i], array[left]);
            if (compare == 0) {
                if (i != cursorForEqualsElements) {
                    swapElements(array, cursor, i);
                    swapElements(array, cursorForEqualsElements, cursor);
                }
                cursorForEqualsElements++;
                cursor++;
            }
            if (compare < 0) {
                if (i != cursor) {
                    swapElements(array, cursor, i);
                }
                cursor++;
            }
        }
        boundary[1] = cursor;
        for (int i = left; i < cursorForEqualsElements; i++) {
            swapElements(array, i, cursor - (i - left) - 1);
        }
        boundary[0] = cursor - (cursorForEqualsElements - left) - 1;
        return boundary;
    }

    /**
     * Меняет местами элемент исходного массива с индексом left с элементом, индекс
     * которого right.
     *
     * @param array - исходный массив.
     * @param left  - первый индекс.
     * @param right - второй индекс.
     */
    private void swapElements(T[] array, int left, int right) {
        T temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
