package sorting_person.impl;

import person.Person;
import sorter.api.Sorter;
import sorter.impl.quick_sort.QuickSort;
import sortingPerson.api.IdenticalPersonsException;
import sortingPerson.api.PersonSorter;

import java.util.Comparator;

import static java.util.Comparator.reverseOrder;

/**
 * Реализация сортировки массива объектов Person по алгоритму быстрой сортировки.
 */
public class SortingPersonsByQuickSort implements PersonSorter {

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

    /**
     * Компаратор для сравнения двух объектов Person. Сначала сравнивает по полю sex.
     * Если равны по предыдущему сравнению, сравнивает по полю name. Если снова равны,
     * то продолжает сравнение по полю age. В случае равных по все полям объектов бросает
     * исключение идентичности Person.
     *
     * @return - компаратор сравнения двух объетов Person.
     */
    private Comparator<Person> getComparatorSexThenNameThenAgeComparing() throws IdenticalPersonsException {
        return Comparator.comparing(Person::getSex)
                .thenComparing((person) -> person.getName().toLowerCase())
                .thenComparing(Person::getAge, reverseOrder())
                .thenComparing(person -> {
                    throw new IdenticalPersonsException();
                });
    }
}
