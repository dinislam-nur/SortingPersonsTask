package sorting_person.impl;

import person.Person;
import sorter.api.Sorter;
import sorter.impl.merge_sort.MergeSort;
import sortingPerson.api.IdenticalPersonsException;
import sortingPerson.api.PersonSorter;

import java.util.Comparator;
import java.util.List;


/**
 * Реализация сортировки массива объектов Person по алгоритму quicksort.
 */
public class SortingPersonsByMergeSort implements PersonSorter {

    /**
     * Сортировка массива объектов Person по алгоритму сортировки слиянием. Алгоритм сортировки слиянием
     * реализован в классе MergeSort<T>. Сравнение двух объектов Person проводится при помощи
     * компаратора.
     * Порядок сортировки:
     * -первые идут мужчины
     * -выше в списке тот, кто более старший
     * -имена сортируются по алфавиту.
     *
     * @param persons - исходный массив.
     * @throws IdenticalPersonsException - идентичные объекты Person по всем полям.
     */
    public void sort(List<Person> persons) throws IdenticalPersonsException {

        Comparator<Person> comparator = getComparatorSexThenNameThenAgeComparing();

        final Sorter<Person> personsSort = new MergeSort<>();
        personsSort.sort(persons, comparator);

    }
}
