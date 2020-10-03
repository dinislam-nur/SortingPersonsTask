package sorter.api;

import java.util.Comparator;
import java.util.List;

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
    void sort(List<T> array) throws ClassCastException;

    /**
     * Сортировка массива. Порядок сортировки зависит от компаратора, который
     * передается на вход.
     *
     * @param array      - массив для сортировки.
     * @param comparator - компаратор, в котором предоставлен метод
     *                   для сравнения двух элементов массива.
     */
    void sort(List<T> array, Comparator<T> comparator);

    /**
     * Компаратор по умолчанию, вызывающий метод compareTo на эллементе массива
     * для сравнение с другим элементом.
     *
     * @return - компаратор по умолчанию.
     * @throws ClassCastException - если элементы массива не предоставляют реализацию
     *                            метода compareTo интерфеса Comparable<T>.
     */
    default Comparator<T> defaultComparator() throws ClassCastException {
        return (o1, o2) -> {
            if (o1 instanceof Comparable) {
                return ((Comparable<T>) o1).compareTo(o2);
            } else {
                throw new ClassCastException("Class " + o1.getClass() + "should implements interface Comparable<T>");
            }
        };
    }
}
