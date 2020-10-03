package sorting_person.impl;

import person.Person;
import sorter.api.Sorter;
import sorter.impl.quick_sort.QuickSort;
import sortingPerson.api.IdenticalPersonsException;

import java.util.Comparator;

/**
 * Реализация сортировки массива объектов Person по алгоритму быстрой сортировки.
 */
public class SortingPersonsByQuickSort extends SortingPerson {

    /**
     * Сортировка массива объектов Person по алгоритму быстрой сортировки. Алгоритм quicksort
     * реализован в классе QuickSort<T>. Сравнение двух объектов Person проводится при помощи
     * компаратора.
     * Порядок сортировки:
     * -первые идут мужчины
     * -выше в списке тот, кто более старший
     * -имена сортируются по алфавиту.
     *
     * @param persons - исходный массив.
     * @throws IdenticalPersonsException - идентичные объекты Person по всем полям.
     */
    public void sort(Person[] persons) throws IdenticalPersonsException {

        Comparator<Person> comparator = getComparatorSexThenNameThenAgeComparing();

        final Sorter<Person> personsSort = new QuickSort<>();
        personsSort.sort(persons, comparator);

    }


}
