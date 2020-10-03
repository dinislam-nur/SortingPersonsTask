package sorter.impl;

import sorter.api.Sorter;

import java.util.Comparator;

/**
 * Абстрактный класс реализующий компаратор по умолчанию.
 *
 * @param <T> - тип элементов сортировки.
 */
public abstract class AbstractSorter<T> implements Sorter<T> {

    /**
     * Компаратор по умолчанию, вызывающий метод compareTo на эллементе массива
     * для сравнение с другим элементом.
     *
     * @return - компаратор по умолчанию.
     * @throws ClassCastException - если элементы массива не предоставляют реализацию
     *                            метода compareTo интерфеса Comparable<T>.
     */
    protected Comparator<T> defaultComparator() throws ClassCastException {
        return (o1, o2) -> {
            if (o1 instanceof Comparable) {
                return ((Comparable<T>) o1).compareTo(o2);
            } else {
                throw new ClassCastException("Class " + o1.getClass() + "should implements interface Comparable<T>");
            }
        };
    }
}
