package sortingPerson.api;

import person.Person;

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
    void sort(Person[] persons) throws IdenticalPersonsException;
}
