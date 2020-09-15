package sorter.api;

import java.util.Comparator;

/**
 * Интерфейс, предоставляющий методы сортировки массива.
 *
 * @param <T> - тип массива.
 */
public interface Sorter<T> {

    /**
     * Сортировка массива в порядке по умолчанию, заданном реализацией
     * метода compareTo(T) интерфейса Comparable<T>.
     *
     * @param array - массив для сортировки.
     * @throws ClassCastException - класс T не реализует интерфейс Comparable<T>.
     */
    void sort(T[] array) throws ClassCastException;

    /**
     * Сортировка массива. Порядок сортировки зависит от компаратора, который
     * передается на вход.
     *
     * @param array      - массив для сортировки.
     * @param comparator - компаратор, в котором предоставлен метод
     *                   для сравнения двух элементов массива.
     */
    void sort(T[] array, Comparator<T> comparator);
}
