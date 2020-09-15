package person;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import sex.Sex;

/**
 * Класс Person.
 * Создает экземпляр человека со свойствоми пола, имени и возраста.
 */
@RequiredArgsConstructor
@Getter
@ToString
public class Person {

    /**
     * Пол объекта
     */
    private final Sex sex;

    /**
     * Имя объекта
     */
    private final String name;

    /**
     * Возраст объекта
     */
    private final int age;

}
