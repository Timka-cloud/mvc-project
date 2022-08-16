package kz.timka.mvc.models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class Person {
    private Long id;
    @NotEmpty(message = "name must not be empty")
    @Size(min = 3, max = 30, message = "name must should be between 3 and 30 characters")
    private String name;
    @NotNull(message = "age must not be empty")
    @Min(value = 0, message = "age should be greater than zero")
    private int age;
    @Email(message = "email should be valid")
    @NotEmpty(message = "email must not be empty")
    private String email;


    public Person(Long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
