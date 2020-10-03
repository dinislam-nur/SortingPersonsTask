package sorting_person.impl;

import person.Person;
import sex.Sex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class PersonGenerator {

    public static List<Person> generate(int n) {
        final ThreadLocalRandom current = ThreadLocalRandom.current();
        final List<Person> people = new ArrayList<>(n);
        final Map<Sex, List<String>> names = namesPull();
        final List<String> maleNames = names.get(Sex.MAN);
        final List<String> femaleNames = names.get(Sex.WOMAN);
        for (int i = 0; i <n; i++) {
            final boolean isMan = current.nextBoolean();
            int age = current.nextInt(10, 100000);
            if (isMan) {
                String name = maleNames.get(current.nextInt(0, maleNames.size()));
                people.add(new Person(Sex.MAN, name, age));
            } else {
                String name = femaleNames.get(current.nextInt(0, femaleNames.size()));
                people.add(new Person(Sex.WOMAN, name, age));
            }
        }
        return people;
    }

    private static Map<Sex, List<String>> namesPull() {

        final EnumMap<Sex, List<String>> enumMap = new EnumMap<>(Sex.class);

        final String maleNameResourcePath = "male_names.txt";
        final List<String> maleNames = getListName(maleNameResourcePath);
        enumMap.put(Sex.MAN, maleNames);
        final String femaleNamesResourcePath = "female_names.txt";
        final List<String> femaleNames = getListName(femaleNamesResourcePath);
        enumMap.put(Sex.WOMAN,femaleNames);
        return enumMap;
    }

    private static List<String> getListName(String nameResourcePath) {
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
