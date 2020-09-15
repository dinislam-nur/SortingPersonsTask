package sorting_person.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import person.Person;
import sex.Sex;
import sortingPerson.api.IdenticalPersonsException;
import sortingPerson.api.PersonSorter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class SortingPersonsByMergeSortTest {

    @Test
    @DisplayName("Test some example")
    void sortTest() {
        //Prepare
        final Person john = new Person(Sex.MAN, "John", 23);
        final Person olderJohn = new Person(Sex.MAN, "John", 24);
        final Person ivan = new Person(Sex.MAN, "Ivan", 23);
        final Person dasha = new Person(Sex.WOMAN, "Dasha", 23);
        final Person maria = new Person(Sex.WOMAN, "Maria", 14);
        Person[] persons = new Person[]{ivan, dasha, john, olderJohn, maria};
        Person[] answer = new Person[]{ivan, olderJohn, john, dasha, maria};

        //Execution
        final PersonSorter sorter = new SortingPersonsByMergeSort();
        sorter.sort(persons);

        //Assertion
        assertArrayEquals(answer, persons);
    }


    @Test
    @DisplayName("Test throwable example")
    void sortEqualsPersonTest() {
        //Prepare
        final Person john = new Person(Sex.MAN, "John", 23);
        final Person olderJohn = new Person(Sex.MAN, "John", 24);
        final Person ivan = new Person(Sex.MAN, "Ivan", 23);
        final Person ivanEqual = new Person(Sex.MAN, "Ivan", 23);
        final Person dasha = new Person(Sex.WOMAN, "Dasha", 23);
        final Person maria = new Person(Sex.WOMAN, "Maria", 14);
        Person[] persons = new Person[]{ivan, dasha, john, olderJohn, maria, ivanEqual};

        final PersonSorter sorter = new SortingPersonsByMergeSort();

        //Assertion
        assertThrows(IdenticalPersonsException.class, () -> sorter.sort(persons));
    }

    @Test
    void sortingRandomPersonsTest() {
        boolean isFailed = true;
        int count = 0;
        while (isFailed) {
            Person[] people = personsGenerator(10000);
            try {
                sortingTime(people);
                isFailed = false;
            } catch (IdenticalPersonsException e) {
                count++;
                System.out.println("fail attempt = " + count);
            }
        }
    }

    private void sortingTime(Person[] people) {
        final  PersonSorter sorter = new SortingPersonsByMergeSort();

        final long start = System.currentTimeMillis();
        sorter.sort(people);
        final long end = System.currentTimeMillis();
        final long timeExecution = end - start;
        System.out.println("timeExecution = " + timeExecution + " Millis");
        for (Person person : people) {
            System.out.println(person);
        }
    }

    private Person[] personsGenerator(int n) {
        final ThreadLocalRandom current = ThreadLocalRandom.current();
        final Person[] people = new Person[n];
        final Map<Sex, List<String>> names = namesPull();
        final List<String> maleNames = names.get(Sex.MAN);
        final List<String> femaleNames = names.get(Sex.WOMAN);
        for (int i = 0; i <n; i++) {
            final boolean isMan = current.nextBoolean();
            int age = current.nextInt(10, 100000);
            if (isMan) {
                String name = maleNames.get(current.nextInt(0, maleNames.size()));
                people[i] = new Person(Sex.MAN, name, age);
            } else {
                String name = femaleNames.get(current.nextInt(0, femaleNames.size()));
                people[i] = new Person(Sex.WOMAN, name, age);
            }
        }
        return people;
    }

    private Map<Sex, List<String>> namesPull() {

        final EnumMap<Sex, List<String>> enumMap = new EnumMap<>(Sex.class);

        final String maleNameResourcePath = "male_names.txt";
        final List<String> maleNames = getListName(maleNameResourcePath);
        enumMap.put(Sex.MAN, maleNames);
        final String femaleNamesResourcePath = "female_names.txt";
        final List<String> femaleNames = getListName(femaleNamesResourcePath);
        enumMap.put(Sex.WOMAN,femaleNames);
        return enumMap;
    }

    private List<String> getListName(String nameResourcePath) {
        final List<String> names = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(
                new InputStreamReader(
                        SortingPersonsByMergeSort.class.getResourceAsStream(nameResourcePath)
                )
        )) {
            while (bfr.ready()) {
                String name = bfr.readLine().trim().toLowerCase();
                final char[] chars = name.toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                name = new String(chars);
                names.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }
}