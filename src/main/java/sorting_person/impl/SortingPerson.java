package sorting_person.impl;

import person.Person;
import sortingPerson.api.IdenticalPersonsException;
import sortingPerson.api.PersonSorter;

import java.util.Comparator;

import static java.util.Comparator.reverseOrder;

/**
 * Абстрактный класс реализующий компаратор для Person.
 */
public abstract class SortingPerson implements PersonSorter {

    /**
     * Компаратор для сравнения двух объектов Person. Сначала сравнивает по полю sex.
     * Если равны по предыдущему сравнению, сравнивает по полю name. Если снова равны,
     * то продолжает сравнение по полю age. В случае равных по все полям объектов бросает
     * исключение идентичности Person.
     *
     * @return - компаратор сравнения двух объетов Person.
     */
    protected Comparator<Person> getComparatorSexThenNameThenAgeComparing() throws IdenticalPersonsException {
        return Comparator.comparing(Person::getSex)
                .thenComparing((person) -> person.getName().toLowerCase())
                .thenComparing(Person::getAge, reverseOrder())
                .thenComparing(person -> {
                    throw new IdenticalPersonsException();
                });
    }
}
