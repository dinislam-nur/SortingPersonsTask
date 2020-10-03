package sortingPerson.api;

import person.Person;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.reverseOrder;

/**
 * Интерфейс, предоставляющий метод сортировки массива Person объектов.
 */
public interface PersonSorter {

    /**
     * Сортировка массива Person объектов.
     *
     * @param persons - исходный массив.
     * @throws IdenticalPersonsException - идентичные объекты Person по всем полям.
     */
    void sort(List<Person> persons) throws IdenticalPersonsException;

    /**
     * Компаратор для сравнения двух объектов Person. Сначала сравнивает по полю sex.
     * Если равны по предыдущему сравнению, сравнивает по полю name. Если снова равны,
     * то продолжает сравнение по полю age. В случае равных по все полям объектов бросает
     * исключение идентичности Person.
     *
     * @return - компаратор сравнения двух объетов Person.
     */
    default Comparator<Person> getComparatorSexThenNameThenAgeComparing() throws IdenticalPersonsException {
        return Comparator.comparing(Person::getSex)
                .thenComparing((person) -> person.getName().toLowerCase())
                .thenComparing(Person::getAge, reverseOrder())
                .thenComparing(person -> {
                    throw new IdenticalPersonsException();
                });
    }
}
