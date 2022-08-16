package kz.timka.mvc.dao;

import kz.timka.mvc.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class PersonDAO {
    private static Long PEOPLE_COUNT = 0L;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom", 24,"tom@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Alex", 52, "bob@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Jane", 18, "jane@mail.ru"));
    }

    public List<Person> findAll() {
        return Collections.unmodifiableList(people);
    }

    public Person findById(Long id) {
        return people.stream().filter((p) -> p.getId().equals(id)).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
       people.add(person);
    }


    public void update(Long id, Person person) {
        Person personToBeUpdated = findById(id);
        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());

    }

    public void delete(Long id) {
        people.removeIf((p) -> p.getId().equals(id));
    }
}
