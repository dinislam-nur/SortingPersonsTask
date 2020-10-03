package sorting_person.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import person.Person;
import sex.Sex;
import sortingPerson.api.IdenticalPersonsException;
import sortingPerson.api.PersonSorter;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SortingPersonsByMergeSortTest {

    @Test
    @DisplayName("Test some example")
    void sortTest() {
        final Person john = new Person(Sex.MAN, "John", 23);
        final Person olderJohn = new Person(Sex.MAN, "John", 24);
        final Person ivan = new Person(Sex.MAN, "Ivan", 23);
        final Person dasha = new Person(Sex.WOMAN, "Dasha", 23);
        final Person maria = new Person(Sex.WOMAN, "Maria", 14);
        List<Person> persons = Arrays.asList(ivan, dasha, john, olderJohn, maria);
        List<Person> answer = Arrays.asList(ivan, olderJohn, john, dasha, maria);

        final PersonSorter sorter = new SortingPersonsByMergeSort();
        sorter.sort(persons);

        for (int i = 0; i < answer.size(); i++) {
            assertEquals(answer.get(i), persons.get(i));
        }
    }


    @Test
    @DisplayName("Test throwable example")
    void sortEqualsPersonTest() {
        final Person john = new Person(Sex.MAN, "John", 23);
        final Person olderJohn = new Person(Sex.MAN, "John", 24);
        final Person ivan = new Person(Sex.MAN, "Ivan", 23);
        final Person ivanEqual = new Person(Sex.MAN, "Ivan", 23);
        final Person dasha = new Person(Sex.WOMAN, "Dasha", 23);
        final Person maria = new Person(Sex.WOMAN, "Maria", 14);
        List<Person> persons = Arrays.asList(ivan, dasha, john, olderJohn, maria, ivanEqual);

        final PersonSorter sorter = new SortingPersonsByMergeSort();

        assertThrows(IdenticalPersonsException.class, () -> sorter.sort(persons));
    }

    @Test
    void sortingRandomPersonsTest() {
        boolean isFailed = true;
        int count = 0;
        final int size = 10000;
        while (isFailed) {
            List<Person> people = PersonGenerator.generate(size);
            final  PersonSorter sorter = new SortingPersonsByMergeSort();
            try {
                sortingTime(people, sorter);
                isFailed = false;
                List<Person> answer = new ArrayList<>(people);
                answer.sort(sorter.getComparatorSexThenNameThenAgeComparing());

                for (int i = 0; i < size; i++) {
                    assertEquals(answer.get(i), people.get(i));
                }

            } catch (IdenticalPersonsException e) {
                count++;
                System.out.println("fail attempt = " + count);
            }
        }


    }

    private void sortingTime(List<Person> people, PersonSorter sorter) {
        final long start = System.currentTimeMillis();
        sorter.sort(people);
        final long end = System.currentTimeMillis();
        final long timeExecution = end - start;
        System.out.println("timeExecution = " + timeExecution + " Millis");
    }
}